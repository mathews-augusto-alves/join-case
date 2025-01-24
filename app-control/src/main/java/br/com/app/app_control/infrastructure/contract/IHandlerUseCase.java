package br.com.app.app_control.infrastructure.contract;

import org.springframework.http.ResponseEntity;

import br.com.app.app_control.infrastructure.record.ResponseRecord;

public interface IHandlerUseCase<I extends IExecution<P>, P> {
    ResponseEntity<ResponseRecord> handle(I execution);
    ResponseEntity<ResponseRecord> handle(I execution, P param);
}
