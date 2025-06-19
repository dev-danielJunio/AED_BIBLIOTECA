package com.ead.biblioteca.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeAtendimento {
    private int codigo;
    private String nomeUnidade;
    private String endereco;
    private long telefone;
    private int bibliotecaria_responsavel;
    private String nome_bibliotecaria;
}
