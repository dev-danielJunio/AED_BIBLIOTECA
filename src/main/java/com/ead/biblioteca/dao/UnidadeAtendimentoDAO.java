package com.ead.biblioteca.dao;

import com.ead.biblioteca.repository.ConexaoBanco;
import com.ead.biblioteca.model.UnidadeAtendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UnidadeAtendimentoDAO {

    private final ConexaoBanco conexaoBanco;

    @Autowired
    public UnidadeAtendimentoDAO(ConexaoBanco conexaoBanco) {
        this.conexaoBanco = conexaoBanco;
    }

    public List<UnidadeAtendimento> listaUnidadeAtendimento() throws SQLException {
        List<UnidadeAtendimento> lista = new ArrayList<>();

        String sql = "SELECT u.codigo, u.nome AS nome_unidade, u.endereco, u.telefone, u.bibliotecaria_responsavel AS matricula_bibliotecaria, b.nome AS nome_bibliotecaria " +
                "FROM biblioteca.tab_unidades_atendimento u " +
                "JOIN biblioteca.tab_bibliotecarias b ON u.bibliotecaria_responsavel = b.matricula";

        try(Connection conn = conexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                UnidadeAtendimento unidadeAtendimento = new UnidadeAtendimento();
                unidadeAtendimento.setCodigo(rs.getInt("codigo"));
                unidadeAtendimento.setNomeUnidade(rs.getString("nome_unidade"));
                unidadeAtendimento.setEndereco(rs.getString("endereco"));
                unidadeAtendimento.setTelefone(rs.getLong("telefone"));
                unidadeAtendimento.setBibliotecaria_responsavel(rs.getInt("matricula_bibliotecaria"));
                unidadeAtendimento.setNome_bibliotecaria(rs.getString("nome_bibliotecaria"));
                lista.add(unidadeAtendimento);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    public void inserirUnidadeAtendimento(UnidadeAtendimento unidade) throws Exception {
        String sql = "INSERT INTO biblioteca.tab_unidades_atendimento (codigo, nome, endereco, telefone, bibliotecaria_responsavel) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, unidade.getCodigo());
            stmt.setString(2, unidade.getNomeUnidade());
            stmt.setString(3, unidade.getEndereco());
            stmt.setLong(4, unidade.getTelefone());
            stmt.setInt(5, unidade.getBibliotecaria_responsavel());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public void atualizarUnidadeAtendimento(UnidadeAtendimento unidade) throws SQLException {
        String sql = "UPDATE biblioteca.tab_unidades_atendimento SET nome = ?, endereco = ?, telefone = ?, bibliotecaria_responsavel = ? WHERE codigo = ?";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, unidade.getNomeUnidade());
            stmt.setString(2, unidade.getEndereco());
            stmt.setLong(3, unidade.getTelefone());
            stmt.setInt(4, unidade.getBibliotecaria_responsavel());
            stmt.setInt(5, unidade.getCodigo());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deletarUnidadeAtendimento(int codigo) throws SQLException {
        String sql = "DELETE FROM biblioteca.tab_unidades_atendimento WHERE codigo = ?";

        try (Connection conn = conexaoBanco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
