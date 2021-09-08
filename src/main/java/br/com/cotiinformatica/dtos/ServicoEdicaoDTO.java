package br.com.cotiinformatica.dtos;

public class ServicoEdicaoDTO {

	private Integer idServico;
	private String nome;
	private String preco;
	private String tempoAtendimento;
	private String descricao;
	private String profissional;

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

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getTempoAtendimento() {
		return tempoAtendimento;
	}

	public void setTempoAtendimento(String tempoAtendimento) {
		this.tempoAtendimento = tempoAtendimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProfissional() {
		return profissional;
	}

	public void setProfissional(String profissional) {
		this.profissional = profissional;
	}

}



