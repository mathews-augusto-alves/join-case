import { Pedido, SucessoResponse } from '@/lib/types';
import CustomException from '@/lib/exception/CustomException';

const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8085';
const ENVIAR_PEDIDO_ENDPOINT = `${API_URL}/app-control-api/private/create/pedido`;
const BUSCAR_PEDIDO_ENDPOINT = `${API_URL}/app-control-api/private/pedido-by-usuario`;

const getJwtToken = (): string => {
    if (typeof window !== "undefined") {
        const jwt = localStorage.getItem('jwt');
        if (!jwt) {
            throw new CustomException('Erro de autenticação', ['Token JWT não encontrado.']);
        }
        return jwt;
    }
    return "";
};



export default async function enviarPedido(pedido: Pedido): Promise<SucessoResponse> {
    const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + getJwtToken()
    };
    try {
        const response = await fetch(ENVIAR_PEDIDO_ENDPOINT, {
            method: 'POST',
            headers,
            body: JSON.stringify(pedido),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new CustomException(
                errorData.title || 'Erro ao tentar finalizar pedido',
                Array.isArray(errorData.errors) ? errorData.errors : ['Erro desconhecido']
            );
        }

        const dataToJson = await response.json();
        return { message: dataToJson.message, title: dataToJson.title };
    } catch (error) {
        if (error instanceof CustomException) {
            throw error;
        }
        throw new CustomException(
            'Erro ao tentar finalizar pedido',
            ['Por favor, tente novamente.']
        );
    }
}

export async function getPedidoPorUsuario(email: string): Promise<Pedido[]> {
    const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + getJwtToken()
    };
    try {
        const response = await fetch(`${BUSCAR_PEDIDO_ENDPOINT}/${email}`, {
            method: 'GET',
            headers,
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new CustomException(
                errorData.title || 'Erro ao buscar pedidos',
                Array.isArray(errorData.errors) ? errorData.errors : ['Erro desconhecido']
            );
        }

        const { data } = await response.json();
        return data;
    } catch (error) {
        if (error instanceof CustomException) {
            throw error;
        }
        throw new CustomException(
            'Erro ao buscar pedidos',
            ['Ocorreu um erro ao buscar os pedidos. Por favor, tente novamente.']
        );
    }
}