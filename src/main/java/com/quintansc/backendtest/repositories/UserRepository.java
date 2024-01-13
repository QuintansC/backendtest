package com.quintansc.backendtest.repositories;

import com.quintansc.backendtest.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> { }
