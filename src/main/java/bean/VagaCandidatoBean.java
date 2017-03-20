package bean;

import java.util.List;

public class VagaCandidatoBean {
	private Integer id;
	private VagaBean vaga;
	private CanalInformacaoBean canalInformacao;
	private List<CandidatoBean> candidatoBean;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VagaBean getVaga() {
		return vaga;
	}

	public void setVaga(VagaBean vaga) {
		this.vaga = vaga;
	}

	public CanalInformacaoBean getCanalInformacao() {
		return canalInformacao;
	}

	public void setCanalInformacao(CanalInformacaoBean canalInformacao) {
		this.canalInformacao = canalInformacao;
	}

	public List<CandidatoBean> getCandidatoBean() {
		return candidatoBean;
	}

	public void setCandidatoBean(List<CandidatoBean> candidatoBean) {
		this.candidatoBean = candidatoBean;
	}
	

}
