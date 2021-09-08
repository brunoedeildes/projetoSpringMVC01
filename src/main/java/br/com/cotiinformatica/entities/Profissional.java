package br.com.cotiinformatica.entities;

import java.util.List;

public class Profissional {

	private Integer idProfissional;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private String observacoes;

	// Associação (TER-MUITOS)
	private List<Servico> servicos;

	public Profissional() {
		// TODO Auto-generated constructor stub
	}

	public Profissional(Integer idProfissional, String nome, String cpf, String email, String telefone,
			String observacoes, List<Servico> servicos) {
		super();
		this.idProfissional = idProfissional;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.observacoes = observacoes;
		this.servicos = servicos;
	}

	public Integer getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(Integer idProfissional) {
		this.idProfissional = idProfissional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	@Override
	public String toString() {
		return "Profissional [idProfissional=" + idProfissional + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email
				+ ", telefone=" + telefone + ", observacoes=" + observacoes + ", servicos=" + servicos + "]";
	}

}


