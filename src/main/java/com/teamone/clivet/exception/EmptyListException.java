package com.teamone.clivet.exception;

public class EmptyListException extends Exception {

    public EmptyListException(String modelName) {
        super("Nothing found. The list of " + modelName + " is empty.");
    }
}
