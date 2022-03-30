package br.com.sistema.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.sistema.DAO.ProdutoDAO;
import br.com.sistema.domain.Fornecedores;
import br.com.sistema.domain.Produtos;

public class ProdutoDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produtos p1 = new Produtos();
		p1.setDescricao("NOVALGINA");
		p1.setPreco(5.99);
		p1.setQuantidade(23L); //O L é porque o parâmetro é do tipo Long

		Fornecedores f = new Fornecedores();
		f.setCodigo(51L); //O L é porque o parâmetro é do tipo Long
		p1.setFornecedores(f); 

		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(p1);
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		
		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produtos> lista = pdao.listar();	
		
		for (Produtos p : lista) {
			System.out.println("Código do Produto: " + p.getCodigo());
			System.out.println("Descrição do Produto: " + p.getDescricao());
			System.out.println("Preço do Produto: " + p.getPreco());
			System.out.println("Quantidade do Produto: " + p.getQuantidade());
			System.out.println("Código do Fornecedor: " + p.getFornecedores().getCodigo());
			System.out.println("Descrição do Fornecedor: " + p.getFornecedores().getDescricao());
			System.out.println("");
		}
		
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException {

		Produtos p = new Produtos();
		p.setCodigo(3L);
		
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.excluir(p);
		
	}

	@Test
	public void editar() throws SQLException {

		Produtos p = new Produtos();
		p.setCodigo(1L);
		p.setDescricao("CATAFLAN");
		p.setPreco(13.25);
		p.setQuantidade(29L);

		Fornecedores f = new Fornecedores();
		f.setCodigo(12L);

		p.setFornecedores(f);
		
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.editar(p);
		
	}
	
	
}
