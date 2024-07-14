package com.pomodoro.pomodoro.exception;

public class SessaoEstudoNaoEncontradaException extends RecursoNaoEncontradoException {
    public SessaoEstudoNaoEncontradaException(Long id) {
        super("Sessão de estudo não encontrada com ID: " + id);
    }
}