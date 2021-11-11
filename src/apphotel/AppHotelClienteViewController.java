/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import entidades.Persona;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AppHotelClienteViewController implements Initializable {

    @FXML
    private TableView<Persona> tableViewCliente;
    @FXML
    private TableColumn<Persona, String> columnDNI;
    @FXML
    private TableColumn<Persona, String> columnNombre;
    @FXML
    private TableColumn<Persona, String> columnDireccion;
    @FXML
    private TableColumn<Persona, String> columnLocalidad;
    @FXML
    private TableColumn<Persona, String> columnProvincia;
    @FXML
    private TableColumn<Persona, Integer> columnTelefono;

     private EntityManager entityManager;
     private Pane rootPrincipalView;
     private Persona persona;
    @FXML
    private Button buttonSalir;
    @FXML
    private AnchorPane viewCliente;
     
    /**
     * Initializes the controller class.
     */
    
    
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        columnDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        columnProvincia.setCellValueFactory(
            cellData->{
            SimpleStringProperty property = new SimpleStringProperty();
            if(cellData.getValue().getProvincia()!=null)
            {
                property.setValue(cellData.getValue().getProvincia().getNombre());
            }
            return property;
            
        });
        
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        
        
    }    
    
    //debemos crear un vinculo entre el controlador y el modelo.
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    } 
    
    
    public void cargarTodasPersonas()
    {
        Query queryPersonaFindAll = entityManager.createNamedQuery("Persona.findAll");
        List<Persona>listPersona=queryPersonaFindAll.getResultList();
        tableViewCliente.setItems(FXCollections.observableArrayList(listPersona));
    }
    
    
   
     // Metodo para asignarle el objeto correspondiente desde la otra clase controladora
    public void setRootPrincipalView(Pane rootPrincipalView){
        this.rootPrincipalView = rootPrincipalView;
    }

    @FXML
    private void onActionSalir(ActionEvent event) 
    {
        //Borramos la escena actual de habitacionesView
        StackPane rootMain = (StackPane) viewCliente.getScene().getRoot();
        rootMain.getChildren().remove(viewCliente);
        
        //Hacemos visible appHotelPrincipalView
        rootPrincipalView.setVisible(true);
    }
    
  
}
