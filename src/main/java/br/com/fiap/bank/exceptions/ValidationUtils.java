package br.com.fiap.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationUtils {

    public static void validate(boolean condition, HttpStatus status, String message) {
        if (condition) {
            throw new ResponseStatusException(status, message);
        }
    }
}