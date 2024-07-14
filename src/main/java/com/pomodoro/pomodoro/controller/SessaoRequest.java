package com.pomodoro.pomodoro.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoRequest {
    private Long usuarioId;
    private String categoria;
}