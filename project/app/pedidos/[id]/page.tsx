'use client';

import { useEffect, useState } from 'react';
import { Pedido } from '@/lib/types';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { formatCurrency } from '@/lib/utils';
import { Loader } from '@/components/ui/loading';

export default function PedidoPage({ params }: { params: { id: string } }) {
  const [pedido, setPedido] = useState<Pedido | null>(null);

  useEffect(() => {
    setPedido(null);
  }, [params.id]);

  if (!pedido) {
    return <Loader className="flex justify-center items-center min-h-[400px]"/>;
  }

  return (
    <div className="space-y-8">
      <div>
        <h1 className="text-3xl font-bold">Pedido #{pedido.id}</h1>
        <p className="text-muted-foreground mt-2">
          Feito em {new Date(pedido.criadoEm ?? "").toLocaleDateString()}
        </p>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>Status do pedido: {pedido.status ? String(pedido.status) : ""}</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="space-y-6">
            <div>
              <h3 className="font-semibold mb-2">Itens</h3>
              <div className="space-y-4">
                {pedido.pedidoProduto.map((item) => (
                  <div key={item.produto.id} className="flex justify-between">
                    <div>
                      <p className="font-medium">{item.produto.nome}</p>
                      <p className="text-sm text-muted-foreground">
                        Quantidade: {item.quantidade}
                      </p>
                    </div>
                    <p className="font-medium">
                      {formatCurrency(item.produto.preco * item.quantidade)}
                    </p>
                  </div>
                ))}
              </div>
            </div>

            <div className="bpedido-t pt-4">
              <div className="flex justify-between font-bold text-lg">
                <span>Total</span>
                <span>{formatCurrency(pedido.total)}</span>
              </div>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}