import CustomException from '@/lib/exception/CustomException';
import { SucessoResponse } from '@/lib/types';

const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8085';
const REGISTRO_ENDPOINT = `${API_URL}/app-control-api/public/register`;

const headers = {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
};

interface RegistroUsuario {
    nome: string;
    email: string;
    senha: string;
}

export default async function usuarioRegistro(registroUsuario: RegistroUsuario): Promise<SucessoResponse> {
    try {
        const response = await fetch(REGISTRO_ENDPOINT, {
            method: 'POST',
            headers,
            body: JSON.stringify(registroUsuario),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new CustomException(
                errorData.title || 'Erro ao cadastrar usuário',
                Array.isArray(errorData.errors) ? errorData.errors : ['Erro desconhecido']
            );
        }

        const { message, title } = await response.json();
        return { message, title };
    } catch (error) {
        if (error instanceof CustomException) {
            throw error;
        }
        throw new CustomException(
            'Erro ao cadastrar usuário',
            ['Ocorreu um erro ao tentar cadastrar. Por favor, tente novamente em instantes.']
        );
    }
}
