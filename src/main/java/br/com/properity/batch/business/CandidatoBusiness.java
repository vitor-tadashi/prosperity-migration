package br.com.properity.batch.business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.properity.batch.bean.CandidatoWordPressBean;
import br.com.properity.batch.dao.CandidatoDAO;

public class CandidatoBusiness {
	
	@Autowired
	private CandidatoDAO candidatoDAO;
	
	private final String textFile = "target/generated-sources/DataUltimoCadastro.txt";

	public List<CandidatoWordPressBean> listar() {
		return candidatoDAO.listar();
	}

	public void gravarDataUltimoProcessamento() {
		try {
			PrintWriter out = null;
			String dataNova;
			FileWriter fwOb = null;
			PrintWriter pwOb = null;
			
			dataNova = candidatoDAO.obterDataUltimoProcessamento();
			
			out = new PrintWriter(new BufferedWriter(new FileWriter(textFile, true)));
			fwOb = new FileWriter(textFile, false);
			pwOb = new PrintWriter(fwOb, false);
			
			// Aqui eu apago o textFile sempre antes de gravar a nova data nele:
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
}
