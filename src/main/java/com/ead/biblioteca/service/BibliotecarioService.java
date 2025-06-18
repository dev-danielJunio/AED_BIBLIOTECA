package com.ead.biblioteca.service;

import com.ead.biblioteca.dao.BibliotecarioDAO;
import com.ead.biblioteca.model.Bibliotecario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliotecarioService {

    private final BibliotecarioDAO bibliotecarioDAO;

    @Autowired
    public BibliotecarioService(BibliotecarioDAO bibliotecarioDAO) {
        this.bibliotecarioDAO = bibliotecarioDAO;
    }

    public void incluir(Bibliotecario bibliotecario) {
        bibliotecarioDAO.inserirBibliotecaria(bibliotecario);

    }
}
