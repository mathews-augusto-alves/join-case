package br.com.app.app_control.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

public class PedidoControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    void getPedidosByUsuario_SemAutenticacao_DeveRetornar401() throws Exception {
        mockMvc.perform(get("/private/pedido-by-usuario/teste@teste.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "outro@teste.com", roles = "CLIENTE")
    void getPedidosByUsuario_UsuarioDiferente_DeveRetornar403() throws Exception {
        mockMvc.perform(get("/private/pedido-by-usuario/teste@teste.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
} 