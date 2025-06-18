package com.ead.biblioteca.controllers;

import com.ead.biblioteca.model.UnidadeAtendimento;
import com.ead.biblioteca.service.UnidadeAtendimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@RestController
@RequestMapping("/biblioteca/unidades")
public class UnidadeAtendimentoController {

    private final UnidadeAtendimentoService unidadeService;

    @Autowired
    public UnidadeAtendimentoController(UnidadeAtendimentoService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<?> listarTodas() {
        try {
            List<UnidadeAtendimento> lista = unidadeService.listarTodasUnidadeAtendimento();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao listar unidades: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody @Valid UnidadeAtendimento unidadeAtendimento) {
        try {
            UnidadeAtendimento novaUnidade = unidadeService.incluir(unidadeAtendimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaUnidade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar unidade: " + e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> alterar(@PathVariable int codigo, @RequestBody @Valid UnidadeAtendimento unidadeAtendimento) {
        try {
            unidadeAtendimento.setCodigo(codigo);
            UnidadeAtendimento atualizada = unidadeService.alterar(unidadeAtendimento);
            return ResponseEntity.ok(atualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao alterar unidade: " + e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> excluir(@PathVariable int codigo) {
        try {
            unidadeService.excluir(codigo);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao excluir unidade: " + e.getMessage());
        }
    }
}