/**
 * Sample Skeleton for 'Rivers.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	
	Model m;
	public void setModel(Model m){
		this.m = m;
		boxRiver.getItems().addAll(m.getRivers());
	}

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doPopola(ActionEvent event) {

    	txtStartDate.clear();
    	txtEndDate.clear();
    	txtNumMeasurements.clear();
    	txtFMed.clear();
    	
    	if(boxRiver.getValue()!=null){
    		
    		List<Flow> f = m.getRilevamenti(boxRiver.getValue());
    		
    		double media = 0;
    		for(Flow fl : f){
    			media+=fl.getFolw();
    		}
    		media=media/(f.size()*3600);
    		
    		txtStartDate.setText(f.get(0).getDay().toString());
    		txtEndDate.setText(f.get(f.size()-1).getDay().toString());
    		txtNumMeasurements.setText(""+f.size());
    		
    		
    		txtFMed.setText(""+new DecimalFormat("#.####").format(media));
    	}	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	if(boxRiver.getValue()!=null){
    		if(txtK.getText()!=null){
    			float k = Float.parseFloat(txtK.getText());
    			List<String> d = m.load(k);
    			txtResult.appendText("Numero di giorni in cui non si è potuta garantire l’erogazione minima: "+d.get(0)+"\n");
    			txtResult.appendText("L’occupazione media del bacino nel corso della simulazione è: "+d.get(1)+"\n");
    		}
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";
    }
}