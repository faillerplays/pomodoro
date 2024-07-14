package com.pomodoro.pomodoro.service;

import com.pomodoro.pomodoro.exception.SessaoEstudoNaoEncontradaException;
import com.pomodoro.pomodoro.exception.UsuarioNaoEncontradoException;
import com.pomodoro.pomodoro.model.SessaoEstudo;
import com.pomodoro.pomodoro.model.Usuario;
import com.pomodoro.pomodoro.repository.SessaoEstudoRepository;
import com.pomodoro.pomodoro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SessaoEstudoService {

    private final SessaoEstudoRepository sessaoEstudoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public SessaoEstudoService(SessaoEstudoRepository sessaoEstudoRepository, UsuarioRepository usuarioRepository) {
        this.sessaoEstudoRepository = sessaoEstudoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public SessaoEstudo criarSessaoEstudo(SessaoEstudo sessaoEstudo) {
        return sessaoEstudoRepository.save(sessaoEstudo);
    }

    public SessaoEstudo buscarSessaoEstudoPorId(Long id) {
        return sessaoEstudoRepository.findById(id)
                .orElseThrow(() -> new SessaoEstudoNaoEncontradaException(id));
    }

    public List<SessaoEstudo> listarSessoesEstudoPorUsuario(Long usuarioId) {
        return sessaoEstudoRepository.findByUsuarioId(usuarioId);
    }

    public SessaoEstudo atualizarSessaoEstudo(Long id, SessaoEstudo sessaoAtualizada) {
        SessaoEstudo sessaoExistente = sessaoEstudoRepository.findById(id)
                .orElseThrow(() -> new SessaoEstudoNaoEncontradaException(id));

        // Atualiza os campos da sessão existente com os valores da sessão atualizada
        sessaoExistente.setCategoria(sessaoAtualizada.getCategoria());
        sessaoExistente.setInicio(sessaoAtualizada.getInicio());
        sessaoExistente.setFim(sessaoAtualizada.getFim());

        return sessaoEstudoRepository.save(sessaoExistente);
    }

    public void excluirSessaoEstudo(Long id) {
        sessaoEstudoRepository.deleteById(id);
    }

    public SessaoEstudo iniciarSessao(Long usuarioId, String categoria) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        SessaoEstudo novaSessao = new SessaoEstudo();
        novaSessao.setUsuario(usuario);
        novaSessao.setCategoria(categoria);
        novaSessao.setInicio(LocalDateTime.now());
        // Defina o status como EM_ANDAMENTO se você estiver usando a enumeração StatusSessao

        return sessaoEstudoRepository.save(novaSessao);
    }

    public SessaoEstudo finalizarSessao(Long id) {
        SessaoEstudo sessao = sessaoEstudoRepository.findById(id)
                .orElseThrow(() -> new SessaoEstudoNaoEncontradaException(id));

        sessao.setFim(LocalDateTime.now());
        // Calcule a duração da sessão aqui se você tiver um campo 'duracao'
        // Defina o status como CONCLUIDA se você estiver usando a enumeração StatusSessao

        return sessaoEstudoRepository.save(sessao);
    }
}
