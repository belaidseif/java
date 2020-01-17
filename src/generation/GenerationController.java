package generation;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

import application.DB;
import application.OutOfHoraireException;
import classe.Classe;
import departement.Departement;
import enseignant.Enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import matiere.Matiere;

public class GenerationController implements Initializable{
	public static Set<String> warning = new HashSet<String>();
	@FXML private Label labelGeneral;
	@FXML private Label lbl1;
	@FXML private Label lbl2;
	@FXML private Label lbl3;
	@FXML private Label lbl4;
	@FXML private Label lbl5;
	@FXML private Label lbl6;
	private DB db = new DB();
	public static List<Classe> classes ;
	public static Map<Integer, Departement> departements ;
	public static Map<Integer, Matiere> matieres;
	public static Map<Integer, Enseignant> enseignants;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Label[] listLabel = {lbl1,lbl2,lbl3,lbl4,lbl5,lbl6};
		classes = new ArrayList<Classe>() ;
		departements = new HashMap<>();
		matieres= new HashMap<>();
		enseignants= new HashMap<>();
		
		getDepartementFromSQL();
		getClassesFromSQL();
		getEnseignantFromSQL();
		getMatiereFromSQL();
		
		setEnseignantOnList();
		try {
			setMatiereOnList();
		} catch (OutOfHoraireException e) {
			warning.add("Le nombre d'horaire de département "+e.getMsg()+" dépasse le limite.");
		}
		
		Generation.generation(classes);
		Genration2.generation(classes);
		if(!warning.isEmpty()) {
			int i=0;
			for(String str:warning) {
				listLabel[i].setText(str);
				i++;
				if(i==6)
					break;
			}
			
		}else {
			labelGeneral.setText("Les emplois sont générés");
		}
		
	}
	
	public void setOKButton(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
	}
	
	public void getClassesFromSQL() {
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from classe")){
			
			while(rs.next())
				classes.add(new Classe(rs.getInt(1),rs.getString(2), departements.get(rs.getInt(3))));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void getDepartementFromSQL() {
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from departement;")){
			
			while(rs.next())
				departements.put(rs.getInt(1),new Departement(rs.getInt(1),rs.getString(2)));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void getMatiereFromSQL() {
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from matiere;")){
			
			while(rs.next())
				matieres.put(rs.getInt(1),new Matiere(rs.getInt(1),rs.getString(2)));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void getEnseignantFromSQL() {
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from enseignant;")){
			
			while(rs.next())
				enseignants.put(rs.getInt(1),new Enseignant(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4)));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void setEnseignantOnList() {
		for(Integer k: matieres.keySet()) {
			
			try(Connection conn = db.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT id_ens FROM `mati_prof` WHERE id_mat = '"+matieres.get(k).getId()+"';")){
				
				while(rs.next())
					matieres.get(k).listEnseignant.add(enseignants.get(rs.getInt(1)));
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}	
		}	
	}
	public void setMatiereOnList() throws OutOfHoraireException {
		for(Integer k:departements.keySet()) {
			try(Connection conn = db.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT SUM(vol_h) FROM `liste_matiere` WHERE id_dep='"+departements.get(k).getId()+"'")){
				if(rs.next())
					if(rs.getDouble(1)>378)
						throw new OutOfHoraireException(departements.get(k));
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		for(Integer k:departements.keySet()) {
			try(Connection conn = db.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT vol_h, id_mat FROM `liste_matiere` WHERE id_dep='"+departements.get(k).getId()+"'")){
				while(rs.next()) {
					if(rs.getDouble(1)==42.0) {
						departements.get(k).listMatier1.put(matieres.get(rs.getInt(2)), 2);
						departements.get(k).listMatier2.put(matieres.get(rs.getInt(2)), 2);
					}else if(rs.getDouble(1)==31.5) {
						if(sommeUnite(departements.get(k).listMatier1) <=16) {
							departements.get(k).listMatier1.put(matieres.get(rs.getInt(2)), 2);
							departements.get(k).listMatier2.put(matieres.get(rs.getInt(2)), 1);
						}else {
							departements.get(k).listMatier1.put(matieres.get(rs.getInt(2)), 1);
							departements.get(k).listMatier2.put(matieres.get(rs.getInt(2)), 2);
						}
					}else if(rs.getDouble(1)==21.0) {
						if(sommeUnite(departements.get(k).listMatier1) <=16) {
							departements.get(k).listMatier1.put(matieres.get(rs.getInt(2)), 2);
						}else if(sommeUnite(departements.get(k).listMatier2) <=16){
							departements.get(k).listMatier2.put(matieres.get(rs.getInt(2)), 2);
						}else {
							departements.get(k).listMatier1.put(matieres.get(rs.getInt(2)), 1);
							departements.get(k).listMatier2.put(matieres.get(rs.getInt(2)), 1);
						}
					}
				}
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	private int sommeUnite(Map<Matiere, Integer> map) {
		int som = 0;
		for(Integer m : map.values()) {
			som+=m;
		}
		return som;
	}
	

}
