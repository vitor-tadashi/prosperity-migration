package bean;

import org.springframework.stereotype.Component;

@Component
public class CandidatoCompetenciaBean {

	private Integer id;
	private CompetenciaBean competencia;
	private AvaliacaoBean avaliacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CompetenciaBean getCompetencia() {
		return competencia;
	}

	public void setCompetencia(CompetenciaBean competencia) {
		this.competencia = competencia;
	}

	public AvaliacaoBean getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(AvaliacaoBean avaliacao) {
		this.avaliacao = avaliacao;
	}

}
