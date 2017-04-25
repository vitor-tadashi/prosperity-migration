package br.com.prosperity.batch.business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import br.com.prosperity.batch.bean.CandidatoWordPressBean;
import br.com.prosperity.batch.dao.CandidatoDAO;

public class CandidatoBusiness {
	private CandidatoDAO candidatoDAO;
	private final String textFile = "target/generated-sources/UltimoLeadId.txt";
	
	// Isso é uma forma manual de fazer AutoWired:
	public void setCandidatoDAO(CandidatoDAO candidatoDAO) {
		this.candidatoDAO = candidatoDAO;
	}

	public List<CandidatoWordPressBean> listar() {
		return candidatoDAO.listar();
	}

	public void gravarLeadIdUltimoProcessamento(Long lead_id) {
		try {
			PrintWriter out = null;
			String lead;
			FileWriter fwOb = null;
			PrintWriter pwOb = null;
			
			lead = candidatoDAO.obterLeadIdUltimoProcessamento(lead_id);
			
			out = new PrintWriter(new BufferedWriter(new FileWriter(textFile, true)));
			fwOb = new FileWriter(textFile, false);
			pwOb = new PrintWriter(fwOb, false);
			
			// Aqui eu apago o textFile sempre antes de gravar o novo lead_id nele:
			pwOb.flush();
			pwOb.close();
			fwOb.close();

			// Finalmente armazeno o lead_id nele:
			out.print(lead);
			out.close();
		} catch (IOException e) {
			System.out.println(
					"Ocorreu o seguinte erro quando o programa tentou escrever no seguinte arquivo:\n" + textFile);
			System.out.println(e);
		}
	}
}
