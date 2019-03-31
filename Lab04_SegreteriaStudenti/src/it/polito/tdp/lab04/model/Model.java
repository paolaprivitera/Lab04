package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

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
	
	

}
