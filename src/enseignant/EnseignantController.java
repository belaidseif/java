package enseignant;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import application.DB;
import application.Main;
import classe.Classe;
import classe.InfoClasseController;
import departement.Departement;
import departement.DepartementController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EnseignantController implements Initializable {

	

	private DB db = new DB();
	
	@FXML private Label lbl;
	@FXML private TableView<Enseignant> table;
	@FXML private TableColumn<Enseignant, Integer> id;
	@FXML private TableColumn<Enseignant, String> name;
	
	public ObservableList<Classe> list;
	public static String disponibilite = "000000000";
	
	
	
	@FXML
    private Button clearButtonClick;
    @FXML
    private Button saveButtonClick;
    @FXML
    private Button gererButtonClick;
    
    @FXML
    private TextField nameTF;
    @FXML
    private TextField surnameTF;
    
    private boolean isSetAddClick;
    private boolean isSetEditClick;
    

    private String temp;
	
	private ObservableList<Enseignant> getDataFromSQL(String query) {
		ObservableList<Enseignant> list = FXCollections.observableArrayList();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next())
				list.add(new Enseignant(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	public void home(ActionEvent event) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/application/main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		Main.stage.hide();
		Main.stage.setScene(scene);
		Main.stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<Enseignant, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Enseignant, String>("nomEtPrenom"));
		table.setItems(getDataFromSQL("select * from enseignant;"));
		setAllDisable();
	}
	
	@FXML
    private void setAddClick(Event event){
        setAllEnable();
        isSetAddClick = true;
    }
	
	public void setAllEnable() {
		nameTF.setDisable(false);
		surnameTF.setDisable(false);
		
		gererButtonClick.setDisable(false);
		clearButtonClick.setDisable(false);
		saveButtonClick.setDisable(false);
	}
	
	public void setAllDisable() {
		nameTF.setDisable(true);
		surnameTF.setDisable(true);
		
		gererButtonClick.setDisable(true);
		clearButtonClick.setDisable(true);
		saveButtonClick.setDisable(true);
	}
	
	@FXML
	public void setSaveClick() {
		
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			if(isSetAddClick && !nameTF.getText().isEmpty() && !surnameTF.getText().isEmpty()) {
				
				stmt.executeUpdate("insert into enseignant(`name`,`surname`,`disponibilite`) values ('"+nameTF.getText()+"', '"+surnameTF.getText()+"','"+disponibilite+"');");
			}else if(isSetEditClick && !nameTF.getText().isEmpty() && !surnameTF.getText().isEmpty()) {
				stmt.executeUpdate("update enseignant set name='"+nameTF.getText()+"', surname = '"+surnameTF.getText()+"', disponibilite = '"+disponibilite+"' where id= '"+temp+"';");
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		setAllClear();
        setAllDisable();
        table.setItems(getDataFromSQL("SELECT * FROM enseignant;"));
        isSetEditClick=false;
        isSetAddClick = false;
        lbl.setText("");
        disponibilite ="000000000";
		
	}
	public void setAllClear() {
		nameTF.clear();
		surnameTF.clear();
	}
	
	@FXML
    private void setClearClick(Event event){
        setAllClear();
        setAllDisable();
        isSetEditClick=false;
        isSetAddClick = false;
        lbl.setText("");
    }
	
	@FXML
    private void setRefreshClick(Event event){
        table.setItems(getDataFromSQL("SELECT * FROM enseignant;"));
        lbl.setText("");
        
    }
	
	@FXML
	public void setEditClick(ActionEvent event) {
		try {
		Enseignant getSelectedRow = table.getSelectionModel().getSelectedItem();
		temp = getSelectedRow.getId().toString();
		setAllEnable();
		nameTF.setText(getSelectedRow.getNom());
		surnameTF.setText(getSelectedRow.getPrenom());
		isSetEditClick = true;
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi un enseignant");
		}
	}
	
	@FXML
	public void setDeleteClick(ActionEvent event) {
		try {
			Enseignant getSelectedRow = table.getSelectionModel().getSelectedItem();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate("delete from enseignant where id="+getSelectedRow.getId()+";");
			table.setItems(getDataFromSQL("select * from enseignant;"));
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi un enseignant");
		}
		
	}
		public void gererDis(ActionEvent event) {
			try {
				
				Parent root = FXMLLoader.load(getClass().getResource("/enseignant/dispo.fxml"));
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				Stage stage = new Stage();
				
				stage.setScene(scene);
				stage.show();
				
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		public void setViewClick(ActionEvent event) {
			try {
				Enseignant getSelectedRow = table.getSelectionModel().getSelectedItem();
				if(getSelectedRow == null)
					lbl.setText("choisi un enseignant");
				else {
					InfoEnsController ic = new InfoEnsController();
					ic.setEnseignant(getSelectedRow);
				Parent root = FXMLLoader.load(getClass().getResource("/enseignant/info.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				Stage stage = new Stage();
				
				stage.setScene(scene);
				stage.show();
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		
		public void setEmploi1Click(ActionEvent event) {
			try {
				Enseignant getSelectedRow = table.getSelectionModel().getSelectedItem();
				if(getSelectedRow == null)
					lbl.setText("choisi un enseignant");
				else {
					Emploi1EnseigantController ic = new Emploi1EnseigantController();
					ic.setEnseignant(getSelectedRow.getId());
				Parent root = FXMLLoader.load(getClass().getResource("/enseignant/emploi1.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				Stage stage = new Stage();
				
				stage.setScene(scene);
				stage.show();
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		public void setEmploi2Click(ActionEvent event) {
			try {
				Enseignant getSelectedRow = table.getSelectionModel().getSelectedItem();
				if(getSelectedRow == null)
					lbl.setText("choisi un enseignant");
				else {
					Emploi1EnseigantController ic = new Emploi1EnseigantController();
					ic.setEnseignant2(getSelectedRow.getId());
				Parent root = FXMLLoader.load(getClass().getResource("/enseignant/emploi1.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				Stage stage = new Stage();
				
				stage.setScene(scene);
				stage.show();
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		


}
