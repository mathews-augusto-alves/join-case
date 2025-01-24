'use client';

import { Button } from '@/components/ui/button';
import { ProdutosGrid } from '@/components/lista-produto';
import { ShoppingBag, Truck, Shield, Clock } from 'lucide-react';
import Link from 'next/link';
import getAllProduto from '@/service/produtoService';

export default function Home() {

return (
  <div className="space-y-20">
    <section className="relative py-20 bg-gradient-to-b from-primary/5 to-background">
      <div className="container mx-auto px-4">
        <div className="max-w-3xl">
          <h1 className="text-5xl font-bold tracking-tight mb-6">
            Descubra produtos incríveis
          </h1>
          <p className="text-xl text-muted-foreground mb-8">
            Compre produtos de alta qualidade. De eletrônicos a moda,
            encontre tudo o que você precisa em um só lugar.
          </p>
          <div className="flex gap-4">
            <Link href="/produtos">
              <Button size="lg">Ir as compras</Button>
            </Link>
            <Link href="/categorias">
              <Button variant="outline" size="lg">Categorias</Button>
            </Link>
          </div>
        </div>
      </div>
    </section>
  </div>
);
}