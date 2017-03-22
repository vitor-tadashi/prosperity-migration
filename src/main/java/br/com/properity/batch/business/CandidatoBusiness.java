package br.com.properity.batch.business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import bean.CandidatoBean;
import br.com.properity.batch.bean.CandidatoWordPressBean;
import br.com.properity.batch.converter.ConverterWordpressBeanToCandidatoBean;
import br.com.properity.batch.dao.CandidatoDAO;

public class CandidatoBusiness {
	private CandidatoDAO candidatoDao = new CandidatoDAO();
	private List<CandidatoWordPressBean> listaCandidatosWordpress = new ArrayList<>();
	private final String textFile = "target/generated-sources/DataUltimoCadastro.txt";

	public List<CandidatoBean> retornaListaBean() {
		
		List<CandidatoBean> candidatos = new ArrayList<>();
		listaCandidatosWordpress = candidatoDao.listar();
		
		ConverterWordpressBeanToCandidatoBean conversor = new ConverterWordpressBeanToCandidatoBean();
		candidatos = conversor.transformaLista(listaCandidatosWordpress);
		
		return candidatos;
		
	}

	public String ultimoCadastro() {
		return candidatoDao.DataUltimoCadastro();
	}

	public void GravaDataUltimoCadastro() {
		String dataNova;

		dataNova = candidatoDao.DataUltimoCadastro();

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(textFile, true)));

			// Aqui eu apago o textFile sempre antes de gravar a nova data nele:
			FileWriter fwOb = new FileWriter(textFile, false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();

			// Finalmente armazeno a primeira data nele:
			out.print("'" + dataNova + "'");
			out.close();
		} catch (IOException e) {
			System.out.println(
					"Ocorreu o seguinte erro quando o programa tentou escrever no seguinte arquivo:\n" + textFile);
			System.out.println(e);
		}
	}

	public void forçaDataAntiga() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(textFile, true)));

			// Aqui eu apago o textFile sempre antes de gravar a nova data nele:
			FileWriter fwOb = new FileWriter(textFile, false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();

			out.print("'" + "2017-01-01" + "'");
			out.close();
		} catch (IOException e) {
			System.out.println(
					"Ocorreu o seguinte erro quando o programa tentou escrever no seguinte arquivo:\n" + textFile);
			System.out.println(e);
		}
	}
}
