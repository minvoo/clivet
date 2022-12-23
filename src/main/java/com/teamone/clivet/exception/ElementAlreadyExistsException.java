package com.teamone.clivet.exception;

public class ElementAlreadyExistsException extends RuntimeException {

    public ElementAlreadyExistsException(String stringModel) {
        super(stringModel + " already exists in the database");
    }
}
