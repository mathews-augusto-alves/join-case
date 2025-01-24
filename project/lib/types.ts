export interface Categorias {
  id: number;
  nome: string;
  descricao: string;
  criadoEm: string;
  atualizadoEm: string;
}

export interface Produto {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
  categoria: Categorias;
  criadoEm: string;
  atualizadoEm: string;
}

export interface PedidoStatus {
  id: number;
  nome: string;
  descricao: string;
}

export interface Pedido {
  id?: number;
  usuario: Usuario;
  total: number;
  status?: PedidoStatus;
  criadoEm?: string;
  atualizadoEm?: string;
  pedidoProduto: Carrinho[];
}

export interface Carrinho {
  produto: Produto;
  quantidade: number;
  preco: number;
}

export interface Usuario {
  nome?: string;
  email: string;
  jwt?: string;
}

export interface ProdutoFilter {
  id?: number;
  categoriaId?: number;
  page?: number;
  size?: number;
}

export interface Categoria {
  id: number;
  nome: string;
  descricao: string;
  criadoEm: string;
  atualizadoEm: string;
}

export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface Pageable {
  pageNumber: number;
  pageSize: number;
  sort: Sort;
  offset: number;
  paged: boolean;
  unpaged: boolean;
}

export interface PaginacaoResponse<T> {
  content: T[];
  pageable: Pageable;
  totalPages: number;
  totalElements: number;
  last: boolean;
  size: number;
  number: number;
  sort: Sort;
  numberOfElements: number;
  first: boolean;
  empty: boolean;
}

export interface SucessoResponse {
  message: string;
  title: string;
}
