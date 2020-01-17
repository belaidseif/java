package departement;

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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import matiere.Matiere;
import matiere.MatiereController;

public class InfoDepController implements Initializable{

	public static Departement departement;
	private DB db = new DB();
	@FXML private Label lbl;
	@FXML private Label lbl1;
	@FXML private TableView<ListeMatiere> table;
	@FXML private TableColumn<ListeMatiere, Matiere> matiere;
	@FXML private TableColumn<ListeMatiere, Double> volumeHoraire;
	public ObservableList<ListeMatiere> list;
	
	
	@FXML
    private Button clearButtonClick;
    @FXML
    private Button saveButtonClick;
    
    
    @FXML
    private ChoiceBox<Matiere> choiceBox1;
    @FXML
    private ChoiceBox<Double> choiceBox2;
    
    
    private boolean isSetAddClick;
    private boolean isSetEditClick;
    

    private String temp;
	
	public ObservableList<ListeMatiere> getDataFromSQL(String query) {
		ObservableList<ListeMatiere> list = FXCollections.observableArrayList();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while(rs.next())
				list.add(new ListeMatiere(rs.getInt(1),new Matiere(rs.getInt(2),rs.getString(3)), Double.parseDouble(rs.getString(4))));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		matiere.setCellValueFactory(new PropertyValueFactory<ListeMatiere, Matiere>("matiere"));
		volumeHoraire.setCellValueFactory(new PropertyValueFactory<ListeMatiere, Double>("volumeHoraire"));
		table.setItems(getDataFromSQL("select l.id, m.id, m.name, `vol_h` from matiere m, `liste_matiere` l where m.id=l.id_mat and id_dep = '"+departement.getId()+"'"));
		setAllDisable();
		MatiereController mc = new MatiereController();
		choiceBox1.setItems(mc.getDataFromSQL("select * from matiere;"));
		choiceBox2.setItems(FXCollections.observableArrayList(21.0,31.5,42.0));
		lbl1.setText(departement.getName());
		
	}
	
	@FXML
    private void setAddClick(Event event){
        setAllEnable();
        isSetAddClick = true;
    }
	
	public void setAllEnable() {
		choiceBox1.setDisable(false);
		choiceBox2.setDisable(false);
		
		clearButtonClick.setDisable(false);
		saveButtonClick.setDisable(false);
	}
	
	public void setAllDisable() {
		choiceBox1.setDisable(false);
		choiceBox2.setDisable(false);
		
		clearButtonClick.setDisable(true);
		saveButtonClick.setDisable(true);
	}
	
	@FXML
	public void setSaveClick() {
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			if(isSetAddClick && choiceBox1.getValue()!= null && choiceBox2.getValue()!= null ) {
				
				stmt.executeUpdate("insert into `liste_matiere` (`vol_h`, `id_dep`, `id_mat`) values ('"+choiceBox2.getValue()+"','"+departement.getId()+"', '"+choiceBox1.getValue().getId()+"');");
			}else if(isSetEditClick && choiceBox1.getValue() != null && choiceBox2.getValue() != null) {
				stmt.executeUpdate("update `liste_matiere` set `id_mat`='"+choiceBox1.getValue().getId()+"', `vol_h`='"+choiceBox2.getValue()+"' where id= '"+temp+"';");
			}
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
		setAllClear();
        setAllDisable();
        table.setItems(getDataFromSQL("select l.id, m.id, m.name, `vol_h` from matiere m, `liste_matiere` l where m.id=l.id_mat and id_dep = '"+departement.getId()+"'"));
        isSetEditClick=false;
        isSetAddClick = false;
        lbl.setText("");
		
	}
	public void setAllClear() {
		
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
        table.setItems(getDataFromSQL("select l.id, m.id, m.name, `vol_h` from matiere m, `liste_matiere` l where m.id=l.id_mat and id_dep = '"+departement.getId()+"'"));
        lbl.setText("");
        
    }
	
	@FXML
	public void setEditClick(ActionEvent event) {
		try {
		ListeMatiere getSelectedRow = table.getSelectionModel().getSelectedItem();
		temp = getSelectedRow.getId().toString();
		setAllEnable();
		choiceBox1.setValue(getSelectedRow.getMatiere());
		choiceBox2.setValue(getSelectedRow.getVolumeHoraire());
		
		isSetEditClick = true;
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi une matière");
		}
	}
	
	@FXML
	public void setDeleteClick(ActionEvent event) {
		try {
		ListeMatiere getSelectedRow = table.getSelectionModel().getSelectedItem();
		try(Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()){
			stmt.executeUpdate("delete from `liste_matiere` where id="+getSelectedRow.getId()+";");
			table.setItems(getDataFromSQL("select l.id, m.id, m.name, `vol_h` from matiere m, `liste_matiere` l where m.id=l.id_mat and id_dep = '"+departement.getId()+"'"));
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		lbl.setText("");
		}catch(NullPointerException e) {
			lbl.setText("choisi une matière");
		}
		
	}
	
	public void setDepartement(Departement d) {
		departement = d;
	}
}
