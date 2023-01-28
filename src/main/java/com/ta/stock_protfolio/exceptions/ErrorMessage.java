package com.ta.stock_protfolio.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    EMAIL_ALREADY_EXISTS("Email already exists"),
    EMAIL_NOT_FOUND("Email not found"),
    ID_NOT_EXISTS("Id not exists"),
    NOT_ENOUGH_STOCKS_TO_SELL("There is not enough stocks to sell!");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
