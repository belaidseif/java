package departement;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.DB;
import application.Main;
import enseignant.Enseignant;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class DepartementController implements Initializable {
	private DB db = new DB();
	@FXML private Label lbl;
	@FXML private TableView<Departement> table;
	@FXML private TableColumn<Departement, Integer> id;
	@FXML private TableColumn<Departement, String> name;
	public ObservableList<Departement> list;
	
	
	@FXML
    private Button clearButtonClick;
    @FXML
    private Button saveButtonClick;
    
    
    @FXML
    private TextField nameTF;
    
    private boolean isSetAddClick;
    private boolean isSetEditClick;
    

    private String temp;
	
	public ObservableList<Departement> getDataFromSQL(String query) {
		ObservableList<Departement> list = FXCollections.observableArrayList();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next())
				list.add(new Departement(rs.getInt(1),rs.getString(2)));
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
		id.setCellValueFactory(new PropertyValueFactory<Departement, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Departement, String>("name"));
		table.setItems(getDataFromSQL("select * from departement;"));
		setAllDisable();
		
	}
	
	@FXML
    private void setAddClick(Event event){
        setAllEnable();
        isSetAddClick = true;
    }
	
	public void setAllEnable() {
		nameTF.setDisable(false);
		
		clearButtonClick.setDisable(false);
		saveButtonClick.setDisable(false);
	}
	
	public void setAllDisable() {
		nameTF.setDisable(true);
		
		clearButtonClick.setDisable(true);
		saveButtonClick.setDisable(true);
	}
	
	@FXML
	public void setSaveClick() {
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			if(isSetAddClick && !nameTF.getText().isEmpty()) {
				
				stmt.executeUpdate("insert into departement(`name`) values ('"+nameTF.getText()+"');");
			}else if(isSetEditClick && !nameTF.getText().isEmpty()) {
				stmt.executeUpdate("update departement set name='"+nameTF.getText()+"' where id= '"+temp+"';");
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		setAllClear();
        setAllDisable();
        table.setItems(getDataFromSQL("SELECT * FROM departement;"));
        isSetEditClick=false;
        isSetAddClick = false;
        lbl.setText("");
		
	}
	public void setAllClear() {
		nameTF.clear();
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
        table.setItems(getDataFromSQL("SELECT * FROM departement;"));
        lbl.setText("");
        
    }
	
	@FXML
	public void setEditClick(ActionEvent event) {
		try {
		Departement getSelectedRow = table.getSelectionModel().getSelectedItem();
		temp = getSelectedRow.getId().toString();
		setAllEnable();
		nameTF.setText(getSelectedRow.getName());
		isSetEditClick = true;
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi un département");
		}
	}
	
	@FXML
	public void setDeleteClick(ActionEvent event) {
		try {
		Departement getSelectedRow = table.getSelectionModel().getSelectedItem();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate("delete from departement where id="+getSelectedRow.getId()+";");
			table.setItems(getDataFromSQL("select * from departement;"));
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi un departement");
		}
		
	}
	public void setViewClick(ActionEvent event) {
		try {
			Departement getSelectedRow = table.getSelectionModel().getSelectedItem();
			if(getSelectedRow == null)
				lbl.setText("choisi un département");
			else {
				InfoDepController ic = new InfoDepController();
				ic.setDepartement(getSelectedRow);
				
			Parent root = FXMLLoader.load(getClass().getResource("/departement/info.fxml"));
		
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
