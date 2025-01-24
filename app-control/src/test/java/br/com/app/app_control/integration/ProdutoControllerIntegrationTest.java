package br.com.app.app_control.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import br.com.app.app_control.domain.dto.ProdutoFilterDTO;
import br.com.app.app_control.infrastructure.dto.PaginationRequestDTO;

public class ProdutoControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void getAllProdutos_DeveRetornarPaginaDeProdutos() throws Exception {
        PaginationRequestDTO request = new PaginationRequestDTO();
        request.setPage(0);
        request.setSize(10);

        mockMvc.perform(post("/public/produtos/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.content").isArray());
    }

    @Test
    void getProdutosByCategoria_DeveRetornarProdutosDaCategoria() throws Exception {
        ProdutoFilterDTO request = new ProdutoFilterDTO();
        request.setPage(0);
        request.setSize(10);
        request.setCategoriaId(1L);

        mockMvc.perform(post("/public/produtos-by-categorias/all")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.content").isArray());
    }
} 