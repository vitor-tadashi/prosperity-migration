package br.com.properity.batch.bean;

import java.util.ArrayList;
import java.util.List;

public class CandidatoWordPressBean {
	
	// Atributos da tabela do wordpress: 
	private Long idWordpress;
	private Long lead_id;
	private Integer form_id;
	private Integer field_number;

	private List<String> tipoCampo = new ArrayList<>();
	private List<String> valorCampo = new ArrayList<>();
	// Fim atributos da tabela do wordpress.
	
	// Setters e getters do WordpressBean
	public String getValorCampo(int i) {
		return valorCampo.get(i);
	}

	public void setValorCampo(String valorCampo) {
		this.valorCampo.add(valorCampo);
	}

	public List<String> getTipoCampo() {
		return this.tipoCampo;
	}

	public void setTipoCampo() {
		
		// Regra para as strings: Primeira letra da primeira palavra sempre maiúscula e sem caracteres especiais.
		// As demais letras ficam minúsculas.
		switch (this.field_number) {
		case 15:
			this.tipoCampo.add("Vaga");
			break;
		case 1:
			this.tipoCampo.add("Nome");
			break;
		case 8:
			this.tipoCampo.add("Data nascimento");
			break;
		case 3:
			this.tipoCampo.add("Email");
			break;
		case 10:
			this.tipoCampo.add("Telefone");
			break;
		case 11:
			this.tipoCampo.add("Cidade");
			break;
		case 27:
			this.tipoCampo.add("Grau de instrucao");
			break;
		case 28:
			this.tipoCampo.add("Curso");
			break;
		case 29:
			this.tipoCampo.add("Instituicao");
			break;
		case 31:
			this.tipoCampo.add("Tipo de curso");
			break;
		case 32:
			this.tipoCampo.add("Situacao atual");
			break;
		case 33:
			this.tipoCampo.add("Mes ano conclusao");
			break;
		case 13:
			this.tipoCampo.add("Curriculo");
			break;
		case 14:
			this.tipoCampo.add("Como ficou sabendo");
			break;
		case 16:
			this.tipoCampo.add("Outros");
			break;
		case 18:
			this.tipoCampo.add("Remuneracao atual");
			break;
		case 23:
			this.tipoCampo.add("Beneficios atuais");
			break;
		case 20:
			this.tipoCampo.add("Pretensao salarial");
			break;
		case 21:
			this.tipoCampo.add("Referencias profissionais");
			break;
		case 19:
			this.tipoCampo.add("Parecer RH");
			break;
		case 24:
			this.tipoCampo.add("Parecer final RH");
			break;
		case 25:
			this.tipoCampo.add("Responsavel tecnico");
			break;
		case 38:
			this.tipoCampo.add("Profundidade");
			break;
		case 36:
			this.tipoCampo.add("Planejamento");
			break;
		case 37:
			this.tipoCampo.add("Execucao e entrega");
			break;
		case 39:
			this.tipoCampo.add("Profundidade");
			break;
		case 42:
			this.tipoCampo.add("Pessoas");
			break;
		case 41:
			this.tipoCampo.add("Comercial");
			break;
		case 40:
			this.tipoCampo.add("Financeiro");
			break;
		case 9:
			this.tipoCampo.add("Cpf");
			break;
		default:
			this.tipoCampo.add("Valor");
		}
	}

	public Long getIdWordpress() {
		return this.idWordpress;
	}

	public void setIdWordpress(Long id) {
		this.idWordpress = id;
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

	public Integer getField_number() {
		return field_number;
	}

	public void setField_number(Integer field_number) {
		this.field_number = field_number;
	}
	// Fim setters e getters do WordpressBean
}
