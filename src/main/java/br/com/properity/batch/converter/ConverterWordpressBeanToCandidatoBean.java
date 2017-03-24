package br.com.properity.batch.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.CanalInformacaoBean;
import bean.CandidatoBean;
import bean.CargoBean;
import bean.ContatoBean;
import bean.EnderecoBean;
import bean.FormacaoBean;
import bean.SituacaoAtualBean;
import bean.TipoCursoBean;
import bean.VagaBean;
import bean.VagaCandidatoBean;
import br.com.properity.batch.bean.CandidatoWordPressBean;

public class ConverterWordpressBeanToCandidatoBean {

	public CandidatoBean transformaWordpressEmCandidato(CandidatoWordPressBean w) {
		CandidatoBean candidato = new CandidatoBean();
		VagaBean vaga = new VagaBean();
		ContatoBean contato = new ContatoBean();
		EnderecoBean endereco = new EnderecoBean();
		FormacaoBean formacao = new FormacaoBean();
		TipoCursoBean tipoCurso = new TipoCursoBean();
		SituacaoAtualBean situacaoAtual = new SituacaoAtualBean();
		CanalInformacaoBean canalInformacao = new CanalInformacaoBean();
		VagaCandidatoBean vagaCandidato = new VagaCandidatoBean();
		CargoBean cargoBean = new CargoBean();
		
		cargoBean.setNome("x");
		
		for (int i = 0; i < w.getTipoCampo().size(); i++) {
			
			// Regra para as strings: Primeira letra da primeira palavra sempre maiúscula e sem caracteres especiais.
			// As demais letras ficam minúsculas.
			if (w.getTipoCampo().get(i).equals("Cpf")) {
				candidato.setCpf(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Nome")) {
				candidato.setNome(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Data nascimento")) {

				DateFormat formatoData = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
				Date dataConvertida = null;
				try {
					dataConvertida = formatoData.parse(w.getValorCampo(i));
				} catch (ParseException e) {
					dataConvertida = null;
					System.out.println("Erro na tentativa de conversão de data no Converter linha 46:\n" + e);
				}
				candidato.setDataNascimento(dataConvertida);
			} else if (w.getTipoCampo().get(i).equals("Vaga")) {
				vaga.setNomeVaga(w.getValorCampo(i));
				//vaga.setAumentaQuadro('c');
			} else if (w.getTipoCampo().get(i).equals("Email")) {
				candidato.setEmail(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Telefone")) {
				contato.setTelefone(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Cidade")) {
				endereco.setCidade(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Grau de instrucao")) {
				// setar alguma coisa aqui
			} else if (w.getTipoCampo().get(i).equals("Curso")) {
				formacao.setNomeCurso(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Instituicao")) {
				formacao.setNomeInstituicao(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Tipo de curso")) {
				tipoCurso.setNome(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Situacao atual")) {
				situacaoAtual.setDescricao(w.getValorCampo(i));
			} else if (w.getTipoCampo().get(i).equals("Mes ano conclusao")) {
				Date data = new Date();
				data = transformaStringEmDataPadrao(w.getValorCampo(i));
				formacao.setDataConclusao(data);
			} else if (w.getTipoCampo().get(i).equals("Como ficou sabendo")) {
				canalInformacao.setNome(w.getValorCampo(i));
			} 
		}
		
		vaga.setAumentaQuadro('s');
		vaga.setCargoBean(cargoBean);
		vaga.setIdTipoVaga('z');
		candidato.setValorMin(10);
		vagaCandidato.setCanalInformacao(canalInformacao);
		formacao.setTipoCurso(tipoCurso);
		formacao.setSituacaoAtual(situacaoAtual);
		
		candidato.setVagaCandidatoBean(vagaCandidato);
		candidato.setFormacao(formacao);
		candidato.setVaga(vaga);
		candidato.setContato(contato);
		candidato.setEndereco(endereco);
		
		return candidato;
	}

	private Date transformaStringEmDataPadrao(String dataNaoTratada) {
		
		String dataTratada = "01";
		DateFormat formatoData = new SimpleDateFormat("dd MM yyyy");
		
		dataNaoTratada = dataNaoTratada.toLowerCase();
		
		if(dataNaoTratada.contains("dez") || dataNaoTratada.contains("dezembro") || dataNaoTratada.substring(0, 2).equals("12"))
			dataTratada += "/12";
		else if(dataNaoTratada.contains("nov") || dataNaoTratada.contains("novembro") || dataNaoTratada.substring(0, 2).equals("11"))
			dataTratada += "/11";
		else if(dataNaoTratada.contains("out") || dataNaoTratada.contains("outubro") || dataNaoTratada.substring(0, 2).equals("10"))
			dataTratada += "/10";
		else if(dataNaoTratada.contains("set") || dataNaoTratada.contains("setembro") || dataNaoTratada.substring(0, 2).equals("09")  || dataNaoTratada.substring(0, 1).equals("9"))
			dataTratada += "/09";
		else if(dataNaoTratada.contains("ago") || dataNaoTratada.contains("agosto") || dataNaoTratada.substring(0, 2).equals("08")  || dataNaoTratada.substring(0, 1).equals("8"))
			dataTratada += "/08";
		else if(dataNaoTratada.contains("jul") || dataNaoTratada.contains("julho") || dataNaoTratada.substring(0, 2).equals("07")  || dataNaoTratada.substring(0, 1).equals("7"))
			dataTratada += "/07";
		else if(dataNaoTratada.contains("jun") || dataNaoTratada.contains("junho") || dataNaoTratada.substring(0, 2).equals("06")  || dataNaoTratada.substring(0, 1).equals("6"))
			dataTratada += "/06";
		else if(dataNaoTratada.contains("mai") || dataNaoTratada.contains("maio") || dataNaoTratada.substring(0, 2).equals("05")  || dataNaoTratada.substring(0, 1).equals("5"))
			dataTratada += "/05";
		else if(dataNaoTratada.contains("abr") || dataNaoTratada.contains("abril") || dataNaoTratada.substring(0, 2).equals("04")  || dataNaoTratada.substring(0, 1).equals("4"))
			dataTratada += "/04";
		else if(dataNaoTratada.contains("mar") || dataNaoTratada.contains("março") || dataNaoTratada.contains("marco") || dataNaoTratada.substring(0, 2).equals("03")  || dataNaoTratada.substring(0, 1).equals("3"))
			dataTratada += "/03";
		else if(dataNaoTratada.contains("fev") || dataNaoTratada.contains("fevereiro") || dataNaoTratada.substring(0, 2).equals("02")  || dataNaoTratada.substring(0, 1).equals("2"))
			dataTratada += "/02";
		else if(dataNaoTratada.contains("jan") || dataNaoTratada.contains("janeiro") || dataNaoTratada.substring(0, 2).equals("01")  || dataNaoTratada.substring(0, 1).equals("1"))
			dataTratada += "/01";
		
		Date dataConvertida = null;
		try {
			dataConvertida = formatoData.parse(dataTratada);
		} catch (ParseException e) {
			dataConvertida = null;
			System.out.println("Erro na tentativa de conversão de data no Converter linha 86:\n" + e);
		}
		
		return dataConvertida;
	}


	public List<CandidatoBean> transformaLista(List<CandidatoWordPressBean> listaWordpress) {

		List<CandidatoBean> candidatos = new ArrayList<>();

		for (CandidatoWordPressBean w : listaWordpress) {
			candidatos.add(this.transformaWordpressEmCandidato(w));
		}
		return candidatos;
	}
}
