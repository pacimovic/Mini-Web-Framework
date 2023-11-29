package org.example.api.request.exceptions;

public class RequestNotValidException extends Exception {

    public RequestNotValidException(String command) {
        super("Client request is invalid. Requested command: " + command);
    }

}
