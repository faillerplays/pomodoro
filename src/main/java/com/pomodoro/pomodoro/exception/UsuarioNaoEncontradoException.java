package com.pomodoro.pomodoro.exception;

public class UsuarioNaoEncontradoException extends RecursoNaoEncontradoException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuário não encontrado com ID: " + id);
    }
}