package br.com.app.app_control.infrastructure.adapter;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.contract.IHandlerUseCase;
import br.com.app.app_control.infrastructure.record.ResponseRecord;

@Component
public class UseCaseHandler<E extends IExecution<I>, I> implements IHandlerUseCase<E, I> {

    private static final String GENERIC_ERROR_MESSAGE = "Não foi possível concluir a requisição.";

    @Override
    public ResponseEntity<ResponseRecord> handle(E execution) {
        try {
            ResponseRecord output = execution.execute();
            return ResponseEntity.ok(output);
        } catch (Exception exception) {
            exception.printStackTrace();
            return createResponseError(exception);
        }
    }

    @Override
    public ResponseEntity<ResponseRecord> handle(E execution, I input) {
        try {
            ResponseRecord output = execution.execute(input);
            return ResponseEntity.ok(output);
        } catch (Exception exception) {
            exception.printStackTrace();
            return createResponseError(exception);
        }
    }

    private ResponseEntity<ResponseRecord> createResponseError(Exception exception) {
        String errorMessage = exception.getMessage() != null ? exception.getMessage() : "Erro genérico.";
        return ResponseEntity.badRequest().body(new ResponseRecord(GENERIC_ERROR_MESSAGE, List.of(errorMessage))); 
    }
}
