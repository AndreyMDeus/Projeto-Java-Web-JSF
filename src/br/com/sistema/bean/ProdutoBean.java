package br.com.sistema.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.sistema.DAO.FornecedoresDAO;
import br.com.sistema.DAO.ProdutoDAO;
import br.com.sistema.domain.Fornecedores;
import br.com.sistema.domain.Produtos;
import br.com.sistema.util.JSFUtil;

@SuppressWarnings("deprecation")
@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutoBean {

	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private ArrayList<Fornecedores> comboFornecedores;

	@Inject
	ProdutoDAO pdao;	

    @Inject
	FornecedoresDAO fdao;
	
	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
	
	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}
	
	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
			
	@PostConstruct	
	public void prepararPesquisa() {

		try {
			ProdutoDAO pdao = new ProdutoDAO();
			itens = pdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void prepararNovo() {
		
	    try {
			produtos = new Produtos();		
		    FornecedoresDAO fdao = new FornecedoresDAO();
			comboFornecedores = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void novo() {
		
		try {
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.salvar(produtos);
			itens = pdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void excluir() {

		try {
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.excluir(produtos);
			itens = pdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto excluído com sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

	public void prepararEditar() {
		
	    try {
			produtos = new Produtos();		
		    FornecedoresDAO fdao = new FornecedoresDAO();
			comboFornecedores = fdao.listar();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	public void editar() {

		try {
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.editar(produtos);
			itens = pdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto alterado com sucesso!");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}
