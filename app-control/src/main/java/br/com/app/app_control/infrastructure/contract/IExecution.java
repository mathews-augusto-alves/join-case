package br.com.app.app_control.infrastructure.contract;

import br.com.app.app_control.infrastructure.record.ResponseRecord;

public interface IExecution<I> {
    default ResponseRecord execute(){
        throw new UnsupportedOperationException("Method not implemented");
    }
    
    default ResponseRecord execute(I input) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
