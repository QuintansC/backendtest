package com.quintansc.backendtest.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.quintansc.backendtest.entities.Usuario;
import com.quintansc.backendtest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    public String teste;
    @Autowired
    private UserRepository repository;

    @GetMapping(value = "/{id}")
    public Usuario findById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @GetMapping
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Usuario insert(@RequestBody Usuario User) {
        return repository.save(User);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Usuario User) {
        List<Usuario> result = repository.findAll();

        for (Usuario usuario : result) {
            if ((usuario.getNome().equals(User.getNome()) || usuario.getEmail().equals(User.getEmail())) && usuario.getSenha().equals(User.getSenha())) {
                return "Logado com sucesso";
            }
        }

        return "Email, senha ou Usuario errados";
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Object> Cadastro(@RequestBody Usuario User) {
        List<Usuario> result = repository.findAll();
        List<String> messages = new ArrayList<>();

        if (User.getNome().isEmpty() || User.getEmail().isEmpty() || User.getSenha().isEmpty()) {
            messages.add("Não pode haver campos vazios");
        } else {
            boolean usuarioExistente = false;
            for (Usuario usuario : result) {
                if (usuario.getNome().equals(User.getNome()) || usuario.getEmail().equals(User.getEmail())) {
                    usuarioExistente = true;
                    break;
                }
            }

            if (usuarioExistente) {
                messages.add("Email ou Usuário já existem");
            } else {
                repository.save(User);
                messages.add("Usuário cadastrado com sucesso");
            }
        }

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
