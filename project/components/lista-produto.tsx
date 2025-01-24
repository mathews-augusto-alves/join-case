import { useEffect, useState } from 'react';
import Link from 'next/link';
import { PaginacaoResponse, Produto, ProdutoFilter } from '@/lib/types';
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from './ui/card';
import { Button } from './ui/button';
import { useDispatch } from 'react-redux';
import { addProdutoAoCarrinho } from '@/lib/store/carrinhoSlice';
import { cn, formatCurrency } from '@/lib/utils';
import {
  Paginacao as PaginacaoComponente,
  ConteudoPaginacao,
  ItemPaginacao,
  ProximaPaginacao,
  PaginacaoAnterior,
} from './ui/pagination';
import { Loader } from './ui/loading';
import { useToast } from '@/hooks/toast/use-toast';
import CustomException from '@/lib/exception/CustomException';
import { renderItemPaginacaos } from './utils/pagination/util';

interface Props {
  service: (paginacao: ProdutoFilter) => Promise<PaginacaoResponse<Produto>>;
  itensPorPagina: number;
  categoriaId?: number;
}

export function ProdutosGrid({ service, itensPorPagina, categoriaId }: Props) {
  const { toast } = useToast();
  const [produtos, setProdutos] = useState<Produto[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalItens, setTotalItens] = useState(0);
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();

  const totalPages = Math.ceil(totalItens / itensPorPagina);

  useEffect(() => {
    fetchProdutos();
  }, [currentPage]);

  const fetchProdutos = async () => {
    setLoading(true);
    try {
      const response = await service({ 
        page: currentPage - 1, 
        size: itensPorPagina,
        categoriaId: categoriaId
      });
      if(response) {
        setProdutos(response.content);
        setTotalItens(response.totalElements);
      }
    } catch (ex) {
      const error = ex as CustomException;
      toast({
        title: error.title,
        description: error.errors
      });
    } finally {
      setLoading(false);
    }
  };

  const handleAddProdutoAoCarrinho = (produto: Produto) => {
    dispatch(addProdutoAoCarrinho({ produto, quantidade: 1, preco: produto.preco }));
  };

  const handlePageChange = (page: number) => {
    setCurrentPage(page);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  

  if (loading && currentPage === 1) {
    return (
      <Loader className="flex justify-center items-center min-h-[400px]"/>
    );
  }

  return (
    <div className="space-y-8">
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4">
        {produtos.map((produto) => (
          <Card key={produto.id} className="flex flex-col h-full hover:shadow-xl transition-shadow">
            <CardHeader className="flex-none">
              <CardTitle className="text-lg line-clamp-2 h-14">{produto.nome}</CardTitle>
            </CardHeader>
            <CardContent className="flex-grow">
              <p className="text-sm text-muted-foreground line-clamp-3 mb-4">{produto.descricao}</p>
              <p className="text-lg font-bold">{formatCurrency(produto.preco)}</p>
            </CardContent>
            <CardFooter className="flex-none mt-auto space-y-2">
              <div className="grid grid-cols-1 gap-2 w-full">
                <Link href={`/produtos/${produto.id}`} className="w-full">
                  <Button variant="outline" className="w-full">Ver detalhes</Button>
                </Link>
                <Button
                  onClick={() => handleAddProdutoAoCarrinho(produto)}
                  className="w-full"
                >
                  Adicionar ao carrinho
                </Button>
              </div>
            </CardFooter>
          </Card>
        ))}
      </div>

      {totalPages > 1 && (
        <div className="flex justify-center mt-8">
          <PaginacaoComponente>
            <ConteudoPaginacao className="flex-wrap justify-center gap-1">
              <ItemPaginacao>
                <PaginacaoAnterior
                  onClick={() => currentPage > 1 && handlePageChange(currentPage - 1)}
                  className={cn(currentPage === 1 && 'pointer-events-none opacity-50')}
                />
              </ItemPaginacao>

              {renderItemPaginacaos({currentPage, totalPages, handlePageChange})}

              <ItemPaginacao>
                <ProximaPaginacao
                  onClick={() => currentPage < totalPages && handlePageChange(currentPage + 1)}
                  className={cn(currentPage === totalPages && 'pointer-events-none opacity-50')}
                />
              </ItemPaginacao>
            </ConteudoPaginacao>
          </PaginacaoComponente>
        </div>
      )}
    </div>
  );
}