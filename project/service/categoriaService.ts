import { Categorias } from '@/lib/types';
import CustomException from '@/lib/exception/CustomException';

const API_URL = process.env.NEXT_PUBLIC_API_URL;
const CATEGORIAS_ENDPOINT = `${API_URL}/app-control-api/public/categorias/all`;

export default async function getAllCategorias(): Promise<Categorias[]> {
    console.log(process.env.NEXT_PUBLIC_API_URL)
    try {
        const response = await fetch(CATEGORIAS_ENDPOINT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new CustomException(
                errorData.title || 'Erro ao buscar categorias',
                Array.isArray(errorData.errors) ? errorData.errors : ['Erro desconhecido']
            );
        }

        const { data } = await response.json();
        return data;
    } catch (error) {
        if (error instanceof CustomException) {
            throw error;
        }
        console.error('Erro ao buscar categorias:', error);
        throw new CustomException(
            'Erro ao buscar categorias',
            ['Ocorreu um erro ao comunicar com o servidor. Tente novamente em instantes.']
        );
    }
}
