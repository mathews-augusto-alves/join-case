'use client';

import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '@/lib/store/store';
import { useEffect, useState } from 'react';
import { Pedido } from '@/lib/types';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { formatCurrency } from '@/lib/utils';
import Link from 'next/link';
import { getPedidoPorUsuario } from '@/service/pedidoService';
import { validarTokenThunk } from '@/lib/store/authSlice';
import CustomException from '@/lib/exception/CustomException';
import { useToast } from '@/hooks/toast/use-toast';

export default function ContaPage() {
  const { usuario, isAuthenticated } = useSelector((state: RootState) => state.auth);
  const [pedidos, setPedidos] = useState<Pedido[]>([]);
  const dispatch = useDispatch<AppDispatch>();
  const { toast } = useToast();

  useEffect(() => {
    dispatch(validarTokenThunk());
  }, [dispatch]);

  useEffect(() => {
    handlePedidos();
  }, []);

  async function handlePedidos() {
    try {
      if (isAuthenticated) {
        const response = await getPedidoPorUsuario(usuario?.email ?? "");
        if(response) setPedidos(response);
      }
    } catch (ex) {
      const error = ex as CustomException;
      toast({
        title: error.title,
        description: error.errors
      });
    }
  }

  if (!usuario) {
    return (
      <div className="text-center py-12">
        <h1 className="text-2xl font-bold mb-4">Faça o login para ver sua conta</h1>
        <Link href="/login" className="text-primary hover:underline">
          Ir para o login
        </Link>
      </div>
    );
  }

  return (
    <div className="space-y-8">
      <div>
        <h1 className="text-3xl font-bold">Minha conta</h1>
        <p className="text-muted-foreground mt-2">Bem vindo de volta, {usuario.nome}!</p>
      </div>

      <div>
        <h2 className="text-2xl font-semibold mb-4">Meus pedidos</h2>
        {pedidos.length === 0 ? (
          <p>Sem pedidos.</p>
        ) : (
          <div>
            {pedidos.map((pedido) => (
              <div className="py-2">
                <Card className="hover:shadow-lg transition-shadow duration-300 ease-in-out">
                  <CardHeader>
                    <CardTitle>Pedido #{pedido.id}</CardTitle>
                  </CardHeader>
                  <CardContent>
                    <div className="flex justify-between">
                      <div>
                        <p className="text-sm text-muted-foreground">
                          Status: {pedido.status?.descricao}
                        </p>
                        <p className="text-sm text-muted-foreground">
                          Data: {new Date(pedido.criadoEm ?? "").toLocaleDateString()}
                        </p>
                      </div>
                      <p className="font-semibold">{formatCurrency(pedido.total)}</p>
                    </div>
                  </CardContent>
                </Card>
                {/* <Link key={pedido.id} href={`/pedidos/${pedido.id}`} >
                </Link> */}
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}