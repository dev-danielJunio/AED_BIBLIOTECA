package com.ead.biblioteca.controllers;
import com.ead.biblioteca.model.Atendente;
import com.ead.biblioteca.service.AtendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biblioteca/atendentes")
@CrossOrigin(origins = "http://localhost:3000")
public class AtendenteController {

    private final AtendenteService atendenteService;

    @Autowired
    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody @Valid Atendente atendente) {
        try {
            atendenteService.incluir(atendente);
            return ResponseEntity.status(201).body("Atendente inserido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao inserir atendente: " + e.getMessage());
        }
    }
}
