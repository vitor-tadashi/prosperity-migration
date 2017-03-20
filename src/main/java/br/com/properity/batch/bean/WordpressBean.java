package br.com.properity.batch.bean;

import java.util.ArrayList;
import java.util.List;

public class WordpressBean {
	
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
		switch (this.field_number) {
		// id 0:
		case 15:
			this.tipoCampo.add("Vaga");
			break;
		// id 1:
		case 1:
			this.tipoCampo.add("Nome Completo");
			break;
		// id 2:
		case 8:
			this.tipoCampo.add("Data nascimento");
			break;
		// id 3:
		case 3:
			this.tipoCampo.add("E-mail");
			break;
		// id 4:
		case 10:
			this.tipoCampo.add("Telefone");
			break;
		// id 5:
		case 11:
			this.tipoCampo.add("Cidade");
			break;
		// id 6:			
		case 27:
			this.tipoCampo.add("Grau de instrução");
			break;
		case 28:
			this.tipoCampo.add("Curso");
			break;
		// id 7:
		case 29:
			this.tipoCampo.add("Instituição");
			break;
		// id 8:
		case 31:
			this.tipoCampo.add("Tipo de curso");
			break;
		// id 9:
		case 32:
			this.tipoCampo.add("Situação atual");
			break;
		// id 10:
		case 33:
			this.tipoCampo.add("Mês/ano de conclusão");
			break;
		// id 11:
		case 13:
			this.tipoCampo.add("Currículo");
			break;
		// id 12:
		case 14:
			this.tipoCampo.add("Como ficou sabendo da Verity");
			break;
		// id 13:
		case 16:
			this.tipoCampo.add("Como ficou sabendo da Verity: Outros");
			break;
		// id 14:
		case 18:
			this.tipoCampo.add("Remuneração atual");
			break;
		// id 15:
		case 23:
			this.tipoCampo.add("Benefícios atuais");
			break;
		// id 16:
		case 20:
			this.tipoCampo.add("Pretensão salarial");
			break;
		// id 17:
		case 21:
			this.tipoCampo.add("Referências profissionais");
			break;
		// id 18:
		case 19:
			this.tipoCampo.add("Parecer RH");
			break;
		// id 19:
		case 24:
			this.tipoCampo.add("Parecer final RH");
			break;
		// id 20:
		case 25:
			this.tipoCampo.add("Responsável técnico");
			break;
		// id 21:
		case 38:
			this.tipoCampo.add("Profundidade");
			break;
		// id 22:
		case 36:
			this.tipoCampo.add("Planejamento");
			break;
		// id 23:
		case 37:
			this.tipoCampo.add("Execução e entrega");
			break;
		// id 24:
		case 39:
			this.tipoCampo.add("Profundidade");
			break;
		// id 25:
		case 42:
			this.tipoCampo.add("Pessoas");
			break;
		// id 26:
		case 41:
			this.tipoCampo.add("Comercial");
			break;
		// id 27:
		case 40:
			this.tipoCampo.add("Financeiro");
			break;
		// id 28 em diante
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
