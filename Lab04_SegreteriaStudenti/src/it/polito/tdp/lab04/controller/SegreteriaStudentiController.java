package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> btnSceltaCorsi;

	@FXML
	private Button btnCercaIscritti;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnSpunta;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnReset;

	@FXML
	void doCercaCorso(ActionEvent event) {

		List<String> corsiMatricola = new LinkedList<String>();

		int matricola = Integer.parseInt(txtMatricola.getText());
		corsiMatricola = model.getCorsiPerMatricola(matricola);

		if(corsiMatricola == null) {
			txtResult.appendText("Matricola non presente!");
		}
		else {
			for(String s : corsiMatricola) {
				txtResult.appendText(s+"\n");
			}
		}

	}

	@FXML
	void doCercaIscritti(ActionEvent event) {

		txtResult.clear();
		
		String corso = btnSceltaCorsi.getValue();
		List<String> studentiIscritti = new LinkedList<String>();

		String matr = txtMatricola.getText();
		boolean iscritto = false;
		int matricola;

		if(corso.compareTo("")!=0 && matr.compareTo("")!=0) { // NOTA BENE: "" vuol dire che non ha selezionato niente
			
			matricola = Integer.parseInt(matr);
			
			iscritto = model.getIscrizione(corso, matricola);			
			
			if(iscritto) {
				txtResult.setText("Studente gi� iscritto a questo corso");
			}
			else {
				txtResult.setText("Studente non iscritto a questo corso");
			}
			
		}
		else if(matr.compareTo("")==0) {
			if(corso==null || corso.compareTo("")==0) {
				txtResult.setText("Nessun corso selezionato!");
			}
			else {
				studentiIscritti = model.getIscrittiCorso(corso);
				for (String s : studentiIscritti) {
					txtResult.appendText(s+"\n");
				}
			}
		}



	}

	@FXML
	void doIscrivi(ActionEvent event) {

	}

	@FXML
	void doReset(ActionEvent event) {
		txtMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		txtResult.clear();
	}

	@FXML
	void doSceltaCorsi(ActionEvent event) {

	}

	@FXML
	void doSpunta(ActionEvent event) {

		int matricola = Integer.parseInt(txtMatricola.getText());
		Studente s = model.getCompletamentoAutomatico(matricola);

		/*txtNome.setText(model.getCompletamentoAutomatico(matricola).getNome());
    	txtCognome.setText(model.getCompletamentoAutomatico(matricola).getCognome());*/
		txtNome.setText(s.getNome());
		txtCognome.setText(s.getCognome());


	}

	@FXML
	void initialize() {
		assert btnSceltaCorsi != null : "fx:id=\"btnSceltaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnSpunta != null : "fx:id=\"btnSpunta\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

	public void setModel(Model model) {
		this.model = model;
		btnSceltaCorsi.getItems().addAll(model.getCorsi()); // NOTA BENE!
	}
}
