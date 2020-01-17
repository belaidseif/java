package classe;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import generation.GenerationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InfoClasseController implements Initializable{
	@FXML
	private Label titreLabel;
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
	@FXML private Button emploi1Button;
	@FXML private Button emploi2Button;
	static boolean emploi1;
	static boolean emploi2;
	static Integer id;
	static Classe classe =null;
	public void setClasse(Integer id) {
		this.id = id;
		emploi1 =true;
		emploi2=false;
	}
	public void setClasse2(Integer id) {
		this.id = id;
		emploi2 =true;
		emploi1=false;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		Optional<Classe> optionaleClasse = GenerationController.classes.stream().filter(c->c.getId()==id).findAny();
		classe = optionaleClasse.get();
		titreLabel.setText(classe.getDepartement().getName()+"-"+classe.getName());
		
		if (emploi1) {setEmploi1(classe);}else {setEmploi2(classe);}
		
		
	}
	
	public void setEmploi1(Classe classe) {
		Label[] listLabel = {lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl15,lbl16,lbl17,lbl18,lbl19,lbl20,lbl21,lbl22,lbl23,lbl24,lbl25,lbl26,lbl27,lbl28,lbl29,lbl30,lbl31,lbl32,lbl33,lbl34,lbl35,lbl36};
		for(Label lbl:listLabel)
			lbl.setText("");
		if (classe.emploi1[0] != null) {
			lbl1.setText(classe.emploi1[0].getMatiere1().getName());
			lbl2.setText(classe.emploi1[0].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[0].getMatiere2() != null)
			lbl3.setText(classe.emploi1[0].getMatiere2().getName());
			if (classe.emploi1[0].getEnseignant2() != null)
				lbl4.setText(classe.emploi1[0].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[1] != null) {
			lbl5.setText(classe.emploi1[1].getMatiere1().getName());
			lbl6.setText(classe.emploi1[1].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[1].getMatiere2() != null)
			lbl7.setText(classe.emploi1[1].getMatiere2().getName());
			if (classe.emploi1[1].getEnseignant2() != null)
				lbl8.setText(classe.emploi1[1].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[2] != null) {
			lbl9.setText(classe.emploi1[2].getMatiere1().getName());
			lbl10.setText(classe.emploi1[2].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[2].getMatiere2() != null)
			lbl11.setText(classe.emploi1[2].getMatiere2().getName());
			if (classe.emploi1[2].getEnseignant2() != null)
				lbl12.setText(classe.emploi1[2].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[3] != null) {
			lbl13.setText(classe.emploi1[3].getMatiere1().getName());
			lbl14.setText(classe.emploi1[3].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[3].getMatiere2() != null)
			lbl15.setText(classe.emploi1[3].getMatiere2().getName());
			if (classe.emploi1[3].getEnseignant2() != null)
				lbl16.setText(classe.emploi1[3].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[4] != null) {
			lbl17.setText(classe.emploi1[4].getMatiere1().getName());
			lbl18.setText(classe.emploi1[4].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[4].getMatiere2() != null)
			lbl19.setText(classe.emploi1[4].getMatiere2().getName());
			if (classe.emploi1[4].getEnseignant2() != null)
				lbl20.setText(classe.emploi1[4].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[5] != null) {
			lbl21.setText(classe.emploi1[5].getMatiere1().getName());
			lbl22.setText(classe.emploi1[5].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[5].getMatiere2() != null)
			lbl23.setText(classe.emploi1[5].getMatiere2().getName());
			if (classe.emploi1[5].getEnseignant2() != null)
				lbl24.setText(classe.emploi1[5].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[6] != null) {
			lbl25.setText(classe.emploi1[6].getMatiere1().getName());
			lbl26.setText(classe.emploi1[6].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[6].getMatiere2() != null)
			lbl27.setText(classe.emploi1[6].getMatiere2().getName());
			if (classe.emploi1[6].getEnseignant2() != null)
				lbl28.setText(classe.emploi1[6].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[7] != null) {
			lbl29.setText(classe.emploi1[7].getMatiere1().getName());
			lbl30.setText(classe.emploi1[7].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[7].getMatiere2() != null)
			lbl31.setText(classe.emploi1[7].getMatiere2().getName());
			if (classe.emploi1[7].getEnseignant2() != null)
				lbl32.setText(classe.emploi1[7].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi1[8] != null) {
			lbl33.setText(classe.emploi1[8].getMatiere1().getName());
			lbl34.setText(classe.emploi1[8].getEnseignant1().getNomEtPrenom());
			if(classe.emploi1[8].getMatiere2() != null)
			lbl35.setText(classe.emploi1[8].getMatiere2().getName());
			if (classe.emploi1[8].getEnseignant2() != null)
				lbl36.setText(classe.emploi1[8].getEnseignant2().getNomEtPrenom());
		}
		
		emploi1Button.setDisable(true);
		emploi2Button.setDisable(false);
	
	}
	public void setEmploi2(Classe classe) {
		Label[] listLabel = {lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12,lbl13,lbl14,lbl15,lbl16,lbl17,lbl18,lbl19,lbl20,lbl21,lbl22,lbl23,lbl24,lbl25,lbl26,lbl27,lbl28,lbl29,lbl30,lbl31,lbl32,lbl33,lbl34,lbl35,lbl36};
		for(Label lbl:listLabel)
			lbl.setText("");
		if (classe.emploi2[0] != null) {
			lbl1.setText(classe.emploi2[0].getMatiere1().getName());
			lbl2.setText(classe.emploi2[0].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[0].getMatiere2() != null)
			lbl3.setText(classe.emploi2[0].getMatiere2().getName());
			if (classe.emploi2[0].getEnseignant2() != null)
				lbl4.setText(classe.emploi2[0].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[1] != null) {
			lbl5.setText(classe.emploi2[1].getMatiere1().getName());
			lbl6.setText(classe.emploi2[1].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[1].getMatiere2() != null)
			lbl7.setText(classe.emploi2[1].getMatiere2().getName());
			if (classe.emploi2[1].getEnseignant2() != null)
				lbl8.setText(classe.emploi2[1].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[2] != null) {
			lbl9.setText(classe.emploi2[2].getMatiere1().getName());
			lbl10.setText(classe.emploi2[2].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[2].getMatiere2() != null)
			lbl11.setText(classe.emploi2[2].getMatiere2().getName());
			if (classe.emploi2[2].getEnseignant2() != null)
				lbl12.setText(classe.emploi2[2].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[3] != null) {
			lbl13.setText(classe.emploi2[3].getMatiere1().getName());
			lbl14.setText(classe.emploi2[3].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[3].getMatiere2() != null)
			lbl15.setText(classe.emploi2[3].getMatiere2().getName());
			if (classe.emploi2[3].getEnseignant2() != null)
				lbl16.setText(classe.emploi2[3].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[4] != null) {
			lbl17.setText(classe.emploi2[4].getMatiere1().getName());
			lbl18.setText(classe.emploi2[4].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[4].getMatiere2() != null)
			lbl19.setText(classe.emploi2[4].getMatiere2().getName());
			if (classe.emploi2[4].getEnseignant2() != null)
				lbl20.setText(classe.emploi2[4].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[5] != null) {
			lbl21.setText(classe.emploi2[5].getMatiere1().getName());
			lbl22.setText(classe.emploi2[5].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[5].getMatiere2() != null)
			lbl23.setText(classe.emploi2[5].getMatiere2().getName());
			if (classe.emploi2[5].getEnseignant2() != null)
				lbl24.setText(classe.emploi2[5].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[6] != null) {
			lbl25.setText(classe.emploi2[6].getMatiere1().getName());
			lbl26.setText(classe.emploi2[6].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[6].getMatiere2() != null)
			lbl27.setText(classe.emploi2[6].getMatiere2().getName());
			if (classe.emploi2[6].getEnseignant2() != null)
				lbl28.setText(classe.emploi2[6].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[7] != null) {
			lbl29.setText(classe.emploi2[7].getMatiere1().getName());
			lbl30.setText(classe.emploi2[7].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[7].getMatiere2() != null)
			lbl31.setText(classe.emploi2[7].getMatiere2().getName());
			if (classe.emploi2[7].getEnseignant2() != null)
				lbl32.setText(classe.emploi2[7].getEnseignant2().getNomEtPrenom());
		}
		if (classe.emploi2[8] != null) {
			lbl33.setText(classe.emploi2[8].getMatiere1().getName());
			lbl34.setText(classe.emploi2[8].getEnseignant1().getNomEtPrenom());
			if(classe.emploi2[8].getMatiere2() != null)
			lbl35.setText(classe.emploi2[8].getMatiere2().getName());
			if (classe.emploi2[8].getEnseignant2() != null)
				lbl36.setText(classe.emploi2[8].getEnseignant2().getNomEtPrenom());
		} 
		emploi2Button.setDisable(true);
		emploi1Button.setDisable(false);
	}
	public void setButton1Click(ActionEvent event) {
		emploi1=true;
		emploi2=false;
		setEmploi1(classe);
	}
	public void setButton2Click(ActionEvent event) {
		emploi2=true;
		emploi1=false;
		setEmploi2(classe);
	}

}
