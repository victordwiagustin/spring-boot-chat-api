package com.ggd.ggdchatapi.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggd.ggdchatapi.config.JacksonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    @Autowired
    private JacksonConfiguration jacksonConfiguration = new JacksonConfiguration();

    protected ObjectMapper objectMapper = jacksonConfiguration.objectMapper();

    protected <T> Map<String, Object> resposeResult(boolean result, T var) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        if (var != null) {
            response.put("payload", var);
        }
        return response;
    }

    protected ResponseEntity<Map<String, Object>> responseOk() {
        return new ResponseEntity<>(resposeResult(Boolean.TRUE, null), HttpStatus.OK);
    }

    protected <T> ResponseEntity<Map<String, Object>> responseOk(T var) {
        return new ResponseEntity<>(resposeResult(Boolean.TRUE, var), HttpStatus.OK);
    }

    protected ResponseEntity<Map<String, Object>> responseError() {
        return new ResponseEntity<>(resposeResult(Boolean.FALSE, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
