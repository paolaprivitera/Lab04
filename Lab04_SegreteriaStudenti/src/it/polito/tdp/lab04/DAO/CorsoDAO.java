package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);

			}

			// rs.close();
			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {

		List<Studente> studenti = new LinkedList<Studente>();
		StudenteDAO dao = new StudenteDAO();
		Studente s;

		final String sql = "SELECT matricola FROM iscrizione WHERE codins = ?";

		// List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int matricola = rs.getInt("matricola");
				
				s = dao.getStudentePerMatricola(matricola);
				
				studenti.add(s);
			}

			// rs.close();
			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

	public Corso cercaCorso(String corso) {
		
		List<Corso> corsi = new LinkedList<Corso>();
		corsi = this.getTuttiICorsi();
		
		for(Corso c : corsi) {
			if (c.getNome().compareTo(corso)==0) {
				return c;
			}
		}
		
		return null;
	}

	public Corso getCorsoPerCodins(String codins) {
		
		List<Corso> corsi = new LinkedList<Corso>();
		corsi = this.getTuttiICorsi();
		
		for(Corso c : corsi) {
			if (c.getCodins().compareTo(codins)==0) {
				return c;
			}
		}
		
		return null;
	}

	public void iscriviStudente(Studente s, Corso c) {
	
		Connection conn = ConnectDB.getConnection();
		String sql = "INSERT INTO iscrizione VALUES (?,?)";
		
		try {
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			st.execute(); // NOTA BENE: non st.executeQuery();
			
			conn.close();			
			
		}
		catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
		
	}


	
	
	
}
