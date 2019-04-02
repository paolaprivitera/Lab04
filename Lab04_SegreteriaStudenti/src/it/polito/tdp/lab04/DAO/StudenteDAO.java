package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudentePerMatricola(int matricola) {
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		Studente s=null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				int matr = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				s = new Studente(matr, cognome, nome, cds);
				System.out.println(s.toString());
			}

			// rs.close();
			conn.close();

			return s;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}

	/*public List<Studente> getStudenti() {
	final String sql = "SELECT * FROM studente";

	List<Studente> studenti = new LinkedList<Studente>();
	Studente s;

	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			int matr = rs.getInt("matricola");
			String cognome = rs.getString("cognome");
			String nome = rs.getString("nome");
			String cds = rs.getString("CDS");

			s = new Studente(matr, cognome, nome, cds);
			studenti.add(s);

		}

		// rs.close();
		conn.close();

		return studenti;

	} catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}

}*/

}
