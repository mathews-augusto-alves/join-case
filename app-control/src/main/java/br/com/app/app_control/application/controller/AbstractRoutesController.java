package br.com.app.app_control.application.controller;

import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.contract.IHandleRoute;

public abstract class AbstractRoutesController<U extends IExecution<C>, C> implements IHandleRoute<C> {

    protected final UseCaseHandler<U, C> useCaseHandler;

    public AbstractRoutesController(final UseCaseHandler<U, C> useCaseHandler) {
        this.useCaseHandler = useCaseHandler;
    }
}
