package com.ead.biblioteca.controllers;

import com.ead.biblioteca.model.Bibliotecario;
import com.ead.biblioteca.service.BibliotecarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca/bibliotecarios")
@CrossOrigin(origins = "http://localhost:3000")
public class BibliotecarioController {

    private final BibliotecarioService bibliotecarioService;

    @Autowired
    public BibliotecarioController(BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody @Valid Bibliotecario bibliotecario) {
        try {
            bibliotecarioService.incluir(bibliotecario);
            return ResponseEntity.status(201).body("Feito com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar bibliotecário: " + e.getMessage());
        }
    }
}
