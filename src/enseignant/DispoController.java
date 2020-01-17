package enseignant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class DispoController {
	@FXML
	private CheckBox seance1;
	@FXML
	private CheckBox seance2;
	@FXML
	private CheckBox seance3;
	@FXML
	private CheckBox seance4;
	@FXML
	private CheckBox seance5;
	@FXML
	private CheckBox seance6;
	@FXML
	private CheckBox seance7;
	@FXML
	private CheckBox seance8;
	@FXML
	private CheckBox seance9;
	private String disponibilite = "000000000";
	public void saveDis(ActionEvent event) {
		disponibilite ="";
		if(seance1.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance2.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance3.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance4.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance5.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance6.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance7.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance8.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		if(seance9.isSelected())
			disponibilite+="1";
		else
			disponibilite+="0";
		EnseignantController.disponibilite = disponibilite;
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
	}

}
