package br.com.project.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusPedidoDTO {
    
    private Long id;

    private String nome;

    private String descricao;
}
