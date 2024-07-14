package com.pomodoro.pomodoro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pomodoro.pomodoro.model.SessaoEstudo;
import java.util.List;

public interface SessaoEstudoRepository extends JpaRepository<SessaoEstudo, Long> {
    List<SessaoEstudo> findByUsuarioId(Long usuarioId);
}
