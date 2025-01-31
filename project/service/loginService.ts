import { jwtDecode } from 'jwt-decode';
import { Usuario } from '@/lib/types';
import CustomException from '@/lib/exception/CustomException';

const API_URL = process.env.NEXT_PUBLIC_API_URL;
const LOGIN_ENDPOINT = `${API_URL}/app-control-api/public/login`;
const VALIDATE_TOKEN_ENDPOINT = `${API_URL}/app-control-api/public/valid-token`;

interface LoginResponse {
    data: {
        jwt: string;
    }
}

const headers = {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
};

export default async function usuarioLogin(email: string, senha: string): Promise<Usuario> {
    try {
        const response = await fetch(LOGIN_ENDPOINT, {
            method: 'POST',
            headers,
            body: JSON.stringify({ email, senha }),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new CustomException(
                errorData.title || 'Erro no login',
                Array.isArray(errorData.errors) ? errorData.errors : ['Erro desconhecido']
            );
        }

        const { data } = await response.json() as LoginResponse;
        const decodedToken = jwtDecode<Usuario>(data.jwt);
        
        return {
            email: decodedToken.email,
            nome: decodedToken.nome,
            jwt: data.jwt
        };
    } catch (error) {
        if (error instanceof CustomException) {
            throw error;
        }
        throw new CustomException(
            'Erro no login',
            ['Ocorreu um erro ao tentar fazer login. Por favor, tente novamente.']
        );
    }
}

export async function validarToken(token: string): Promise<boolean> {
    try {
        const response = await fetch(VALIDATE_TOKEN_ENDPOINT, {
            method: 'POST',
            headers,
            body: JSON.stringify({ token }),
        });

        if (!response.ok) {
            return false;
        }

        const resultado = await response.json();
        return resultado;
    } catch (error) {
        console.error('Erro ao validar token:', error);
        throw new CustomException(
            'Erro na validação do token',
            ['Não foi possível validar sua sessão. Por favor, faça login novamente.']
        );
    }
}
