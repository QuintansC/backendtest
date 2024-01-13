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
        Usuario result = repository.findById(id).get();
        return result;
    }
    @GetMapping
    public List<Usuario> findAll(){
        List<Usuario> result = repository.findAll();
        return result;
    }

    @PostMapping
    public Usuario insert(@RequestBody Usuario User){
        Usuario result = repository.save(User);
        return result;
    }
}
