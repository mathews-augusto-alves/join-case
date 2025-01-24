'use client';

import { ProdutosGrid } from '@/components/lista-produto';
import getAllProduto from '@/service/produtoService';

export default function ProdutosPage() {
  
  return (
    <div className="space-y-8">
      <div>
        <h1 className="text-3xl font-bold">Todos os produtos</h1>
        <p className="text-muted-foreground mt-2">Escolha seus produtos</p>
      </div>

      <ProdutosGrid service={getAllProduto} itensPorPagina={8}/>
    </div>
  );
}