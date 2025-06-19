package com.ead.biblioteca.service;

import com.ead.biblioteca.dao.BibliotecarioDAO;
import com.ead.biblioteca.model.Bibliotecario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BibliotecarioService {

    private final BibliotecarioDAO bibliotecarioDAO;

    @Autowired
    public BibliotecarioService(BibliotecarioDAO bibliotecarioDAO) {
        this.bibliotecarioDAO = bibliotecarioDAO;
    }

    public List<Bibliotecario> listarTodosBibliotecarios() throws SQLException {
        return bibliotecarioDAO.listaBibliotecarios();
    }

    public Bibliotecario incluir(Bibliotecario bibliotecario) throws Exception {
        bibliotecarioDAO.inserirBibliotecaria(bibliotecario);
        return bibliotecario;
    }

    public Bibliotecario alterar(Bibliotecario bibliotecario) throws SQLException {
        bibliotecarioDAO.atualizarBibliotecario(bibliotecario);
        return bibliotecario;
    }

    public void excluir(int matricula) throws SQLException {
        bibliotecarioDAO.deletarBibliotecario(matricula);
    }
}