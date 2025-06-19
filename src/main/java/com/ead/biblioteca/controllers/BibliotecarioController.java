package com.ead.biblioteca.controllers;

import com.ead.biblioteca.model.Bibliotecario;
import com.ead.biblioteca.service.BibliotecarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@Validated
@RestController
@RequestMapping("/biblioteca/bibliotecarios")
public class BibliotecarioController {

    private final BibliotecarioService bibliotecarioService;

    @Autowired
    public BibliotecarioController(BibliotecarioService bibliotecarioService) {
        this.bibliotecarioService = bibliotecarioService;
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Bibliotecario> lista = bibliotecarioService.listarTodosBibliotecarios();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao listar bibliotecários: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody @Valid Bibliotecario bibliotecario) {
        try {
            Bibliotecario novoBibliotecario = bibliotecarioService.incluir(bibliotecario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoBibliotecario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar bibliotecário: " + e.getMessage());
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<?> alterar(@PathVariable int matricula, @RequestBody @Valid Bibliotecario bibliotecario) {
        try {
            bibliotecario.setMatricula(matricula);
            Bibliotecario atualizado = bibliotecarioService.alterar(bibliotecario);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao alterar bibliotecário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<?> excluir(@PathVariable int matricula) {
        try {
            bibliotecarioService.excluir(matricula);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao excluir bibliotecário: " + e.getMessage());
        }
    }
}