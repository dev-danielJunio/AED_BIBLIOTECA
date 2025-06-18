package com.ead.biblioteca.service;

import com.ead.biblioteca.dao.UnidadeAtendimentoDAO;
import com.ead.biblioteca.model.UnidadeAtendimento;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UnidadeAtendimentoService {

    private final UnidadeAtendimentoDAO unidadeAtendimentoDAO;


    public UnidadeAtendimentoService(UnidadeAtendimentoDAO unidadeDAO) {
        this.unidadeAtendimentoDAO = unidadeDAO;
    }

    public List<UnidadeAtendimento> listarTodasUnidadeAtendimento() throws SQLException {
        return  unidadeAtendimentoDAO.listaUnidadeAtendimento();
    }

    public UnidadeAtendimento incluir(UnidadeAtendimento unidadeAtendimento) throws Exception {
        unidadeAtendimentoDAO.inserirUnidadeAtendimento(unidadeAtendimento);
        return unidadeAtendimento;
    }

    public UnidadeAtendimento alterar(UnidadeAtendimento unidadeAtendimento) throws SQLException {
        unidadeAtendimentoDAO.atualizarUnidadeAtendimento(unidadeAtendimento);
        return unidadeAtendimento;
    }

    public void excluir(int codigo) throws SQLException {
        unidadeAtendimentoDAO.deletarUnidadeAtendimento(codigo);
    }


}
