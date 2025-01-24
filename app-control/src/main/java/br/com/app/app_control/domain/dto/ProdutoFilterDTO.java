package br.com.app.app_control.domain.dto;

import br.com.app.app_control.infrastructure.dto.PaginationRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFilterDTO extends PaginationRequestDTO{

    private Long categoriaId;
    private Long usuarioId;
    private Long id;
}
