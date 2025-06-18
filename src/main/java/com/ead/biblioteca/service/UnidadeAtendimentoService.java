package com.ead.biblioteca.service;

import com.ead.biblioteca.dao.UnidadeAtendimentoDAO;
import com.ead.biblioteca.model.UnidadeAtendimento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeAtendimentoService {

    private final UnidadeAtendimentoDAO unidadeAtendimentoDAO;


    public UnidadeAtendimentoService(UnidadeAtendimentoDAO unidadeDAO) {
        this.unidadeAtendimentoDAO = unidadeDAO;
    }

    public List<UnidadeAtendimento> listarTodasUnidadeAtendimento() {
        return  unidadeAtendimentoDAO.listaUnidadeAtendimento();
    }

    public UnidadeAtendimento incluir(UnidadeAtendimento unidadeAtendimento) {
        unidadeAtendimentoDAO.inserirUnidadeAtendimento(unidadeAtendimento); // método que insere no banco
        return unidadeAtendimento;
    }

    public UnidadeAtendimento alterar(UnidadeAtendimento unidadeAtendimento) {
        unidadeAtendimentoDAO.atualizarUnidadeAtendimento(unidadeAtendimento);
        return unidadeAtendimento;
    }

    public void excluir(int codigo) {
        unidadeAtendimentoDAO.deletarUnidadeAtendimento(codigo);
    }


}
