'use-client';

import React, { useEffect, useState } from 'react';
import Link from 'next/link';
import { Categorias } from '@/lib/types';
import { Card, CardContent, CardHeader, CardTitle } from './ui/card';
import CustomException from '@/lib/exception/CustomException';
import { useToast } from '@/hooks/toast/use-toast';

interface Props {
  service: () => Promise<Categorias[]>;
}

export const ListaCategorias = React.memo(({ service }: Props) => {
  const { toast } = useToast();
  const [categorias, setCategorias] = useState<Categorias[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    fetchProdutos();
  }, []);

  const fetchProdutos = async () => {
    setLoading(true);
    try {
      const response = await service();
      setCategorias(response);
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

  if (loading) {
    return (
      <div className="flex justify-center items-center min-h-[400px]">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-primary"></div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-6">
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 sm:gap-6">
        {categorias.map((categoria) => (
          <Link
            key={categoria.id}
            href={`/categorias/${categoria.id}`}
            onClick={() => {
              console.log(typeof window)
              if (typeof window !== "undefined") {
                localStorage.setItem('categoria', JSON.stringify(categoria))
              }
            }}
            className="block h-full" prefetch
          >
            <Card className="h-full hover:shadow-lg transition-shadow duration-300 ease-in-out">
              <CardHeader className="p-4 sm:p-6">
                <CardTitle className="text-lg sm:text-xl line-clamp-2">
                  {categoria.nome}
                </CardTitle>
              </CardHeader>
              <CardContent className="p-4 sm:p-6 pt-0">
                <p className="text-sm text-muted-foreground line-clamp-3">
                  {categoria.descricao}
                </p>
              </CardContent>
            </Card>
          </Link>
        ))}
      </div>
    </div>
  );
});