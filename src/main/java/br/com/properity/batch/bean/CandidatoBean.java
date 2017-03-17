package br.com.properity.batch.bean;

import java.util.ArrayList;
import java.util.List;

public class CandidatoBean {
	private int contador = -1;
	private Long id;
	private Long lead_id;
	private Integer form_id;
	private Integer field_number;

	private List<String> tipoCampo = new ArrayList<>();
	private List<String> valorCampo = new ArrayList<>();

	public String getValorCampo(int i) {
		return valorCampo.get(i);
	}

	public void setValorCampo(String valorCampo) {
		this.valorCampo.add(valorCampo);
	}

	public List<String> getTipoCampo() {
		return this.tipoCampo;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public void setTipoCampo() {
		this.contador++;
		switch (this.field_number) {
		case 15:
			this.tipoCampo.add("vaga");
			break;
		case 1:
			this.tipoCampo.add("Nome Completo");
			break;
		case 8:
			this.tipoCampo.add("Data nascimento");
			break;
		case 3:
			this.tipoCampo.add("E-mail");
			break;
		case 10:
			this.tipoCampo.add("Telefone");
			break;
		case 11:
			this.tipoCampo.add("Cidade");
			break;
		case 27:
			this.tipoCampo.add("Grau de instruções");
			break;
		case 28:
			this.tipoCampo.add("Curso");
			break;
		case 29:
			this.tipoCampo.add("Instituição");
			break;
		case 31:
			this.tipoCampo.add("Tipo de curso");
			break;
		case 32:
			this.tipoCampo.add("Situação atual");
			break;
		case 33:
			this.tipoCampo.add("Mês/ano de conclusão");
			break;
		case 13:
			this.tipoCampo.add("Currículo");
			break;
		default:
			this.tipoCampo.add("Valor");
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
