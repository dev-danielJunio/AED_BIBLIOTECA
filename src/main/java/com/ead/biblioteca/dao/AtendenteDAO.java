package com.ead.biblioteca.dao;

import com.ead.biblioteca.model.Atendente;
import com.ead.biblioteca.repository.ConexaoBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class AtendenteDAO {

    private final ConexaoBanco conexaoBanco;

    @Autowired
    public AtendenteDAO(ConexaoBanco conexaoBanco) {
        this.conexaoBanco = conexaoBanco;
    }

    public void inserirAtendente(Atendente atendente) {
        String sql = "INSERT INTO biblioteca.tab_atendentes (matricula, nome, unidade_atendimento) VALUES (?, ?, ?)";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, atendente.getMatricula());
            stmt.setString(2, atendente.getNome());
            stmt.setInt(3, atendente.getUnidadeAtendimento());

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir atendente: " + e.getMessage());
        }
    }
}
