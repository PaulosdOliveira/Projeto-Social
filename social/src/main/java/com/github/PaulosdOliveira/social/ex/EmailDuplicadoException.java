package com.github.PaulosdOliveira.social.ex;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException() {
        super("Este email já está sendo usado");
    }
}
