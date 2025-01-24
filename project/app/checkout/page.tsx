'use client';

import { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useRouter } from 'next/navigation';
import { AppDispatch, RootState } from '@/lib/store/store';
import { Button } from '@/components/ui/button';
import { formatCurrency } from '@/lib/utils';
import enviarPedido from '@/service/pedidoService';
import { validarTokenThunk } from '@/lib/store/authSlice';
import { useToast } from '@/hooks/toast/use-toast';
import CustomException from '@/lib/exception/CustomException';
import { Loader2 } from 'lucide-react';
import { limparCarrinho } from '@/lib/store/carrinhoSlice';

export default function CheckoutPage() {
  const { toast } = useToast();
  const { items, total } = useSelector((state: RootState) => state.carrinho);
  const { usuario, isAuthenticated } = useSelector((state: RootState) => state.auth);
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch<AppDispatch>();
  const router = useRouter();

  useEffect(() => {
    dispatch(validarTokenThunk());
  }, [dispatch]);

  useEffect(() => {
    if (!isAuthenticated) {
      router.push('/login');
    } else if (items.length === 0) {
      router.push('/carrinho');
    }
  }, [isAuthenticated, items.length, router]);

  if (!isAuthenticated || items.length === 0) {
    return null;
  }

  const handleSubmit = async () => {
    setLoading(true);
    try {
      const response = await enviarPedido({
        usuario: {
          email: usuario?.email ?? ""
        },
        total,
        pedidoProduto: items
      });

      toast({
        title: response.title,
        description: [response.message]
      });
      dispatch(limparCarrinho());
      router.push('/conta');
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

  return (
    <div className="max-w-lg mx-auto">
      <h1 className="text-2xl font-bold mb-6">Checkout</h1>
      <div className="grid grid-cols-1 gap-8">
        <div className="space-y-4">
          <h2 className="text-lg font-semibold mb-4">Pedido</h2>
          <div className="border rounded-lg p-4">
            {items.map((item) => (
              <div key={item.produto.id} className="flex justify-between py-2">
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

            <div className="border-t mt-4 pt-4">
              <div className="flex justify-between font-bold text-lg">
                <span>Total</span>
                <span>{formatCurrency(total)}</span>
              </div>
            </div>
          </div>
          <Button onClick={handleSubmit} className="w-full">
            { loading ? <Loader2 className="animate-spin text-white-500"/> : "Concluir" }
          </Button>
        </div>
      </div>
    </div>
  );
}