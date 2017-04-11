package br.com.prosperity.batch.util;

import java.util.ArrayList;
import java.util.List;

public class VagaUtil {
	private static List<String> vagas = new ArrayList<>();
	
	public static void adicionaNovaVaga(String nomeVaga) {
		if(!vagas.contains(nomeVaga)) {
			vagas.add(nomeVaga);
		}
	}
	
	public static String getVaga(int i) {
		return vagas.get(i);
	}
	
	public static int getIdNomeVaga(String vaga) {
		
		int j = -1;
		
		for(int i = 0; i<vagas.size(); i++) {
			if(vaga.equals(vagas.get(i))) {
				j = i;
			}
		}
		
		return j;
	}
}
