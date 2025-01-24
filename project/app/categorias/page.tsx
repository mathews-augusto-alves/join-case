'use client';

import { ListaCategorias } from '@/components/lista-categorias';
import getAllCategorias from '@/service/categoriaService';

export default function Categoria() {
  return (
    <div className="space-y-8">
      <div>
        <h1 className="text-3xl font-bold">Categorias</h1>
        <p className="text-muted-foreground mt-2">Explore por categorias</p>
      </div>

      <ListaCategorias service={getAllCategorias}/>
    </div>
  );
}
