package com.gavin.rinnepay.common.exception;

import com.gavin.rinnepay.common.enums.BusinessType;
import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException {
    private final String errorCode;

    public DuplicateResourceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
