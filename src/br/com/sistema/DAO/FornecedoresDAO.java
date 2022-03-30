package br.com.sistema.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Stateless;

import br.com.sistema.domain.Fornecedores;
import br.com.sistema.factory.ConexaoFactory;

@Stateless
public class FornecedoresDAO {

	public void salvar(Fornecedores f) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
				
	}

	public void editar(Fornecedores f) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET ");
		sql.append("descricao = ? ");
		sql.append("WHERE ");
		sql.append("codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
				
	}

	public void excluir(Fornecedores f) throws SQLException {
	
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE ");
		sql.append("codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
				
	}	
	
	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores ");
		sql.append("WHERE ");
		sql.append("codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
			
		}
		
		return retorno;
		
	}

	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores ");
		sql.append("WHERE ");
		sql.append("descricao LIKE ?");
		sql.append("ORDER BY descricao ASC ");		
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1,"%" + f.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while(resultado.next()) {
			
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);			
		
		}
		
		return lista;
		
		
	}
	
	
	public ArrayList<Fornecedores> listar() throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM fornecedores ");
		sql.append("ORDER BY codigo ASC ");		

		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);			
		
		}
		
		return lista;
		
	}
	
	public static void main(String[] args) {
		
		Fornecedores f1 = new Fornecedores();
		f1.setDescricao("Fornecedor 1");
		Fornecedores f2 = new Fornecedores();
		f2.setDescricao("Fornecedor 2");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);
			System.out.println("Registros salvos com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar os registros!");
			e.printStackTrace();
		}
		
		Fornecedores f3 = new Fornecedores();
		f3.setCodigo((long) 2);	
		FornecedoresDAO fdao1 = new FornecedoresDAO();	
		try {
			fdao1.excluir(f3);
			System.out.println("Registro excluido com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao excluir o registro!");
			e.printStackTrace();
		}

		Fornecedores f4 = new Fornecedores();
		f4.setCodigo(8L);
		f4.setDescricao("Novo Fornecedor 8");		
		FornecedoresDAO fdao2 = new FornecedoresDAO();		
		try {
			fdao2.editar(f4);
			System.out.println("Registro alterado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao alterar o registro!");
			e.printStackTrace();
		}
		
		Fornecedores f5 = new Fornecedores();
		f5.setCodigo(9L);		
		FornecedoresDAO fdao3 = new FornecedoresDAO();		
		try {
			Fornecedores f6 = fdao3.buscaPorCodigo(f5);
			if(f6 != null) {
				System.out.println("Resultado da busca: " + f6);
				System.out.println("Registro localizado com sucesso!");
			} else {
				System.out.println("Registro não localizado!");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o registro!");
			e.printStackTrace();
		}

		
		FornecedoresDAO fdao4 = new FornecedoresDAO();
		System.out.println("-----------------------");
		System.out.println("Listagem dos Registros:");
		System.out.println("-----------------------");
		try {

			ArrayList<Fornecedores> lista = fdao4.listar();
			
			for(Fornecedores f : lista) {
				System.out.println("Resultado " + f);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar os registros!");
			e.printStackTrace();
		}
		
		Fornecedores f10 = new Fornecedores();
		f10.setDescricao("Nov");
		FornecedoresDAO fdao10 = new FornecedoresDAO();		
		
		try {

			ArrayList<Fornecedores> lista = fdao10.buscarPorDescricao(f10);
			
			for(Fornecedores f : lista) {
				System.out.println("Resultado " + f);
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao listar os registros selecionados por descrição!");
			e.printStackTrace();
		}
		
		
	}
}
