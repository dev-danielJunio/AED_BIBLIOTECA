package com.ead.biblioteca.dao;

import com.ead.biblioteca.model.Bibliotecario;
import com.ead.biblioteca.repository.ConexaoBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BibliotecarioDAO {

    private final ConexaoBanco conexaoBanco;

    @Autowired
    public BibliotecarioDAO(ConexaoBanco conexaoBanco) {
        this.conexaoBanco = conexaoBanco;
    }

    public List<Bibliotecario> listaBibliotecarios() throws SQLException {
        List<Bibliotecario> lista = new ArrayList<>();

        String sql = "SELECT matricula, nome FROM biblioteca.tab_bibliotecarias";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bibliotecario bibliotecario = new Bibliotecario();
                bibliotecario.setMatricula(rs.getInt("matricula"));
                bibliotecario.setNome(rs.getString("nome"));
                lista.add(bibliotecario);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    public void inserirBibliotecaria(Bibliotecario bibliotecario) throws Exception {
        String sql = "INSERT INTO biblioteca.tab_bibliotecarias (matricula, nome) VALUES (?, ?)";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bibliotecario.getMatricula());
            stmt.setString(2, bibliotecario.getNome());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void atualizarBibliotecario(Bibliotecario bibliotecario) throws SQLException {
        String sql = "UPDATE biblioteca.tab_bibliotecarias SET nome = ? WHERE matricula = ?";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bibliotecario.getNome());
            stmt.setInt(2, bibliotecario.getMatricula());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deletarBibliotecario(int matricula) throws SQLException {
        String sql = "DELETE FROM biblioteca.tab_bibliotecarias WHERE matricula = ?";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}