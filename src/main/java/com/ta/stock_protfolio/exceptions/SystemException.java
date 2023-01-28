package com.ta.stock_protfolio.exceptions;

public class SystemException extends Exception {
    public SystemException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
