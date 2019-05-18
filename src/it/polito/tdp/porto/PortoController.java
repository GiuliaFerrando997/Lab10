package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<?> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	this.txtResult.clear();
    	try {
    		Author author = this.boxPrimo.getValue();
    		List<Author> coautori = model.getCoautori(author);
    		this.txtResult.appendText("I coautori sono: \n"+coautori.toString());
    	} catch(NumberFormatException c) {
    		txtResult.setText("Inserire una numero di lettere valido");
    	} catch(RuntimeException e ) {
    		txtResult.setText("Errore di connessione al database!");
    	}
    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.setComboBox();
		model.creaGrafo();
	}

	private void setComboBox() {
		model.creaGrafo();
		this.boxPrimo.getItems().setAll(model.getAllAuthors());
		
	}
}
