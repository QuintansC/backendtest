package com.quintansc.backendtest.controllers;

import com.quintansc.backendtest.entities.Usuario;
import com.quintansc.backendtest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;
    @GetMapping(value = "/{id}")
    public Usuario findById(@PathVariable Long id){
        return repository.findById(id).get();
    }
    @GetMapping
    public List<Usuario> findAll(){
        return repository.findAll();
    }
    @PostMapping
    public Usuario insert(@RequestBody Usuario User){
        return repository.save(User);
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Usuario User){
        List<Usuario> result = repository.findAll();

        for (Usuario usuario : result) {
            if ((usuario.getNome().equals(User.getNome()) ||  usuario.getEmail().equals(User.getEmail()))  && usuario.getSenha().equals(User.getSenha())) {
                return "Logado com sucesso";
            }
        }

        return "Email, senha ou Usuario errados";
    }
}
