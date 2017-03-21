package br.com.prosperity.batch;

import br.com.properity.batch.business.CandidatoBusiness;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!

public class TesteConn {
	public static void main(String[] args) {
		CandidatoBusiness b = new CandidatoBusiness();
		
		b.forçaDataAntiga();
		b.mostrarLista();
		b.GravaDataUltimoCadastro();
	}
}