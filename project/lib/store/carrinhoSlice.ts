import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Carrinho, Produto } from '../types';

interface CarrinhoState {
  items: Carrinho[];
  total: number;
}

const saveToLocalStorage = (state: CarrinhoState) => {
  if (typeof window !== "undefined") {
    localStorage.setItem('carrinho', JSON.stringify(state));
  }
};

const loadFromLocalStorage = (): CarrinhoState => {
  if (typeof window === "undefined") {
    return { items: [], total: 0 };
  }
  const data = localStorage.getItem('carrinho');
  return data ? JSON.parse(data) : { items: [], total: 0 };
};

const initialState: CarrinhoState = loadFromLocalStorage();

const carrinhoSlice = createSlice({
  name: 'carrinho',
  initialState,
  reducers: {
    addProdutoAoCarrinho: (state, action: PayloadAction<{ produto: Produto; quantidade: number; preco: number }>) => {
      const { produto, quantidade } = action.payload;
      const itemExistente = state.items.find(item => item.produto.id === produto.id);

      if (itemExistente) {
        itemExistente.quantidade += quantidade;
      } else {
        state.items.push({ produto, quantidade, preco: produto.preco });
      }

      state.total = state.items.reduce((total, item) => total + item.produto.preco * item.quantidade, 0);
      saveToLocalStorage(state);
    },
    removeProdutoDoCarrinho: (state, action: PayloadAction<number>) => {
      state.items = state.items.filter(item => item.produto.id !== action.payload);
      state.total = state.items.reduce((total, item) => total + item.produto.preco * item.quantidade, 0);
      saveToLocalStorage(state);
    },
    updatequantidade: (state, action: PayloadAction<{ produtoId: number; quantidade: number }>) => {
      const { produtoId, quantidade } = action.payload;
      const item = state.items.find(item => item.produto.id === produtoId);
      if (item) {
        item.quantidade = quantidade;
      }
      state.total = state.items.reduce((total, item) => total + item.produto.preco * item.quantidade, 0);
      saveToLocalStorage(state);
    },
    limparCarrinho: (state) => {
      state.items = [];
      state.total = 0;
      saveToLocalStorage(state);
    },
  },
});

export const { addProdutoAoCarrinho, removeProdutoDoCarrinho, updatequantidade, limparCarrinho } = carrinhoSlice.actions;
export default carrinhoSlice.reducer;
