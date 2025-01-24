package br.com.app.app_control.infrastructure.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaginationRequestDTO {

    @NotNull(message = "O campo 'page' é obrigatório.")
    @Min(value = 0, message = "O valor de 'page' deve ser maior ou igual a 0.")
    private int page;

    @NotNull(message = "O campo 'size' é obrigatório.")
    @Min(value = 1, message = "O valor de 'size' deve ser maior ou igual a 1.")
    private int size;

    @NotBlank(message = "O campo 'sortBy' é obrigatório.")
    private String sortBy = "id";
    
    private String direction = "ASC";

    public Pageable toPageable() {
        Sort sort = direction.equalsIgnoreCase("DESC")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();
        return PageRequest.of(page, size, sort);
    }
}
