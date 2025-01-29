import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Usuario } from '../types';
import { validarToken } from '@/service/loginService';
import { dispatch, genId } from '@/hooks/toast/toastUtils';

interface AuthState {
  usuario: Usuario | null;
  isAuthenticated: boolean;
}

function getInitialState(): AuthState {
  if (typeof window === "undefined") {
    return { usuario: null, isAuthenticated: false };
  }
  
  const usuario = JSON.parse(localStorage.getItem('usuario') || 'null');
  const isAuthenticated = localStorage.getItem('isAuthenticated') === 'true';
  const jwt = localStorage.getItem('jwt');
  if (!jwt) {
    localStorage.clear();
    return { usuario: null, isAuthenticated: false };
  }

  return { usuario, isAuthenticated };
}

export const validarTokenThunk = createAsyncThunk('auth/validarToken', async (_, { dispatch }) => {
  if (typeof window === "undefined") {
    return { usuario: null, isAuthenticated: false };
  }
  const jwt = localStorage.getItem('jwt');
  try {
    if(jwt) {
      const result = await validarToken(jwt ?? '');
      if (!result) {
        dispatch(authSlice.actions.logout());
      }
    }
  } catch (error) {
    dispatch(authSlice.actions.logout());
  }
});

const authSlice = createSlice({
  name: 'auth',
  initialState: getInitialState(),
  reducers: {
    login: (state, action: PayloadAction<Usuario>) => {
      if(action.payload.jwt && typeof window !== "undefined") {

        localStorage.setItem('usuario', JSON.stringify(action.payload));
        localStorage.setItem('isAuthenticated', 'true');
        localStorage.setItem('jwt', action.payload.jwt);
  
        state.usuario = action.payload;
        state.isAuthenticated = true;
      }
    },
    logout: (state) => {
      if (typeof window !== "undefined") {
        localStorage.clear();
        state.usuario = null;
        state.isAuthenticated = false;
        dispatch({
          type: 'ADD_TOAST',
          toast: { title: "Login expirado", description: ["Para continuar, faça o login novamente."], id: genId() },
        });
      }
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
