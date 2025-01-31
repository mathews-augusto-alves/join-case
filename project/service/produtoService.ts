import { PaginacaoResponse, Produto, ProdutoFilter } from '@/lib/types';
import CustomException from '@/lib/exception/CustomException';

const API_URL = process.env.NEXT_PUBLIC_API_URL;
const ENDPOINTS = {
    ALL_PRODUTOS: `${API_URL}/app-control-api/public/produtos/all`,
    PRODUTOS_BY_CATEGORIA: `${API_URL}/app-control-api/public/produtos-by-categorias/all`, 
    PRODUTO_BY_ID: `${API_URL}/app-control-api/public/produtos-by-id`
};

const headers = {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
};

async function fetchProdutoData<T>(url: string, data: ProdutoFilter): Promise<T> {
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers,
            body: JSON.stringify(data),
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new CustomException(
                errorData.title || 'Erro ao buscar produtos',
                Array.isArray(errorData.errors) ? errorData.errors : ['Erro desconhecido']
            );
        }

        const { data: responseData } = await response.json();
        return responseData;
    } catch (error) {
        if (error instanceof CustomException) {
            throw error;
        }
        throw new CustomException(
            'Erro ao buscar produtos',
            ['Ocorreu um erro ao buscar os produtos. Tente novamente em instantes.']
        );
    }
}

export default async function getAllProduto(paginacao: ProdutoFilter): Promise<PaginacaoResponse<Produto>> {
    return fetchProdutoData<PaginacaoResponse<Produto>>(ENDPOINTS.ALL_PRODUTOS, paginacao);
}

export async function getAllProdutoPorCategoria(paginacao: ProdutoFilter): Promise<PaginacaoResponse<Produto>> {
    return fetchProdutoData<PaginacaoResponse<Produto>>(ENDPOINTS.PRODUTOS_BY_CATEGORIA, paginacao);
}

export async function getProdutoPorId(paginacao: ProdutoFilter): Promise<Produto> {
    return fetchProdutoData<Produto>(ENDPOINTS.PRODUTO_BY_ID, paginacao);
}
