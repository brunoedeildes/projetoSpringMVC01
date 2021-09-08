package br.com.cotiinformatica.entities;

public class Servico {

	private Integer idServico;
	private String nome;
	private String descricao;
	private Integer tempoAtendimento;
	private Double preco;

	// Associação TER-1
	private Profissional profissional;

	// construtor default (vazio)
	public Servico() {
		// TODO Auto-generated constructor stub
	}

	// sobrecarga do método construtor (OVERLOADING)
	public Servico(Integer idServico, String nome, String descricao, Integer tempoAtendimento, Double preco,
			Profissional profissional) {
		super();
		this.idServico = idServico;
		this.nome = nome;
		this.descricao = descricao;
		this.tempoAtendimento = tempoAtendimento;
		this.preco = preco;
		this.profissional = profissional;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTempoAtendimento() {
		return tempoAtendimento;
	}

	public void setTempoAtendimento(Integer tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	// Sobrescrita de método (OVERRIDE)
	@Override
	public String toString() {
		return "Servico [idServico=" + idServico + ", nome=" + nome + ", descricao=" + descricao + ", tempoAtendimento="
				+ tempoAtendimento + ", preco=" + preco + ", profissional=" + profissional + "]";
	}
}
