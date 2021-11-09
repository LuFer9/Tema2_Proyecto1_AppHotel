/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import entidades.Persona;
import entidades.Provincia;
import entidades.ReservaHabitacion;
import entidades.TipoHabitacion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class AppHotelReservaHabitacionesViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Pane rootPrincipalView;
    @FXML
    private TextField textFieldDNI;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldDireccion;
    @FXML
    private TextField textFieldLocalidad;
    @FXML
    private ComboBox<Provincia> comboBoxProvincia;
    @FXML
    private DatePicker datePickerLlegada;
    @FXML
    private DatePicker datePickerSalida;
    @FXML
    private Spinner<Integer> spinnerNumHab;
    @FXML
    private ComboBox<TipoHabitacion> comboBoxTipoHab;
    @FXML
    private CheckBox checkBoxFumador;
    @FXML
    private Button buttonAceptar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonLimpiar;
    @FXML
    private RadioButton radioButtonAlojamiento;
    @FXML
    private RadioButton radioButtonMediaPension;
    @FXML
    private RadioButton radioButtonPensionCompleta;
    @FXML
    private ToggleGroup toggleGroupRegimen;
    
    private EntityManager entityManager;
    private Persona persona;
    @FXML
    private AnchorPane viewReservaHabitacion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Query queryPersonaFindAll = entityManager.createNamedQuery("Persona.findByDni");
        List listPersona = queryPersonaFindAll.getResultList();
        */
            textFieldDNI.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldProperty, Boolean newProperty) -> {
                
                if(newProperty){
                    
                    System.out.println("changing");
                }
                else{
                    
                   
                    try{    
                    
                        persona = entityManager.find(Persona.class, textFieldDNI.getText());


                        textFieldNombre.setText(persona.getNombre());
                        textFieldDireccion.setText(persona.getDireccion());
                        textFieldLocalidad.setText(persona.getLocalidad());

                        //Provincia consulta para combo box
                        Query queryProvincialFindAll = entityManager.createNamedQuery("Provincia.findAll");
                        List listProvincia = queryProvincialFindAll.getResultList();
                        comboBoxProvincia.setItems(FXCollections.observableList(listProvincia));

                        if(persona.getProvincia() != null){

                            comboBoxProvincia.setValue(persona.getProvincia());
                        }

                        //Determinamos como se muestran los datos de provincia en este caso CADIZ, nombre
                        comboBoxProvincia.setCellFactory(
                                (ListView<Provincia> l)-> new ListCell<Provincia>(){
                                    @Override
                                    protected void updateItem(Provincia provincia, boolean empty){
                                        super.updateItem(provincia, empty);
                                        if (provincia == null || empty){
                                            setText("");
                                        } else {
                                            setText(provincia.getNombre());
                                        }
                                    }
                                });  

                        //Determinamos como se muestra el comboBox cuando no se este seleccionando nada de su lista
                        comboBoxProvincia.setConverter(new StringConverter<Provincia>(){
                            @Override
                            public String toString(Provincia provincia) {
                                if(provincia == null){
                                    return "";
                                }
                                else{
                                   return provincia.getNombre();
                                }
                            }

                            @Override
                            public Provincia fromString(String string) {
                                return null;
                            }


                        });
                        
                    }
                    catch(NullPointerException e){
                        
                        System.out.println("No existe ninguna persona con ese DNI");
                    }
                }
            });
        
          
    }    
    
    // Metodo para asignarle el objeto correspondiente desde la otra clase controladora
    public void setRootPrincipalView(Pane rootPrincipalView){
        this.rootPrincipalView = rootPrincipalView;
    }
    

    @FXML
    private void onActionButtonAceptar(ActionEvent event) 
    {
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) 
    {

        //Borramos la escena actual de habitacionesView
        StackPane rootMain = (StackPane) viewReservaHabitacion.getScene().getRoot();
        rootMain.getChildren().remove(viewReservaHabitacion);
        
        //Hacemos visible appHotelPrincipalView
        rootPrincipalView.setVisible(true);
    }

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) 
    {
        
    }
    
    public void setPersona(EntityManager em, Persona persona){
        
        this.entityManager = em;
        this.persona = persona;
    }
    
    
    public void mostrarDatos(){
        
        //Provincia consulta para combo box
        Query queryProvincialFindAll = entityManager.createNamedQuery("Provincia.findAll");
        List listProvincia = queryProvincialFindAll.getResultList();
        comboBoxProvincia.setItems(FXCollections.observableList(listProvincia));
                    
        //Determinamos como se muestran los datos de provincia en este caso CADIZ, nombre
        comboBoxProvincia.setCellFactory(
                (ListView<Provincia> l)-> new ListCell<Provincia>(){
            @Override
            protected void updateItem(Provincia provincia, boolean empty){
                super.updateItem(provincia, empty);
                if (provincia == null || empty){
                    setText("");
                } else {
                    setText(provincia.getNombre());
                }
            }
        });  
                    
        //Determinamos como se muestra el comboBox cuando no se este seleccionando nada de su lista
        comboBoxProvincia.setConverter(new StringConverter<Provincia>(){
        @Override
            public String toString(Provincia provincia) {
                if(provincia == null){
                    return "";
                }
                else{
                return provincia.getNombre();
                }
            }

            @Override
            public Provincia fromString(String string) {
                return null;
            }
                        
                        
        });
        
        
        //Mostramos los tipos de habitaciones
        //Provincia consulta para combo box
        Query queryTipoHabitacionFindAll = entityManager.createNamedQuery("TipoHabitacion.findAll");
        List listHabitacion = queryTipoHabitacionFindAll.getResultList();
        comboBoxTipoHab.setItems(FXCollections.observableList(listHabitacion));
                    
        //Determinamos como se muestran los datos de provincia en este caso CADIZ, nombre
        comboBoxTipoHab.setCellFactory(
                (ListView<TipoHabitacion> l)-> new ListCell<TipoHabitacion>(){
            @Override
            protected void updateItem(TipoHabitacion tipoHab, boolean empty){
                super.updateItem(tipoHab, empty);
                if (tipoHab == null || empty){
                    setText("");
                } else {
                    setText(tipoHab.getNombre());
                }
            }
        });  
                    
        //Determinamos como se muestra el comboBox cuando no se este seleccionando nada de su lista
        comboBoxTipoHab.setConverter(new StringConverter<TipoHabitacion>(){
        @Override
            public String toString(TipoHabitacion tipoHab) {
                if(tipoHab == null){
                    return "";
                }
                else{
                return tipoHab.getNombre();
                }
            }

            @Override
            public TipoHabitacion fromString(String string) {
                return null;
            }
                        
                        
        });
    }
    
    
    
    
           
    
     
}
