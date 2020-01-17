package enseignant;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;
import matiere.Matiere;

public class InfoEnsController implements Initializable {
	private DB db = new DB();
	@FXML Label lbl1;
	@FXML Label lbl2;
	@FXML
	Rectangle rectangle1;
	@FXML
	Rectangle rectangle2;
	@FXML
	Rectangle rectangle3;
	@FXML
	Rectangle rectangle4;
	@FXML
	Rectangle rectangle5;
	@FXML
	Rectangle rectangle6;
	@FXML
	Rectangle rectangle7;
	@FXML
	Rectangle rectangle8;
	@FXML
	Rectangle rectangle9;
	@FXML
	ChoiceBox<Matiere> choiceBox;
	@FXML
	ListView<Matiere> listView;
	public static Enseignant enseignant;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lbl1.setText(enseignant.getNomEtPrenom());
		if(enseignant.getDisponibilite(0)==false)
			rectangle1.setVisible(false);
		if(enseignant.getDisponibilite(1)==false)
			rectangle2.setVisible(false);
		if(enseignant.getDisponibilite(2)==false)
			rectangle3.setVisible(false);
		if(enseignant.getDisponibilite(3)==false)
			rectangle4.setVisible(false);
		if(enseignant.getDisponibilite(4)==false)
			rectangle5.setVisible(false);
		if(enseignant.getDisponibilite(5)==false)
			rectangle6.setVisible(false);
		if(enseignant.getDisponibilite(6)==false)
			rectangle7.setVisible(false);
		if(enseignant.getDisponibilite(7)==false)
			rectangle8.setVisible(false);
		if(enseignant.getDisponibilite(8)==false)
			rectangle9.setVisible(false);
		choiceBox.setItems(getDataFromSQL("select * from matiere;"));
		listView.setItems(getDataFromSQL("select m.id, name from matiere m,"
				+ " `mati_prof` p where m.id=p.id_mat and p.id_ens = '"+enseignant.getId()+"'"));
		
		
	}
	
	private ObservableList<Matiere> getDataFromSQL(String query) {
		ObservableList<Matiere> list = FXCollections.observableArrayList();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next())
				list.add(new Matiere(rs.getInt(1),rs.getString(2)));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	
	public void ajouterClick(ActionEvent event ) {
		lbl2.setText("");
		Matiere matiere = choiceBox.getValue();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate("insert into mati_prof (`id_mat`,`id_ens`) values ('"+matiere.getId()+"', '"+enseignant.getId()+"');");	
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		listView.setItems(getDataFromSQL("select m.id, name from matiere m,"
				+ " `mati_prof` p where m.id=p.id_mat and p.id_ens = '"+enseignant.getId()+"'"));
	}
	public void supprimerClick(ActionEvent event) {
		Matiere matiere = listView.getSelectionModel().getSelectedItem();
		
		if(matiere != null) {
			lbl2.setText("");
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate("delete from `mati_prof` where `id_mat`= '"+matiere.getId()+"' and `id_ens`='"+enseignant.getId()+"'");	
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		}else {
			lbl2.setText("choisi une matiere");
		}
		listView.setItems(getDataFromSQL("select m.id, name from matiere m,"
				+ " `mati_prof` p where m.id=p.id_mat and p.id_ens = '"+enseignant.getId()+"'"));
	}
}
