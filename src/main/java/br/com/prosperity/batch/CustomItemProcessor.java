package br.com.prosperity.batch;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import br.com.prosperity.batch.bean.CandidatoWordPressBean;
import br.com.prosperity.batch.bean.WordpressBean;
import br.com.prosperity.bean.CanalInformacaoBean;
import br.com.prosperity.bean.CandidatoBean;
import br.com.prosperity.bean.ContatoBean;
import br.com.prosperity.bean.EnderecoBean;
import br.com.prosperity.bean.FormacaoBean;
import br.com.prosperity.bean.SituacaoAtualBean;
import br.com.prosperity.bean.StatusCandidatoBean;
import br.com.prosperity.bean.TipoCursoBean;
import br.com.prosperity.bean.UsuarioBean;
import br.com.prosperity.bean.VagaBean;
import br.com.prosperity.bean.VagaCandidatoBean;

public class CustomItemProcessor implements ItemProcessor<WordpressBean, WordpressBean> {

	@Override
	public WordpressBean process(WordpressBean wordPressBean) throws Exception {

		try {
			List<CandidatoBean> candidatos = convert(wordPressBean.getCandidatosWordPress());
			wordPressBean.setCandidatos(candidatos);
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}

		return wordPressBean;
	}

	// PRINCIPAL CONVERSOR:
	private CandidatoBean transformaWordpressEmCandidato(CandidatoWordPressBean w) {
		CanalInformacaoBean canalInformacao = new CanalInformacaoBean();
		CandidatoBean candidato = new CandidatoBean();
		ContatoBean contato = new ContatoBean();
		EnderecoBean endereco = new EnderecoBean();
		FormacaoBean formacao = new FormacaoBean();
		VagaBean vaga = new VagaBean();
		VagaCandidatoBean vagaCandidato = new VagaCandidatoBean();
		TipoCursoBean tipoCurso = new TipoCursoBean();
		SituacaoAtualBean situacaoAtual = new SituacaoAtualBean();
		StatusCandidatoBean statusCandidato = new StatusCandidatoBean();
		UsuarioBean usuario = new UsuarioBean();

		BigDecimal pretensao;

		// CANAL INFORMACAO BEAN:
		if (w.getComoFicouSabendo() != null)
			canalInformacao.setId(pegarIdCanal(w.getComoFicouSabendo()));
		else
			canalInformacao.setId(9); // Outros

		// CONTATO BEAN
		// Campo obrigatório:
		if (w.getTelefone() != null) {
			if (w.getTelefone().length() <= 50 && !"".equals(w.getTelefone()))
				contato.setTelefone(w.getTelefone());
			else
				contato.setTelefone("N/C");
		} else
			contato.setTelefone("N/C");

		// ENDEREÇO BEAN
		// Campo obrigatório:
		if (w.getCEP() != null) {
			if (w.getCEP().length() <= 15 && !"".equals(w.getCEP()))
				endereco.setCep(w.getCEP());
			else
				endereco.setCep("N/C");
		} else
			endereco.setCep("N/C");

		// Campo obrigatório:
		if (w.getCidade() != null) {
			if (w.getCidade().length() <= 45 && !"".equals(w.getCidade()))
				endereco.setCidade(w.getCidade());
			else
				endereco.setCidade("N/C");
		} else
			endereco.setCidade("N/C");

		// Campo obrigatório:
		if (w.getEstado() != null) {
			if (w.getEstado().length() <= 20 && !"".equals(w.getEstado()))
				endereco.setEstado(w.getEstado());
			else
				endereco.setEstado("N/C");
		} else
			endereco.setEstado("N/C");

		// Campo obrigatório:
		if (w.getLogradouro() != null) {
			if (w.getLogradouro().length() <= 50)
				endereco.setLogradouro(w.getLogradouro());
			else
				endereco.setLogradouro("N/C");
		} else
			endereco.setLogradouro("N/C");

		if (w.getComplemento() != null)
			if (w.getComplemento().length() <= 50 && !"".equals(w.getComplemento()))
				endereco.setComplemento(w.getComplemento());

		if (w.getNumeroResidencial() != null)
			if (w.getNumeroResidencial().length() <= 10 && w.getNumeroResidencial().matches("[0-9]")
					&& !w.getNumeroResidencial().equals(null))
				endereco.setNumero(Integer.parseInt(w.getNumeroResidencial()));

		// FORMAÇÃO BEAN
		if (w.getDataFormacao() != null) {
			if (!w.getDataFormacao().equals(null))
				formacao.setDataConclusao(this.transformaStringData(w.getDataFormacao()));
			else
				formacao.setDataConclusao(this.transformaStringData("12/1980"));
		} else
			formacao.setDataConclusao(this.transformaStringData("12/1980"));

		if (w.getCurso() != null) {
			if (w.getCurso().length() <= 100 && !w.getCurso().equals(null))
				formacao.setNomeCurso(w.getCurso());
			else
				formacao.setNomeCurso("N/C");
		} else
			formacao.setNomeCurso("N/C");

		if (w.getInstituicao() != null) {
			if (w.getInstituicao().length() <= 100 && !w.getInstituicao().equals(null))
				formacao.setNomeInstituicao(w.getInstituicao());
			else
				formacao.setNomeInstituicao("N/C");
		} else
			formacao.setNomeInstituicao("N/C");

		if (w.getSituacaoAtual() != null) {
			if (w.getSituacaoAtual().length() <= 50 && !"".equals(w.getSituacaoAtual()))
				situacaoAtual.setId(pegaIdSituacao(w.getSituacaoAtual()));
		} else
			situacaoAtual.setId(9);

		if (w.getTipoCurso() != null) {
			if (w.getTipoCurso().length() <= 40 && !"".equals(w.getTipoCurso()))
				tipoCurso.setId(pegaIdTipoCurso(w.getTipoCurso()));
		} else
			tipoCurso.setId(16);

		// VAGA BEAN CRIADA ESPECIALMENTE PARA OS CANDIDATOS DO WORDPRESS:
		vaga.setId(4446);

		// CANDIDATO BEAN
		if (w.getCPF().length() <= 15 && w.getCPF().length() > 0)
			candidato.setCpf(w.getCPF());
		else
			candidato.setCpf("111111111-1");

		if (isDateValid(w.getDataNascimento(), "dd/MM/yyyy")) {
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				df.setLenient(false);
				Date dataNasc;
				dataNasc = df.parse(w.getDataNascimento());
				candidato.setDataNascimento(dataNasc);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				df.setLenient(false);
				Date dataNasc;

				// Valor inválido digitado pelo usuário:
				dataNasc = df.parse("01/01/1900");
				candidato.setDataNascimento(dataNasc);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (w.getEmail().length() <= 45 && w.getEmail().length() > 0)
			candidato.setEmail(w.getEmail());
		else
			candidato.setEmail("email.invalido@verity.com.br");

		if (w.getNome().length() <= 100 && w.getEmail().length() > 0)
			candidato.setNome(w.getNome());
		else
			candidato.setNome("Nome inválido");

		if (w.getPretensao() != null && !w.getPretensao().isEmpty()) {
			
			try {
			pretensao = new BigDecimal(w.getPretensao());
			candidato.setValorPretensao(pretensao);
			} catch (Exception e) {
				candidato.setValorPretensao(BigDecimal.TEN);
			}
		} else
			candidato.setValorPretensao(BigDecimal.ZERO);
		
		if(candidato.getValorPretensao().compareTo(BigDecimal.valueOf( 214700 ))>=0){
			candidato.setValorPretensao(BigDecimal.valueOf(214000));
		}

			/* price.compareTo( BigDecimal.valueOf( 500 ) > 0 
				     && price.compareTo( BigDecimal.valueOf( 1000 ) < 0 )
			*/
			
		if (w.getRG() != null)
			candidato.setRg(w.getRG());
		else
			candidato.setRg("000");

		if (w.getCurriculo() != null)
			candidato.setCurriculoTexto(w.getCurriculo());

		// BEANS QUE VÃO DENTRO DE CANDIDATO BEAN:
		usuario.setId(1043);

		statusCandidato.setUsuario(usuario);

		vagaCandidato.setVaga(vaga);
		vagaCandidato.setCanalInformacao(canalInformacao);

		formacao.setTipoCurso(tipoCurso);
		formacao.setSituacaoAtual(situacaoAtual);

		candidato.setVagaCandidato(vagaCandidato);
		candidato.setContato(contato);
		candidato.setEndereco(endereco);
		candidato.setFormacao(formacao);
		candidato.setUsuario(usuario);

		return candidato;
	}

	private Integer pegarIdCanal(String comoFicouSabendo) {
		int id;

		switch (comoFicouSabendo) {
		case "SITE VERITY":
			id = 2;
			break;
		case "FACEBOOK":
			id = 3;
			break;
		case "LINKEDIN":
			id = 4;
			break;
		case "APINFO":
			id = 5;
			break;
		case "CATHO":
			id = 6;
			break;
		case "OUTROS SITES DE OPORTUNIDADES":
			id = 7;
			break;
		case "INDICAÇÃO DE COLEGAS":
			id = 8;
			break;
		default:
			id = 9; // Outros default
			break;
		}

		return id;
	}

	private Integer pegaIdTipoCurso(String tipoCurso) {

		int id;

		switch (tipoCurso) {
		case "Formação Escolar Fundamental (1° grau) e Média (2° grau)":
			id = 4;
			break;
		case "Curso Técnico - Médio (2º Grau)":
			id = 6;
			break;
		case "Graduação":
			id = 7;
			break;
		case "Pós-graduação – Especialização":
			id = 9;
			break;
		case "Pós-graduação – MBA":
			id = 10;
			break;
		case "Pós-graduação – Mestrado":
			id = 12;
			break;
		case "Pós-graduação – Doutorado":
			id = 15;
			break;
		default:
			id = 7; // Graduação default
			break;
		}

		return id;
	}

	private Integer pegaIdSituacao(String situacaoAtual) {

		int id = 9; // Cursando

		switch (situacaoAtual) {
		case "Cursando":
			id = 9;
			break;
		case "Concluido":
		case "Concluído":
			id = 1009;
			break;
		case "Interrompido":
			id = 1010;
			break;
		default:
			id = 9;
			break;
		}

		return id;
	}

	public boolean isDateValid(String date, String DATE_FORMAT) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private Date transformaStringData(String dataNaoTratada) {

		if (dataNaoTratada == null || dataNaoTratada.isEmpty())
			return null;

		if (dataNaoTratada.length() < 6)
			return null;

		String ano = dataNaoTratada.substring(dataNaoTratada.length() - 4, dataNaoTratada.length());

		if (ano.matches("[a-zA-Z]"))
			return null;

		String dataTratada = "01";
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		dataNaoTratada = dataNaoTratada.toLowerCase();

		if (dataNaoTratada.contains("dez") || dataNaoTratada.contains("dezembro")
				|| dataNaoTratada.startsWith("12"))
			dataTratada += "/12";
		else if (dataNaoTratada.contains("nov") || dataNaoTratada.contains("novembro")
				|| dataNaoTratada.startsWith("11"))
			dataTratada += "/11";
		else if (dataNaoTratada.contains("out") || dataNaoTratada.contains("outubro")
				|| dataNaoTratada.startsWith("10"))
			dataTratada += "/10";
		else if (dataNaoTratada.contains("set") || dataNaoTratada.contains("setembro")
				|| dataNaoTratada.startsWith("09") || dataNaoTratada.startsWith("9"))
			dataTratada += "/09";
		else if (dataNaoTratada.contains("ago") || dataNaoTratada.contains("agosto")
				|| dataNaoTratada.startsWith("08") || dataNaoTratada.startsWith("8"))
			dataTratada += "/08";
		else if (dataNaoTratada.contains("jul") || dataNaoTratada.contains("julho")
				|| dataNaoTratada.startsWith("07") || dataNaoTratada.startsWith("7"))
			dataTratada += "/07";
		else if (dataNaoTratada.contains("jun") || dataNaoTratada.contains("junho")
				|| dataNaoTratada.startsWith("06") || dataNaoTratada.startsWith("6"))
			dataTratada += "/06";
		else if (dataNaoTratada.contains("mai") || dataNaoTratada.contains("maio")
				|| dataNaoTratada.startsWith("05") || dataNaoTratada.startsWith("5"))
			dataTratada += "/05";
		else if (dataNaoTratada.contains("abr") || dataNaoTratada.contains("abril")
				|| dataNaoTratada.startsWith("04") || dataNaoTratada.startsWith("4"))
			dataTratada += "/04";
		else if (dataNaoTratada.contains("mar") || dataNaoTratada.contains("março") || dataNaoTratada.contains("marco")
				|| dataNaoTratada.startsWith("03") || dataNaoTratada.startsWith("3"))
			dataTratada += "/03";
		else if (dataNaoTratada.contains("fev") || dataNaoTratada.contains("fevereiro")
				|| dataNaoTratada.startsWith("02") || dataNaoTratada.startsWith("2"))
			dataTratada += "/02";
		else if (dataNaoTratada.contains("jan") || dataNaoTratada.contains("janeiro")
				|| dataNaoTratada.startsWith("01") || dataNaoTratada.startsWith("1"))
			dataTratada += "/01";
		else
			dataTratada = null;

		// Pega o ano:

		Date dataConvertida = null;

		if (dataTratada != null)
			if (!ano.contains("/"))
				if (Integer.parseInt(
						dataNaoTratada.substring(dataNaoTratada.length() - 4, dataNaoTratada.length())) >= 2000)
					dataTratada += "/" + ano;
				else
					dataTratada = null;

		// Se a data não estiver vazia e não conter caracteres, ela parsea:
		// do contrário retornará null lá embaixo
		if (dataTratada != null && dataTratada.length() == 10) {

			try {
				dataConvertida = formatoData.parse(dataTratada);
			} catch (ParseException e) {
				dataConvertida = null;
				System.out.println("Erro na tentativa de conversão de data no Converter:\n" + e);
			}
		}

		return dataConvertida;
	}

	private List<CandidatoBean> convert(List<CandidatoWordPressBean> listaWordpress) {

		List<CandidatoBean> candidatos = new ArrayList<>();

		for (CandidatoWordPressBean w : listaWordpress) {

			CandidatoBean candidato = new CandidatoBean();

			try {

				candidato = this.transformaWordpressEmCandidato(w);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (candidato.getCpf() != null && candidato.getNome() != null)
				if (!"".equals(candidato.getCpf()) && !"".equals(candidato.getNome()))
					candidatos.add(candidato);
		}
		return candidatos;
	}
}