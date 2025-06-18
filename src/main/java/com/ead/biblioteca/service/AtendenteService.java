package com.ead.biblioteca.service;

import com.ead.biblioteca.dao.AtendenteDAO;
import com.ead.biblioteca.model.Atendente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendenteService {

    private final AtendenteDAO atendenteDAO;

    @Autowired
    public AtendenteService(AtendenteDAO atendenteDAO) {
        this.atendenteDAO = atendenteDAO;
    }

    public void incluir(Atendente atendente) {
        atendenteDAO.inserirAtendente(atendente);
    }
}
