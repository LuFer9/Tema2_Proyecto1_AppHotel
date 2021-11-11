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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

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
    private ReservaHabitacion reservaH;
    private TipoHabitacion tipoHabitacion;
    @FXML
    private AnchorPane viewReservaHabitacion;
    private boolean nuevaPersona;
    private boolean insertado;
    
    public static final String ALOJAMIENTOYDESAYUNO = "ALOJAMIENTO Y DESAYUNO";
    public static final String MEDIAPENSION = "MEDIA PENSION";
    public static final String PENSIONCOMPLETA = "PENSION COMPLETA";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Query queryPersonaFindAll = entityManager.createNamedQuery("Persona.findByDni");
        List listPersona = queryPersonaFindAll.getResultList();
        */
        //Configuramos el spinner
        SpinnerValueFactory<Integer> gradesValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        this.spinnerNumHab.setValueFactory(gradesValue);

            textFieldDNI.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldProperty, Boolean newProperty) -> {
                
                if(newProperty){
                    
                    System.out.println("changing");
                }
                else{
                    
                   
                    try{    
    
                        persona = entityManager.find(Persona.class, textFieldDNI.getText());
                        nuevaPersona = false;

                        textFieldNombre.setText(persona.getNombre());
                        textFieldNombre.setEditable(false);
                        textFieldDireccion.setText(persona.getDireccion());
                        textFieldDireccion.setEditable(false);
                        textFieldLocalidad.setText(persona.getLocalidad());
                        textFieldLocalidad.setEditable(false);

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
                        
                        
                        comboBoxProvincia.setDisable(true);
                        
                    }
                    catch(NullPointerException e){
                        
                        nuevaPersona = true;
                        System.out.println("ola nueva persona");
                        LimpiarDatos();
                       
                        
                    }
                }
            });
                     
    }    
    
    // Metodo para asignarle el objeto correspondiente desde la otra clase controladora
    public void setRootPrincipalView(Pane rootPrincipalView){
        this.rootPrincipalView = rootPrincipalView;
    }
    

    @FXML
    private void onActionButtonAceptar(ActionEvent event){
        
        boolean errorFormato = false;
        reservaH = new ReservaHabitacion();
        tipoHabitacion = new TipoHabitacion();
        Alert alert;
        String cadenaAlert = "";
        int codError = 0;
        
        if(nuevaPersona){
            persona = new Persona();
        }
            
        //Actualizamos los datos de la persona en cuestion
           try
           {
                
                //Combrobacion DNI
                if(comprobarDNI()){
                   
                    persona.setDni(textFieldDNI.getText()); 
                    //Insertamos nuestro DNI en la tabla reserva habitaciones
                    reservaH.setDniCliente(persona);
                   
                }
                else{
                    errorFormato = true;
                    codError = 1;
                    
               
                }
                
                if(!errorFormato){
                    
                    //Comprobacion nombre
                    if(comprobarNombre()){

                        persona.setNombre(textFieldNombre.getText());
                    }
                    else{
                        errorFormato = true;
                        codError = 2;
                 
                    }
                    
                }
                
                if(!errorFormato){
                    //Comprobacion direccion
                    if(comprobarDireccion()){

                        persona.setDireccion(textFieldDireccion.getText());
                    }
                    else{
                        errorFormato = true;
                        codError = 3;
                    }
                    
                }
            
                if(!errorFormato){
                    //Comprobacion localidad
                    if(comprobarLocalidad()){

                        persona.setLocalidad(textFieldLocalidad.getText());
                    }
                    else{
                        errorFormato = true;
                        codError = 4;
           
                    }
                    
                }
             
                
                if(!errorFormato){
                     //Comprobacion provincia
                    if(comboBoxProvincia.getValue() != null){

                        persona.setProvincia(comboBoxProvincia.getValue());
                    }
                    else{
                        errorFormato = true;
                        codError = 5;



                    }
                    
                }
           
   
                
                if(!errorFormato){
                     //Comprobacion regimen
                    if(radioButtonAlojamiento.isSelected()){
                        reservaH.setRegimen(ALOJAMIENTOYDESAYUNO);
                    }
                    else if(radioButtonMediaPension.isSelected()){
                        reservaH.setRegimen(MEDIAPENSION);
                    }
                    else if(radioButtonPensionCompleta.isSelected()){
                        reservaH.setRegimen(PENSIONCOMPLETA);
                    }
                    else{

                        errorFormato = true;
                        codError = 6;
                         
                    }
                }
                    
                if(!errorFormato){
                    
                    //Comprobacion tipo habitacion
                    if(comboBoxTipoHab.getValue() != null){

                        try{
                            Query queryFindTipoHab = entityManager.createNamedQuery("TipoHabitacion.findByNombre");
                            queryFindTipoHab.setParameter("nombre", comboBoxTipoHab.getValue().toString());
                            tipoHabitacion = (TipoHabitacion) queryFindTipoHab.getSingleResult();


                            reservaH.setTipoHabitacion(tipoHabitacion);
                        }
                        catch(NoResultException e){

                        }
                    }
                    else{
                        codError = 7;
                        errorFormato = true;


                    }
                }

                
                if(!errorFormato){
                     //Comprobacion fechas
                    //Fechas
                    if (datePickerLlegada.getValue() != null){

                        LocalDate localDate = datePickerLlegada.getValue();
                        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
                        Instant instant = zonedDateTime.toInstant();
                        Date date = Date.from(instant);
                        reservaH.setFechaLlegada(date);
                    }
                    else {
                        errorFormato = true;
                        codError = 8;


                    }     
                    
                }
                
                
                if(!errorFormato){
                    
                
                    if(datePickerSalida.getValue() != null){
                    LocalDate localDate = datePickerSalida.getValue();
                    ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
                    Instant instant = zonedDateTime.toInstant();
                    Date date = Date.from(instant);
                    reservaH.setFechaSalida(date);
                    }
                    else {
                        errorFormato = true;
                        codError = 9;


                    }
                    
                }
                
                
                if(!errorFormato){
                    
                    //comprobamos si las fechas son iguales
                    if(datePickerSalida.getValue() != null && datePickerLlegada.getValue() != null){

                        if(datePickerSalida.getValue().equals(datePickerLlegada.getValue())){
                        errorFormato = true;
                        codError = 10;


                        }
                        //Comprobamos si las fecha de llegada es mayor que la de salida
                        if(datePickerLlegada.getValue().isAfter(datePickerSalida.getValue())){
                            errorFormato = true;     
                            codError = 11;


                        }

                    }
                    
                }
             
                
                //Comprobacion numero habitaciones
                reservaH.setNumeroHabitaciones(spinnerNumHab.getValue());
                
                //casilla de fumador
                if(checkBoxFumador.isSelected()){
                    reservaH.setFumador(true);
                }
                else{
                    reservaH.setFumador(false);
                }
                
                //si telefono esta a null se pondra el valor a 0
                if(persona.getTelefono() == null){
                   persona.setTelefono(0);
                }
                
                //Si apellido es null se pondra como vacio
                if(persona.getApellidos() == null){
                    persona.setApellidos("Vacío");
                }
                
                //En caso de que haya algun error de formato mostramos los fallos
                if(errorFormato){
                    
                    switch(codError){
                    case 1: 
                        cadenaAlert = " DNI no valido ";
                        textFieldDNI.requestFocus();
                        break;
                    case 2:
                        cadenaAlert = " Campo nombre no valido";
                        textFieldNombre.requestFocus();
                        break;
                    case 3:
                        cadenaAlert=" Campo direccion no valido ";
                        textFieldDireccion.requestFocus();
                        break;
                    case 4:
                        cadenaAlert="Tienes que rellenar el campo localidad";
                        textFieldLocalidad.requestFocus();
                        break;

                    case 5:
                        cadenaAlert="Debe indicar una provincia";
                        comboBoxProvincia.requestFocus();
                        break;

                    case 6:
                        cadenaAlert="Debes seleccionar un régimen";
                        
                        break;

                    case 7:
                        cadenaAlert = "Debes seleccionar un tipo habitación";
                        break;
                        
                    case 8:
                        cadenaAlert="Debes de poner fechas de llegada";
                        datePickerLlegada.requestFocus();
                        break;
                        
                    case 9:
                        cadenaAlert = "Debe indicar una fecha de salida";
                        datePickerSalida.requestFocus();
                        break;
                        
                    case 10:
                        cadenaAlert = "Debes seleccionar fechas de llegada y salida que no sean el mismo dia";  
                        break;

                    case 11:
                        cadenaAlert ="Debes seleccionar fechas de llegada y salida coherentes";                   
                        break;
                        
                }
                 alert = new Alert(AlertType.ERROR, cadenaAlert);
                 alert.showAndWait();
                   
                }
                 
                
                 
                
                
                
                //Insertamos en la base de datos
                
                if(nuevaPersona && !errorFormato){
                    //Iniciamos de nuevo la transaccion
                    entityManager.getTransaction().begin();
                    
                    entityManager.persist(persona);
                    entityManager.persist(reservaH);
                    Alert alertNuevaPersona = new Alert(AlertType.INFORMATION, "Nuevo cliente insertado y datos de reserva insertados con exito");
                    alertNuevaPersona.showAndWait();
                    entityManager.getTransaction().commit();
                }
                if(!nuevaPersona && !errorFormato){
                    //Iniciamos de nuevo la transaccion
                    entityManager.getTransaction().begin();
                    
                    entityManager.merge(persona);
                    entityManager.persist(reservaH);
                    Alert alertReserva = new Alert(AlertType.INFORMATION, "Datos de reserva insertados con exito");
                    alertReserva.showAndWait();
                    entityManager.getTransaction().commit();
                }          
                
             
           }
           catch(RollbackException ex) // Los datos introducidos no cumplen los requisitos
           {
               Alert alertErrorEnBD = new Alert(AlertType.ERROR);
               alertErrorEnBD.setHeaderText(" No se han podido guardar los cambios. Compruebe que los datos cumplen los requisitos");
               alertErrorEnBD.setContentText(ex.getLocalizedMessage());
               alertErrorEnBD.showAndWait();
               
           }
           catch(IllegalArgumentException e){
               Alert alertErrorEnBD = new Alert(AlertType.ERROR);
               alertErrorEnBD.setHeaderText("Argumento Ilegal, No se ha podido encontrar ningun Cliente o no se ha podido crear");
               alertErrorEnBD.setContentText(e.getLocalizedMessage());
               alertErrorEnBD.showAndWait();
               
           }
           
       
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
        textFieldDNI.setText(null);
        textFieldDNI.setEditable(true); // Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
        
        textFieldNombre.setText(null);
        textFieldNombre.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
        
        textFieldDireccion.setText(null);
        textFieldDireccion.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
        
        textFieldLocalidad.setText(null);
        textFieldLocalidad.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
       
        comboBoxProvincia.getSelectionModel().clearSelection();
        comboBoxProvincia.setDisable(false);
        
        
        datePickerLlegada.setValue(null);
        datePickerSalida.setValue(null);
        //spinnerNumHab.setVisible(false);
        
        comboBoxTipoHab.getSelectionModel().clearSelection();
        checkBoxFumador.setSelected(false);
        radioButtonAlojamiento.setSelected(false);
        radioButtonMediaPension.setSelected(false);
        radioButtonPensionCompleta.setSelected(false);    
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
    
    //Comprobaciones
    public boolean comprobarDNI(){
        
        Pattern patronDNI = Pattern.compile("[0-9]{8}[A-Za-z]");
        Matcher match = patronDNI.matcher(textFieldDNI.getText());
        
        return match.matches();
    
    }
    
    public boolean comprobarNombre(){
        Pattern patronNombre = Pattern.compile("[A-Za-z]{2,20}");
        Matcher match = patronNombre.matcher(textFieldNombre.getText());
      
        return match.matches();
    }
    
     public boolean comprobarDireccion(){
        Pattern patronDireccion = Pattern.compile("[A-Za-z]{5,50}");
        Matcher match = patronDireccion.matcher(textFieldDireccion.getText());
      
        return match.matches();
    }
     
    public boolean comprobarLocalidad(){
        Pattern patronLocalidad = Pattern.compile("[A-Za-z]{3,20}");
        Matcher match = patronLocalidad.matcher(textFieldLocalidad.getText());
      
        return match.matches();
    }
    
    public void LimpiarDatos(){
        
        textFieldNombre.setText("");
        textFieldNombre.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
        
        textFieldDireccion.setText("");
        textFieldDireccion.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
        
        textFieldLocalidad.setText("");
        textFieldLocalidad.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
       
        comboBoxProvincia.getSelectionModel().clearSelection();
        comboBoxProvincia.setDisable(false);
        
        datePickerSalida.setValue(null);
        datePickerLlegada.setValue(null);
        
        SpinnerValueFactory<Integer> gradesValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        this.spinnerNumHab.setValueFactory(gradesValue);
        
        comboBoxTipoHab.getSelectionModel().clearSelection();
        
        checkBoxFumador.setSelected(false);
        
        radioButtonAlojamiento.setSelected(false);
        radioButtonMediaPension.setSelected(false);
        radioButtonPensionCompleta.setSelected(false);
    }
    
           
    
     
}
