package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	public List<String> getCorsi() {
		
		List<String> corsi = new LinkedList<String>();
		
		CorsoDAO dao = new CorsoDAO();
		
		corsi.add("");
		
		for(Corso c : dao.getTuttiICorsi()) {
			corsi.add(c.getNome());
		}
		
		return corsi;
	}

	public Studente getCompletamentoAutomatico(int matricola) {
		StudenteDAO dao = new StudenteDAO();
		Studente s=null;
		
		s = dao.getStudentePerMatricola(matricola);
		
		return s;
		
		/*List<Studente> studenti = new LinkedList<Studente>(dao.getStudenti());
				
		for(Studente s : studenti) {
			if(s.getMatricola()==matricola) {
				return s;
			}
		}
		
		return null;*/
		
		
	}

	public List<Studente> getIscrittiCorso(String corso) {
		
		
		
		return null;
	}
	
	

}
