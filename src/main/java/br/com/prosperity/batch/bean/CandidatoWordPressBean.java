package br.com.prosperity.batch.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CandidatoWordPressBean")
public class CandidatoWordPressBean {

	// Atributos da tabela do wordpress:
	private Long idWordpress;
	private Long lead_id;
	private Integer form_id;

	// Atributos com nomes específicos:
	private String nome;
	private String email;
	private String dataNascimento;
	private String vaga;
	private String telefone;
	private String cidade;
	private String tipoCurso;
	private String curso;
	private String instituicao;
	private String situacaoAtual;
	private String grauInstrucao;
	private String dataFormacao;
	private String comoFicouSabendo;
	private String CEP;
	private String CPF;
	private String RG;
	private String estado;
	private String logradouro;
	private String numeroResidencial;
	private String complemento;
	private String pretensao;
	
	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroResidencial() {
		return numeroResidencial;
	}

	public void setNumeroResidencial(String numeroResidencial) {
		this.numeroResidencial = numeroResidencial;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPretensao() {
		return pretensao;
	}

	public void setPretensao(String pretensao) {
		this.pretensao = pretensao;
	}

	public void setComoFicouSabendo(String comoFicouSabendo) {
		this.comoFicouSabendo = comoFicouSabendo;
	}

	public Long getIdWordpress() {
		return idWordpress;
	}

	public void setIdWordpress(Long idWordpress) {
		this.idWordpress = idWordpress;
	}

	public Long getLead_id() {
		return lead_id;
	}

	public void setLead_id(Long lead_id) {
		this.lead_id = lead_id;
	}

	public Integer getForm_id() {
		return form_id;
	}

	public void setForm_id(Integer form_id) {
		this.form_id = form_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getVaga() {
		return vaga;
	}

	public void setVaga(String vaga) {
		this.vaga = vaga;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getSituacaoAtual() {
		return situacaoAtual;
	}

	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}

	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	public String getDataFormacao() {
		return dataFormacao;
	}

	public void setDataFormacao(String dataFormacao) {
		this.dataFormacao = dataFormacao;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getComoFicouSabendo() {
		return comoFicouSabendo;
	}

}