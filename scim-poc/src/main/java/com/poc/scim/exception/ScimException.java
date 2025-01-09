package com.poc.scim.exception;

import com.poc.scim.dto.ErrorRecord;
import lombok.Getter;

@Getter
public class ScimException extends RuntimeException {

    private ErrorRecord error;

    public ScimException(ErrorRecord error) {
        this(error.toString());
        this.error = error;
    }

    public ScimException(String message) {
        super(message);
    }
}
