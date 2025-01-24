package br.com.project.core.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntregaDTO {

    private Long id;

    private PedidoDTO pedido;

    private EnderecoDTO endereco;

    private String statusEntrega;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}
