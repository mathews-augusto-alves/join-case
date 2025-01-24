'use client';

import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '@/lib/store/store';
import { removeProdutoDoCarrinho, updatequantidade } from '@/lib/store/carrinhoSlice';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { formatCurrency } from '@/lib/utils';
import Link from 'next/link';
import { Trash2 } from 'lucide-react';

export default function CarrinhoPage() {
  const { items, total } = useSelector((state: RootState) => state.carrinho);
  const dispatch = useDispatch();

  if (items.length === 0) {
    return (
      <div className="text-center py-12">
        <h1 className="text-2xl font-bold mb-4">Seu carrinho está vazio</h1>
        <Link href="/produtos">
          <Button>Continue comprando</Button>
        </Link>
      </div>
    );
  }

  return (
    <div>
      <h1 className="text-2xl font-bold mb-6">Carrinho de compras</h1>
      
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <div className="lg:col-span-2">
          {items.map((item) => (
            <div key={item.produto.id} className="flex items-center justify-between border-b py-4">
              <div className="flex-1">
                <h3 className="font-semibold">{item.produto.nome}</h3>
                <p className="text-sm text-muted-foreground">{item.produto.descricao}</p>
                <p className="font-medium mt-1">{formatCurrency(item.produto.preco)}</p>
              </div>
              
              <div className="flex items-center space-x-4">
                <Input
                  type="number"
                  min="1"
                  value={item.quantidade}
                  onChange={(e) => dispatch(updatequantidade({
                    produtoId: item.produto.id,
                    quantidade: parseInt(e.target.value)
                  }))}
                  className="w-20"
                />
                
                <Button
                  variant="destructive"
                  size="icon"
                  onClick={() => dispatch(removeProdutoDoCarrinho(item.produto.id))}
                >
                  <Trash2 className="h-4 w-4" />
                </Button>
              </div>
            </div>
          ))}
        </div>
        
        <div className="lg:col-span-1">
          <div className="border rounded-lg p-6">
            <h3 className="text-lg font-semibold mb-4">Pedido</h3>
            
            <div className="space-y-2">
              <div className="flex justify-between">
                <span>Subtotal</span>
                <span>{formatCurrency(total)}</span>
              </div>
              <div className="flex justify-between font-bold text-lg border-t pt-2">
                <span>Total</span>
                <span>{formatCurrency(total)}</span>
              </div>
            </div>
            
            <Link href="/checkout">
              <Button className="w-full mt-6">Continuar para pagamento</Button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}