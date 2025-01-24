import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { Produto } from '@/lib/types';
import { addProdutoAoCarrinho } from '@/lib/store/carrinhoSlice';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { formatCurrency } from '@/lib/utils';
import { getProdutoPorId } from '@/service/produtoService';
import CustomException from '@/lib/exception/CustomException';
import { useToast } from '@/hooks/toast/use-toast';
import { Loader } from './ui/loading';

interface Props {
  id: number
}

export function DetalheProduto({ id }: Props) {
  const { toast } = useToast();
  const [produto, setProduto] = useState<Produto | null>(null);
  const [quantidade, setQuantidade] = useState(1);
  const dispatch = useDispatch();

  useEffect(() => {
    fetchProdutos();
  }, [id]);

  const fetchProdutos = async () => {
    try {
      const response = await getProdutoPorId({ 
        id
      });
      setProduto(response);
    } catch (ex) {
      const error = ex as CustomException;
            toast({
              title: error.title,
              description: error.errors
            });
    } 
  };

  if (!produto) {
    return <Loader className="flex justify-center items-center min-h-[400px]"/>;
  }

  const handleaAdProdutoAoCarrinho = () => {
    dispatch(addProdutoAoCarrinho({ produto, quantidade, preco: produto.preco }));
  };

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
      <div>
        {/* Product Image Placeholder */}
        <div className="aspect-square bg-gray-100 rounded-lg"></div>
      </div>

      <div className="space-y-6">
        <div>
          <h1 className="text-3xl font-bold">{produto.nome}</h1>
          <p className="text-xl font-semibold mt-2">{formatCurrency(produto.preco)}</p>
        </div>

        <div>
          <h2 className="text-lg font-semibold mb-2">Descrição</h2>
          <p className="text-muted-foreground">{produto.descricao}</p>
        </div>

        <div>
          <h2 className="text-lg font-semibold mb-2">Categoria</h2>
          <p className="text-muted-foreground">{produto.categoria.nome}</p>
        </div>

        <div className="space-y-4">
          <div className="flex items-center space-x-4">
            <Input
              type="number"
              min="1"
              value={quantidade}
              onChange={(e) => setQuantidade(parseInt(e.target.value))}
              className="w-20"
            />
            <Button size="lg" onClick={handleaAdProdutoAoCarrinho}>
              Adicionar ao carrinho
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}