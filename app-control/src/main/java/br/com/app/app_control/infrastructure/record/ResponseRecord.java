package br.com.app.app_control.infrastructure.record;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ResponseRecord(Object data, List<Object> dataList, String message, String title, List<String> errors) {
    
    public ResponseRecord(Object data) {
        this(data, null, null, null, null);
    }

    public ResponseRecord(List<Object> dataList) {
        this(null, dataList, null, null, null);
    }

    public ResponseRecord(String title, String message) {
        this(null, null, message, title, null);
    }

    public ResponseRecord(Object data, String message) {
        this(data, null, message, null, null);
    }

    public ResponseRecord(String title, List<String> errors) {
        this(null, null, null, title, errors);
    }
}
