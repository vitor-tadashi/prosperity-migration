package br.com.properity.batch.bean;

public class CandidatoBean {
	private Long id;
	private Long lead_id;
	private Integer form_id;
	private Integer field_number;

	private String tipoCampo;
	private String valorCampo;

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public String getValorCampo() {
		return valorCampo;
	}

	public void setValorCampo(String valorCampo) {
		this.valorCampo = valorCampo;
	}

	public String getTipoCampo() {
		return valorCampo;
	}

	public void setTipoCampo() {
		switch (this.field_number) {
		case 15:
			this.valorCampo = "vaga";
			break;
		case 1:
			this.valorCampo = "Nome Completo";
			break;
		case 8:
			this.valorCampo = "Data nascimento";
			break;
		case 3:
			this.valorCampo = "E-mail";
			break;
		case 10:
			this.valorCampo = "Telefone";
			break;
		case 11:
			this.valorCampo = "Cidade";
			break;
		case 27:
			this.valorCampo = "Grau de instruções";
			break;
		case 28:
			this.valorCampo = "Curso";
			break;
		case 29:
			this.valorCampo = "Instituição";
			break;
		case 31:
			this.valorCampo = "Tipo de curso";
			break;
		case 32:
			this.valorCampo = "Situação atual";
			break;
		case 33:
			this.valorCampo = "Mês/ano de conclusão";
			break;
		case 13:
			this.valorCampo = "Currículo";
			break;
		default:
			this.valorCampo = "Valor";
			;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
