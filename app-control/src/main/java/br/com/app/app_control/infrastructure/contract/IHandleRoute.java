package br.com.app.app_control.infrastructure.contract;

import org.springframework.http.ResponseEntity;

import br.com.app.app_control.infrastructure.record.ResponseRecord;

public interface IHandleRoute<I> {
    default ResponseEntity<ResponseRecord> handleRoute() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    default ResponseEntity<ResponseRecord> handleRoute(I input) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
