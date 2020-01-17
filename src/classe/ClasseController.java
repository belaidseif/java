package classe;

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
import departement.Departement;
import departement.DepartementController;
import enseignant.Enseignant;
import enseignant.InfoEnsController;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClasseController implements Initializable {
	

	private DB db = new DB();
	@FXML private ChoiceBox<Departement> choiceBox;
	@FXML private Label lbl;
	@FXML private TableView<Classe> table;
	@FXML private TableColumn<Classe, Integer> id;
	@FXML private TableColumn<Classe, String> name;
	@FXML private TableColumn<Classe, Departement> departement;
	public ObservableList<Classe> list;
	public Map<Integer, Departement> map = new HashMap<Integer, Departement>();
	
	
	@FXML
    private Button clearButtonClick;
    @FXML
    private Button saveButtonClick;
    
    
    @FXML
    private TextField nameTF;
    
    private boolean isSetAddClick;
    private boolean isSetEditClick;
    

    private String temp;
	
	private ObservableList<Classe> getDataFromSQL(String query) {
		ObservableList<Classe> list = FXCollections.observableArrayList();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next())
				list.add(new Classe(rs.getInt(1),rs.getString(2), map.get(rs.getInt(3))));
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
		id.setCellValueFactory(new PropertyValueFactory<Classe, Integer>("id"));
		name.setCellValueFactory(new PropertyValueFactory<Classe, String>("name"));
		departement.setCellValueFactory(new PropertyValueFactory<Classe, Departement>("departement"));
		DepartementController dc = new DepartementController();
		ObservableList<Departement> listDC = dc.getDataFromSQL("select * from departement;");
		for(Departement d : listDC)
			map.put(d.getId(),d);
		table.setItems(getDataFromSQL("select * from classe;"));
		choiceBox.setItems(listDC);
		setAllDisable();
		
	}
	
	@FXML
    private void setAddClick(Event event){
        setAllEnable();
        isSetAddClick = true;
    }
	
	public void setAllEnable() {
		nameTF.setDisable(false);
		choiceBox.setDisable(false);
		
		clearButtonClick.setDisable(false);
		saveButtonClick.setDisable(false);
	}
	
	public void setAllDisable() {
		nameTF.setDisable(true);
		choiceBox.setDisable(true);
		
		clearButtonClick.setDisable(true);
		saveButtonClick.setDisable(true);
	}
	
	@FXML
	public void setSaveClick() {
		
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			if(isSetAddClick && !nameTF.getText().isEmpty() && choiceBox.getValue()!=null) {
				
				stmt.executeUpdate("insert into classe(`name`,`id_dep`) values ('"+nameTF.getText()+"', '"+choiceBox.getValue().getId()+"');");
			}else if(isSetEditClick && !nameTF.getText().isEmpty() && choiceBox.getValue()!=null) {
				stmt.executeUpdate("update classe set name='"+nameTF.getText()+"', id_dep = '"+choiceBox.getValue().getId()+"' where id= '"+temp+"';");
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		setAllClear();
        setAllDisable();
        table.setItems(getDataFromSQL("SELECT * FROM classe;"));
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
        table.setItems(getDataFromSQL("SELECT * FROM classe;"));
        lbl.setText("");
        
    }
	
	@FXML
	public void setEditClick(ActionEvent event) {
		try {
		Classe getSelectedRow = table.getSelectionModel().getSelectedItem();
		temp = getSelectedRow.getId().toString();
		setAllEnable();
		nameTF.setText(getSelectedRow.getName());
		choiceBox.setValue(getSelectedRow.getDepartement());
		isSetEditClick = true;
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi une classe");
		}
	}
	
	@FXML
	public void setDeleteClick(ActionEvent event) {
		try {
			Classe getSelectedRow = table.getSelectionModel().getSelectedItem();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate("delete from classe where id="+getSelectedRow.getId()+";");
			table.setItems(getDataFromSQL("select * from classe;"));
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi une classe");
		}
		
	}
	public void setViewclick(ActionEvent event) {
		try {
			Classe getSelectedRow = table.getSelectionModel().getSelectedItem();
			if(getSelectedRow == null)
				lbl.setText("choisi une classe");
			else {
				InfoClasseController ic = new InfoClasseController();
				ic.setClasse(getSelectedRow.getId());
			Parent root = FXMLLoader.load(getClass().getResource("/classe/viewClasse.fxml"));
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
	public void setEmploi2click(ActionEvent event) {
		try {
			Classe getSelectedRow = table.getSelectionModel().getSelectedItem();
			if(getSelectedRow == null)
				lbl.setText("choisi une classe");
			else {
				InfoClasseController ic = new InfoClasseController();
				ic.setClasse2(getSelectedRow.getId());
			Parent root = FXMLLoader.load(getClass().getResource("/classe/viewClasse.fxml"));
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
