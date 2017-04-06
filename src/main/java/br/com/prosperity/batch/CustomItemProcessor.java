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
import br.com.prosperity.bean.CandidatoBean;

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

		// CONTATO BEAN
		if (w.getTelefone().matches("[0-9]+") && w.getTelefone().length() <= 50)
			candidato.getContato().setTelefone(w.getTelefone());

		// ENDEREÇO BEAN
		candidato.getEndereco().setCep(w.getCEP());

		if (w.getCidade().length() <= 45)
			candidato.getEndereco().setCidade(w.getCidade());

		if (w.getEstado().length() <= 20)
			candidato.getEndereco().setEstado(w.getEstado());

		if (w.getLogradouro().length() <= 50)
			candidato.getEndereco().setLogradouro(w.getLogradouro());

		if (w.getComplemento().length() <= 50)
			candidato.getEndereco().setComplemento(w.getComplemento());

		if (w.getNumeroResidencial().length() <= 10 && w.getNumeroResidencial().matches("[0-9]+"))
			candidato.getEndereco().setNumero(Integer.parseInt(w.getNumeroResidencial()));

		// FORMAÇÃO BEAN
		candidato.getFormacao().setDataConclusao(this.transformaStringData(w.getDataFormacao()));

		if (w.getCurso().length() <= 100)
			candidato.getFormacao().setNomeCurso(w.getCurso());

		if (w.getInstituicao().length() <= 100)
			candidato.getFormacao().setNomeInstituicao(w.getInstituicao());

		if (w.getSituacaoAtual().length() <= 50)
			candidato.getFormacao().getSituacaoAtual().setDescricao(w.getSituacaoAtual());

		if (w.getTipoCurso().length() <= 40)
			candidato.getFormacao().getTipoCurso().setNome(w.getTipoCurso());

		// VAGA BEAN
		if (w.getVaga().length() <= 50)
			candidato.getUltimaVaga().setNomeVaga(w.getVaga());

		// CANDIDATO BEAN
		candidato.setCpf(w.getCPF());

		if (isDateValid(w.getDataNascimento(), "dd-MM-yyyy")) {
			try {
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				df.setLenient(false);

				Date dataNasc = df.parse(w.getDataNascimento());

				candidato.setDataNascimento(dataNasc);
			} catch (ParseException e) {
			}
		} else if (isDateValid(w.getDataNascimento(), "MM-dd-yyyy")) {
			try {
				DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
				df.setLenient(false);

				Date dataNasc = df.parse(w.getDataNascimento());

				candidato.setDataNascimento(dataNasc);
			} catch (ParseException e) {
			}
		}

		if (w.getEmail().length() <= 45)
			candidato.setEmail(w.getEmail());
		if (w.getNome().length() <= 100)
			candidato.setNome(w.getNome());

		candidato.setValorMin(Double.parseDouble(w.getPretensaoMinima()));
		candidato.setValorMax(Double.parseDouble(w.getPretensaoMaxima()));
		
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
				System.out.println(ex.getMessage());
			}

			candidatos.add(candidato);
		}
		return candidatos;
	}
}