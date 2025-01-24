'use client';

import { DetalheProduto } from '@/components/detalhe-produto';

export default function ProdutoPage({ params }: { params: { id: number } }) {
  return <DetalheProduto id={params.id} />;
}