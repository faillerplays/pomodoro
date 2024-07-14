package com.pomodoro.pomodoro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pomodoro.pomodoro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}