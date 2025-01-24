import { useEffect, useState } from 'react';
import { Categorias } from '@/lib/types';
import { ProdutosGrid } from '@/components/lista-produto';
import { getAllProdutoPorCategoria } from '@/service/produtoService';
import { Loader } from './ui/loading';

export function DetalheCategoria() {
  const [categorias, setCategorias] = useState<Categorias | null>(null);

  useEffect(() => {
    if (typeof window !== "undefined") {
      const data = localStorage.getItem('categoria');
      if (data) {
        setCategorias(JSON.parse(data));
      }
    }
  }, []);

  if (!categorias) {
    return <Loader className="flex justify-center items-center min-h-[400px]"/>;
  }

  return (
    <div>
      <div className="mb-8">
        <h1 className="text-3xl font-bold">{categorias.nome}</h1>
        <p className="text-muted-foreground mt-2">{categorias.descricao}</p>
      </div>

      <ProdutosGrid service={getAllProdutoPorCategoria} itensPorPagina={8} categoriaId={categorias.id}/>
    </div>
  );
}