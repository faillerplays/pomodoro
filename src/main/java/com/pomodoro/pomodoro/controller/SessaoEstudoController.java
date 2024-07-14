package com.pomodoro.pomodoro.controller;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import com.pomodoro.pomodoro.model.SessaoEstudo;
import com.pomodoro.pomodoro.model.Usuario;
import com.pomodoro.pomodoro.repository.SessaoEstudoRepository;
import com.pomodoro.pomodoro.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/sessoes")
public class SessaoEstudoController {
    private final SessaoEstudoRepository sessaoEstudoRepository;
    private final UsuarioRepository usuarioRepository;

    public SessaoEstudoController(SessaoEstudoRepository sessaoEstudoRepository, UsuarioRepository usuarioRepository) {
        this.sessaoEstudoRepository = sessaoEstudoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<SessaoEstudo> getSessoes(@RequestParam Long usuarioId) {
        return sessaoEstudoRepository.findByUsuarioId(usuarioId);
    }

    @PostMapping("/iniciar")
    public SessaoEstudo iniciarSessao(@RequestBody SessaoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        SessaoEstudo sessao = new SessaoEstudo();
        sessao.setCategoria(request.getCategoria());
        sessao.setInicio(LocalDateTime.now());
        sessao.setUsuario(usuario);
        return sessaoEstudoRepository.save(sessao);
    }

    @PostMapping("/parar/{id}")
    public SessaoEstudo pararSessao(@PathVariable Long id) {
        SessaoEstudo sessao = sessaoEstudoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        sessao.setFim(LocalDateTime.now());
        return sessaoEstudoRepository.save(sessao);
    }
}
