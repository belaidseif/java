package enseignant;

import java.net.URL;
import java.util.ResourceBundle;

import generation.GenerationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Emploi1EnseigantController implements Initializable{
	
	@FXML private Label label;
	
	@FXML private Label lbl1;
	@FXML private Label lbl2;
	@FXML private Label lbl3;
	@FXML private Label lbl4;
	@FXML private Label lbl5;
	@FXML private Label lbl6;
	@FXML private Label lbl7;
	@FXML private Label lbl8;
	
	@FXML private Label lbl9;
	@FXML private Label lbl10;
	@FXML private Label lbl11;
	@FXML private Label lbl12;
	@FXML private Label lbl13;
	@FXML private Label lbl14;
	@FXML private Label lbl15;
	@FXML private Label lbl16;
	
	@FXML private Label lbl17;
	@FXML private Label lbl18;
	@FXML private Label lbl19;
	@FXML private Label lbl20;
	
	@FXML private Label lbl21;
	@FXML private Label lbl22;
	@FXML private Label lbl23;
	@FXML private Label lbl24;
	@FXML private Label lbl25;
	@FXML private Label lbl26;
	@FXML private Label lbl27;
	@FXML private Label lbl28;
	
	@FXML private Label lbl29;
	@FXML private Label lbl30;
	@FXML private Label lbl31;
	@FXML private Label lbl32;
	@FXML private Label lbl33;
	@FXML private Label lbl34;
	@FXML private Label lbl35;
	@FXML private Label lbl36;
	
	
	
	static boolean emploi1;
	static boolean emploi2;
	static Integer id;
	static Enseignant enseignant;
	public void setEnseignant(Integer id) {
		this.id = id;
		emploi1 =true;
		emploi2 =false;
	}
	public void setEnseignant2(Integer id) {
		this.id = id;
		emploi2 =true;
		emploi1 =false;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		enseignant = GenerationController.enseignants.get(id);
		label.setText(enseignant.getNomEtPrenom());
		if (emploi1) {setEmploi1();}else {setEmploi2();}
		
	}
	@FXML private Button emploi1Button;
	@FXML private Button emploi2Button;
	public void setEmploi1() {
		Label[] listLabel = {lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl15,lbl16,lbl17,lbl18,lbl19,lbl20,lbl21,lbl22,lbl23,lbl24,lbl25,lbl26,lbl27,lbl28,lbl29,lbl30,lbl31,lbl32,lbl33,lbl34,lbl35,lbl36};
		for(Label lbl:listLabel)
			lbl.setText("");
		if (enseignant.getNewDisponibilite(0) != null) {
			lbl1.setText(enseignant.getNewDisponibilite(0).getMatiere().toString());
			lbl2.setText(enseignant.getNewDisponibilite(0).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(1) != null) {
			lbl3.setText(enseignant.getNewDisponibilite(1).getMatiere().toString());
			lbl4.setText(enseignant.getNewDisponibilite(1).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(2) != null) {
			lbl5.setText(enseignant.getNewDisponibilite(2).getMatiere().toString());
			lbl6.setText(enseignant.getNewDisponibilite(2).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(3) != null) {
			lbl7.setText(enseignant.getNewDisponibilite(3).getMatiere().toString());
			lbl8.setText(enseignant.getNewDisponibilite(3).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(4) != null) {
			lbl9.setText(enseignant.getNewDisponibilite(4).getMatiere().toString());
			lbl10.setText(enseignant.getNewDisponibilite(4).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(5) != null) {
			lbl11.setText(enseignant.getNewDisponibilite(5).getMatiere().toString());
			lbl12.setText(enseignant.getNewDisponibilite(5).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(6) != null) {
			lbl13.setText(enseignant.getNewDisponibilite(6).getMatiere().toString());
			lbl14.setText(enseignant.getNewDisponibilite(6).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(7) != null) {
			lbl15.setText(enseignant.getNewDisponibilite(7).getMatiere().toString());
			lbl16.setText(enseignant.getNewDisponibilite(7).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(8) != null) {
			lbl17.setText(enseignant.getNewDisponibilite(8).getMatiere().toString());
			lbl18.setText(enseignant.getNewDisponibilite(8).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(9) != null) {
			lbl19.setText(enseignant.getNewDisponibilite(9).getMatiere().toString());
			lbl20.setText(enseignant.getNewDisponibilite(9).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(10) != null) {
			lbl21.setText(enseignant.getNewDisponibilite(10).getMatiere().toString());
			lbl22.setText(enseignant.getNewDisponibilite(10).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(11) != null) {
			lbl23.setText(enseignant.getNewDisponibilite(11).getMatiere().toString());
			lbl24.setText(enseignant.getNewDisponibilite(11).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(12) != null) {
			lbl25.setText(enseignant.getNewDisponibilite(12).getMatiere().toString());
			lbl26.setText(enseignant.getNewDisponibilite(12).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(13) != null) {
			lbl27.setText(enseignant.getNewDisponibilite(13).getMatiere().toString());
			lbl28.setText(enseignant.getNewDisponibilite(13).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(14) != null) {
			lbl29.setText(enseignant.getNewDisponibilite(14).getMatiere().toString());
			lbl30.setText(enseignant.getNewDisponibilite(14).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(15) != null) {
			lbl31.setText(enseignant.getNewDisponibilite(15).getMatiere().toString());
			lbl32.setText(enseignant.getNewDisponibilite(15).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(16) != null) {
			lbl33.setText(enseignant.getNewDisponibilite(16).getMatiere().toString());
			lbl34.setText(enseignant.getNewDisponibilite(16).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite(17) != null) {
			lbl35.setText(enseignant.getNewDisponibilite(17).getMatiere().toString());
			lbl36.setText(enseignant.getNewDisponibilite(17).getClasse().toString());
		} 
	
		emploi1Button.setDisable(true);
		emploi2Button.setDisable(false);
	}
	public void setEmploi2() {
		Label[] listLabel = {lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl15,lbl16,lbl17,lbl18,lbl19,lbl20,lbl21,lbl22,lbl23,lbl24,lbl25,lbl26,lbl27,lbl28,lbl29,lbl30,lbl31,lbl32,lbl33,lbl34,lbl35,lbl36};
		for(Label lbl:listLabel)
			lbl.setText("");

		if (enseignant.getNewDisponibilite2(0) != null) {
			lbl1.setText(enseignant.getNewDisponibilite2(0).getMatiere().toString());
			lbl2.setText(enseignant.getNewDisponibilite2(0).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(1) != null) {
			lbl3.setText(enseignant.getNewDisponibilite2(1).getMatiere().toString());
			lbl4.setText(enseignant.getNewDisponibilite2(1).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(2) != null) {
			lbl5.setText(enseignant.getNewDisponibilite2(2).getMatiere().toString());
			lbl6.setText(enseignant.getNewDisponibilite2(2).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(3) != null) {
			lbl7.setText(enseignant.getNewDisponibilite2(3).getMatiere().toString());
			lbl8.setText(enseignant.getNewDisponibilite2(3).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(4) != null) {
			lbl9.setText(enseignant.getNewDisponibilite2(4).getMatiere().toString());
			lbl10.setText(enseignant.getNewDisponibilite2(4).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(5) != null) {
			lbl11.setText(enseignant.getNewDisponibilite2(5).getMatiere().toString());
			lbl12.setText(enseignant.getNewDisponibilite2(5).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(6) != null) {
			lbl13.setText(enseignant.getNewDisponibilite2(6).getMatiere().toString());
			lbl14.setText(enseignant.getNewDisponibilite2(6).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(7) != null) {
			lbl15.setText(enseignant.getNewDisponibilite2(7).getMatiere().toString());
			lbl16.setText(enseignant.getNewDisponibilite2(7).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(8) != null) {
			lbl17.setText(enseignant.getNewDisponibilite2(8).getMatiere().toString());
			lbl18.setText(enseignant.getNewDisponibilite2(8).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(9) != null) {
			lbl19.setText(enseignant.getNewDisponibilite2(9).getMatiere().toString());
			lbl20.setText(enseignant.getNewDisponibilite2(9).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(10) != null) {
			lbl21.setText(enseignant.getNewDisponibilite2(10).getMatiere().toString());
			lbl22.setText(enseignant.getNewDisponibilite2(10).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(11) != null) {
			lbl23.setText(enseignant.getNewDisponibilite2(11).getMatiere().toString());
			lbl24.setText(enseignant.getNewDisponibilite2(11).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(12) != null) {
			lbl25.setText(enseignant.getNewDisponibilite2(12).getMatiere().toString());
			lbl26.setText(enseignant.getNewDisponibilite2(12).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(13) != null) {
			lbl27.setText(enseignant.getNewDisponibilite2(13).getMatiere().toString());
			lbl28.setText(enseignant.getNewDisponibilite2(13).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(14) != null) {
			lbl29.setText(enseignant.getNewDisponibilite2(14).getMatiere().toString());
			lbl30.setText(enseignant.getNewDisponibilite2(14).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(15) != null) {
			lbl31.setText(enseignant.getNewDisponibilite2(15).getMatiere().toString());
			lbl32.setText(enseignant.getNewDisponibilite2(15).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(16) != null) {
			lbl33.setText(enseignant.getNewDisponibilite2(16).getMatiere().toString());
			lbl34.setText(enseignant.getNewDisponibilite2(16).getClasse().toString());
		}
		if (enseignant.getNewDisponibilite2(17) != null) {
			lbl35.setText(enseignant.getNewDisponibilite2(17).getMatiere().toString());
			lbl36.setText(enseignant.getNewDisponibilite2(17).getClasse().toString());
		} 
	
	
		emploi2Button.setDisable(true);
		emploi1Button.setDisable(false);
	}
	public void setButton1Click(ActionEvent event) {
		emploi1=true;
		emploi2=false;
		setEmploi1();
	}
	public void setButton2Click(ActionEvent event) {
		emploi2=true;
		emploi1=false;
		setEmploi2();
	}
}
