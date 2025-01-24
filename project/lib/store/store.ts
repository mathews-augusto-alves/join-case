import { configureStore } from '@reduxjs/toolkit';
import cartReducer from './carrinhoSlice';
import authReducer from './authSlice';

export const store = configureStore({
  reducer: {
    carrinho: cartReducer,
    auth: authReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;