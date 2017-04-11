package br.com.prosperity.batch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import br.com.prosperity.batch.bean.CandidatoWordPressBean;
import br.com.prosperity.batch.bean.WordpressBean;
import br.com.prosperity.batch.util.VagaUtil;
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

	private CandidatoBean transformaWordpressEmCandidato(CandidatoWordPressBean w) {
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

		VagaUtil.adicionaNovaVaga(w.getVaga());
		
		situacaoAtual.setId(9);

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
				formacao.setDataConclusao(this.transformaStringData("12/1912"));
		} else
			formacao.setDataConclusao(this.transformaStringData("12/1912"));

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

		if (w.getSituacaoAtual() != null)
			if (w.getSituacaoAtual().length() <= 50 && !"".equals(w.getSituacaoAtual()))
				situacaoAtual.setDescricao(w.getSituacaoAtual());

		if (w.getTipoCurso() != null)
			if (w.getTipoCurso().length() <= 40 && !"".equals(w.getTipoCurso()))
				tipoCurso.setNome(w.getTipoCurso());

		// VAGA BEAN
		if (w.getVaga().length() <= 50)
			vaga.setNomeVaga(w.getVaga());

		int idVaga = VagaUtil.getIdNomeVaga(w.getVaga());

		if (idVaga != -1)
			vaga.setId(idVaga);
		else {
			vaga.setId(999);
			vaga.setNomeVaga("Vaga Inválida");
		}
		// CANDIDATO BEAN
		if (w.getCPF().length() <= 15 && w.getCPF().length() > 0)
			candidato.setCpf(w.getCPF());
		else
			candidato.setCpf("111111111-1");

		if (isDateValid(w.getDataNascimento(), "dd-MM-yyyy")) {
			try {
				String brazilianPattern = "\\d{2}-\\d{2}-\\d{4}";
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				df.setLenient(false);
				Date dataNasc;
				if (w.getDataNascimento().matches(brazilianPattern)) {
					dataNasc = df.parse(w.getDataNascimento());
					candidato.setDataNascimento(dataNasc);
				} else {
					// Valor inválido digitado pelo usuário:
					dataNasc = df.parse("01-01-1980");
					candidato.setDataNascimento(dataNasc);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			try {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				df.setLenient(false);
				Date dataNasc;

				// Valor inválido digitado pelo usuário:
				dataNasc = df.parse("01-01-1980");
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

		if (w.getPretensao() != null && !w.getPretensao().isEmpty())
			candidato.setValorPretensao(1000.0);
		else
			candidato.setValorPretensao(1000.0);

		// BEANS QUE VÃO DENTRO DE CANDIDATO BEAN:
		
		usuario.setId(47);
		usuario.setSenha("1234");
		statusCandidato.setUsuario(usuario);
		
		vagaCandidato.setVaga(vaga);

		formacao.setTipoCurso(tipoCurso);
		formacao.setSituacaoAtual(situacaoAtual);

		candidato.setVagaCandidato(vagaCandidato);
		candidato.setContato(contato);
		candidato.setEndereco(endereco);
		candidato.setFormacao(formacao);

		return candidato;
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
				|| dataNaoTratada.substring(0, 2).equals("12"))
			dataTratada += "/12";
		else if (dataNaoTratada.contains("nov") || dataNaoTratada.contains("novembro")
				|| dataNaoTratada.substring(0, 2).equals("11"))
			dataTratada += "/11";
		else if (dataNaoTratada.contains("out") || dataNaoTratada.contains("outubro")
				|| dataNaoTratada.substring(0, 2).equals("10"))
			dataTratada += "/10";
		else if (dataNaoTratada.contains("set") || dataNaoTratada.contains("setembro")
				|| dataNaoTratada.substring(0, 2).equals("09") || dataNaoTratada.substring(0, 1).equals("9"))
			dataTratada += "/09";
		else if (dataNaoTratada.contains("ago") || dataNaoTratada.contains("agosto")
				|| dataNaoTratada.substring(0, 2).equals("08") || dataNaoTratada.substring(0, 1).equals("8"))
			dataTratada += "/08";
		else if (dataNaoTratada.contains("jul") || dataNaoTratada.contains("julho")
				|| dataNaoTratada.substring(0, 2).equals("07") || dataNaoTratada.substring(0, 1).equals("7"))
			dataTratada += "/07";
		else if (dataNaoTratada.contains("jun") || dataNaoTratada.contains("junho")
				|| dataNaoTratada.substring(0, 2).equals("06") || dataNaoTratada.substring(0, 1).equals("6"))
			dataTratada += "/06";
		else if (dataNaoTratada.contains("mai") || dataNaoTratada.contains("maio")
				|| dataNaoTratada.substring(0, 2).equals("05") || dataNaoTratada.substring(0, 1).equals("5"))
			dataTratada += "/05";
		else if (dataNaoTratada.contains("abr") || dataNaoTratada.contains("abril")
				|| dataNaoTratada.substring(0, 2).equals("04") || dataNaoTratada.substring(0, 1).equals("4"))
			dataTratada += "/04";
		else if (dataNaoTratada.contains("mar") || dataNaoTratada.contains("março") || dataNaoTratada.contains("marco")
				|| dataNaoTratada.substring(0, 2).equals("03") || dataNaoTratada.substring(0, 1).equals("3"))
			dataTratada += "/03";
		else if (dataNaoTratada.contains("fev") || dataNaoTratada.contains("fevereiro")
				|| dataNaoTratada.substring(0, 2).equals("02") || dataNaoTratada.substring(0, 1).equals("2"))
			dataTratada += "/02";
		else if (dataNaoTratada.contains("jan") || dataNaoTratada.contains("janeiro")
				|| dataNaoTratada.substring(0, 2).equals("01") || dataNaoTratada.substring(0, 1).equals("1"))
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

			candidatos.add(candidato);
		}
		return candidatos;
	}
}