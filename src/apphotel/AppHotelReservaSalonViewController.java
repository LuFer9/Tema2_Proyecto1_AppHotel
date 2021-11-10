/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import entidades.Persona;
import entidades.Provincia;
import entidades.ReservaSalon;
import entidades.TipoCocina;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
public class AppHotelReservaSalonViewController implements Initializable {

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
    private TextField textFieldTelefono;
    @FXML
    private Button buttonLimpiar;
    @FXML
    private Button buttonAceptar;
    @FXML
    private RadioButton RadioButtonBanquete;
    @FXML
    private RadioButton RadioButtonJornada;
    @FXML
    private RadioButton RadioButtonCongreso;
    @FXML
    private HBox hBoxNumeroPersona;
    @FXML
    private TextField textFieldNumPersonas;
    @FXML
    private HBox hBoxTipoCocina;
    @FXML
    private ComboBox<TipoCocina> ComboBoxCocina;
    @FXML
    private HBox hBoxHabitaciones;
    
    @FXML
    private Button buttonCancelar;
    @FXML
    private CheckBox checkBoxhabitaciones;
    @FXML
    private HBox hBoxNumeroDias;
    @FXML
    private TextField textFieldNumDias;
    @FXML
    private DatePicker datePickerFechaEvento;
    @FXML
    private ImageView imageViewFoto;
    @FXML
    private Label textLabelTipoEvento;
    @FXML
    private Label textLabelTipoEvento2;
    @FXML
    private AnchorPane rootSalonView;
    
    private AnchorPane AppHotelPrincipalView;
    private Persona persona;
    private EntityManager entityManager;
    private ReservaSalon reservaSalon;
    private boolean nuevaPersona;
    private TipoCocina tipoCocina;
    private boolean salir;
    @FXML
    private ToggleGroup ToggleGroupTipoEvento;
    @FXML
    private TextField textFieldCantidadHabitaciones;
    
        
    public static final String BANQUETE = "BANQUETE";
    public static final String JORNADA = "JORNADA";
    public static final String CONGRESO = "CONGRESO";
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                        textFieldTelefono.setText(String.valueOf(persona.getTelefono()));
                        if(textFieldTelefono.getText() != null){
                            textFieldTelefono.setEditable(false);
                        }
                        else
                            textFieldTelefono.setEditable(false);
   
                        
                    }
                    catch(NullPointerException e){
                        
                        nuevaPersona = true;
                        System.out.println("ola nueva persona");
                        LimpiarDatos();
                        limpiarDatosInternosAlCambiarDeRadioButton();
                                
                        if(textFieldDNI.getText().equals("")){
                           
                        }
                        else{
                            
                        }
                        
                    }
                }
            });
        
    }  
    
    public void setRootPrincipalView(Pane rootPrincipalView){
        this.rootPrincipalView = rootPrincipalView;
    }

     @FXML
    private void OnActionButtonBanquete(ActionEvent event) {
        //Limpiamos los datos introducidos anteriormente
        limpiarDatosInternosAlCambiarDeRadioButton();
        
        imageViewFoto.setVisible(true);
        
        hBoxTipoCocina.setDisable(false);
        hBoxHabitaciones.setDisable(true);
        textFieldNumDias.setDisable(true);
        datePickerFechaEvento.setEditable(false);
        textFieldNumPersonas.setDisable(false);
        textFieldNumPersonas.setEditable(true);
        ComboBoxCocina.setDisable(false);

        
        textLabelTipoEvento.setText("Tipo: Banquete");
        textLabelTipoEvento2.setText("Selecciona personas...");
        
        Image imagen = new Image("/resources/images/Banquete.jpg",250,200,true,false);
        imageViewFoto.setImage(imagen);
    }

    @FXML
    private void onActionButtonJornada(ActionEvent event) {
        //Limpiamos los datos introducidos anteriormente
        limpiarDatosInternosAlCambiarDeRadioButton();
        imageViewFoto.setVisible(true);
        
        hBoxTipoCocina.setDisable(true);
        hBoxHabitaciones.setDisable(true);
        textFieldNumDias.setDisable(true);
        datePickerFechaEvento.setEditable(false);
        textFieldNumPersonas.setDisable(false);
        textFieldNumPersonas.setEditable(true);
        
        textLabelTipoEvento.setText("Tipo: Jornada");
        textLabelTipoEvento2.setText("Selecciona personas...");
        
        Image imagen = new Image("/resources/images/Jornada.jpg",300,300,true,false);
        imageViewFoto.setImage(imagen);
        
    }

    @FXML
    private void OnActionButtonCongreso(ActionEvent event) {
        //Limpiamos los datos introducidos anteriormente
        limpiarDatosInternosAlCambiarDeRadioButton();
        
        imageViewFoto.setVisible(true);
        
        hBoxTipoCocina.setDisable(true);
        hBoxHabitaciones.setDisable(false);
        textFieldNumDias.setEditable(true);
        textFieldNumDias.setDisable(false);
        textFieldCantidadHabitaciones.setEditable(false);
        datePickerFechaEvento.setEditable(false);
        textFieldNumPersonas.setDisable(false);
        textFieldNumPersonas.setEditable(true);
                       
        
        textLabelTipoEvento.setText("Tipo: Congreso");
        textLabelTipoEvento2.setText("Selecciona personas...");
        
        Image imagen = new Image("/resources/images/Congreso.jpg",300,300,true,false);
        imageViewFoto.setImage(imagen);
    }

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) {
        textFieldDNI.setText("");
        textFieldNombre.setText("");
        textFieldDireccion.setText("");
        textFieldTelefono.setText("");
        RadioButtonBanquete.setSelected(false);
        RadioButtonJornada.setSelected(false);
        RadioButtonCongreso.setSelected(false);
        textFieldNumPersonas.setText("");
        ComboBoxCocina.getSelectionModel().clearSelection();
        textFieldCantidadHabitaciones.setText("");
        datePickerFechaEvento.setValue(null);
        textFieldNumDias.setText("");
        checkBoxhabitaciones.setSelected(false);
        
        
        hBoxNumeroPersona.setDisable(false);
        hBoxTipoCocina.setDisable(false);
        hBoxHabitaciones.setDisable(false);
        hBoxNumeroDias.setDisable(false);
        imageViewFoto.setVisible(false);
        
    }
    
    
     public void setPersona(EntityManager entityManager, Persona persona){
        this.entityManager = entityManager;
        this.persona = persona;
    }
     
    
    public void setAppHotelPrincipalView(AnchorPane AppHotelPrincipalView){
        this.AppHotelPrincipalView = AppHotelPrincipalView;
    }


    @FXML
    private void onActionButtonAceptar(ActionEvent event) {
        
        //Atributos
        boolean errorFormato = false;
        reservaSalon = new ReservaSalon();
        tipoCocina = new TipoCocina();
        Alert alert = new Alert(AlertType.ERROR);
        String cadenaAlert = "";
        salir = false;
        
        if(nuevaPersona){
            persona = new Persona();
        }
        
        //Actualizmaos los datos de la persona
        if(!salir){
            
            try{
                
                if(comprobarDNI()){
                    
                    persona.setDni(textFieldDNI.getText());
                    //Insertamos el DNI en la tabla salon
                    reservaSalon.setDniCliente(persona);
                }else{
                    errorFormato = true;
                    cadenaAlert += "DNI no valido\n";
                    textFieldDNI.requestFocus();
                }
                
                //Comprobacion nombre
                if(textFieldNombre.getText().length() > 2 && textFieldNombre.getText().length() <= 20){
                    
                    persona.setNombre(textFieldNombre.getText());
                }
                else{
                    errorFormato = true;
                    cadenaAlert += "Nombre no valido\n";
                    textFieldNombre.requestFocus();
                }
                
                //Comprobacion direccion
                if(textFieldDireccion.getText().length() > 5 && textFieldDireccion.getText().length() <= 50){
                    
                    persona.setDireccion(textFieldDireccion.getText());
                }
                else{
                    errorFormato = true;
                    cadenaAlert += "Direccion no valida\n";
                    textFieldDireccion.requestFocus();
                }
                
                //Comprobacion de telefono
                if(!textFieldTelefono.getText().isEmpty() && comprobarTelefono()){
                    
                    try{
                        persona.setTelefono(Integer.valueOf(textFieldTelefono.getText()));
                    }
                    catch(NumberFormatException ex){
                        errorFormato = true;
                        cadenaAlert += "Telefono no valido\n";
                        textFieldTelefono.requestFocus();
                    }
                }
                else{
                    errorFormato = true;
                    cadenaAlert += "Telefono no valido\n";
                    textFieldTelefono.requestFocus();
                }
                
                //comprobacion de radioButtons
                //Comprobacion radioButton banquete
                if(RadioButtonBanquete.isSelected()){
                    
                    reservaSalon.setTipoEvento(BANQUETE);
                    //Numero de personas
                    if(textFieldNumPersonas.getText() != null && Integer.valueOf(textFieldNumPersonas.getText()) > 1 && Integer.valueOf(textFieldNumPersonas.getText()) < 100){
                        
                        reservaSalon.setNumPersonas(Integer.valueOf(textFieldNumPersonas.getText()));
                    }
                    else{
                        errorFormato = true;
                        cadenaAlert += "Numero de personas no valido\n";
                        textFieldNumPersonas.requestFocus();
                    }
                    
                    //Comprobacion tipo Cocina comboBox
                    if(ComboBoxCocina.getValue() != null){
                        
                        try{
                            Query queryFindTipoHab = entityManager.createNamedQuery("TipoCocina.findByNombre");
                            queryFindTipoHab.setParameter("nombre", ComboBoxCocina.getValue().toString());
                            tipoCocina = (TipoCocina) queryFindTipoHab.getSingleResult();
                            
                            reservaSalon.setTipoCocina(tipoCocina);
                        }
                        catch(NoResultException e){
                            
                        }
                    }
                    else{
                        errorFormato = true;
                        cadenaAlert += "Debes seleccionar un tipo cocina\n";
                    }
                    
                    //Controlamos la fecha del evento
                    if(datePickerFechaEvento.getValue() != null){
                        LocalDate localDate = datePickerFechaEvento.getValue();
                        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
                        Instant instant = zonedDateTime.toInstant();
                        Date date = Date.from(instant);
                        reservaSalon.setFechaEvento(date);
                    }
                    else {
                        errorFormato = true;
                        cadenaAlert += "Debes seleccionar la fecha de llegada\n";
                    
                    }
                    
                }
                
                //RadioButton jornada
                if(RadioButtonJornada.isSelected()){
                    
                    reservaSalon.setTipoEvento(JORNADA);
                    
                    //Numero de personas
                    if(textFieldNumPersonas.getText() != null && Integer.valueOf(textFieldNumPersonas.getText()) > 1 && Integer.valueOf(textFieldNumPersonas.getText()) < 50){
                        
                        reservaSalon.setNumPersonas(Integer.valueOf(textFieldNumPersonas.getText()));
                    }
                    else{
                        errorFormato = true;
                        cadenaAlert += "Numero de personas no valido\n";
                        textFieldNumPersonas.requestFocus();
                    }
                    
                    //Controlamos la fecha del evento
                    if(datePickerFechaEvento.getValue() != null){
                        LocalDate localDate = datePickerFechaEvento.getValue();
                        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
                        Instant instant = zonedDateTime.toInstant();
                        Date date = Date.from(instant);
                        reservaSalon.setFechaEvento(date);
                    }
                    else {
                        errorFormato = true;
                        cadenaAlert += "Debes seleccionar la fecha de llegada\n";
                    
                    }
                }
                
                //radioButton congreso
               if(RadioButtonCongreso.isSelected()){
                   
                   reservaSalon.setTipoEvento(CONGRESO);
                   
                   //Numero de personas
                    if(textFieldNumPersonas.getText() != null && Integer.valueOf(textFieldNumPersonas.getText()) > 1 && Integer.valueOf(textFieldNumPersonas.getText()) < 50){
                        
                        reservaSalon.setNumPersonas(Integer.valueOf(textFieldNumPersonas.getText()));
                    }
                    else{
                        errorFormato = true;
                        cadenaAlert += "Numero de personas no valido\n";
                        textFieldNumPersonas.requestFocus();
                    }
                    
                    //Controlamos la fecha del evento
                    if(datePickerFechaEvento.getValue() != null){
                        LocalDate localDate = datePickerFechaEvento.getValue();
                        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
                        Instant instant = zonedDateTime.toInstant();
                        Date date = Date.from(instant);
                        reservaSalon.setFechaEvento(date);
                    }
                    else {
                        errorFormato = true;
                        cadenaAlert += "Debes seleccionar la fecha de llegada\n";
                    
                    }
                    
                    //Comprobacion habitaciones y cuantas
                    if(checkBoxhabitaciones.isSelected()){
                        
                        reservaSalon.setHabitaciones(true);
                        textFieldCantidadHabitaciones.setEditable(true);
                        
                        if(textFieldCantidadHabitaciones.getText() != null){
                            
                            try{
                                reservaSalon.setNumHabitaciones(Integer.valueOf(textFieldCantidadHabitaciones.getText()));                             
                            }
                            catch(NumberFormatException ex){
                                errorFormato = true;
                                cadenaAlert += "Debes introducir un numero en cantidad de habitaciones\n";
                            }
                            
                        }
                        else{
                            errorFormato = true;
                            cadenaAlert += "Cantidad de habitaciones debe ser rellenado\n"; 
                        }
                    }
                    else{
                        reservaSalon.setHabitaciones(false);
                        textFieldCantidadHabitaciones.setEditable(false);
                    }
                    
                     //Numero de dias
                    if(textFieldNumDias.getText() != null && Integer.valueOf(textFieldNumDias.getText()) > 1 && Integer.valueOf(textFieldNumDias.getText()) < 10){
                        
                        try{
                            reservaSalon.setNumDias(Integer.valueOf(textFieldNumDias.getText()));
                        }
                        catch(NumberFormatException ex){
                            errorFormato = true;
                            cadenaAlert += "numero de dias no es un numero\n";
                            textFieldNumDias.requestFocus();
                        }
                        
                    }
                    else{
                         errorFormato = true;
                         cadenaAlert += "numero de dias invalido\n";
                         textFieldNumDias.requestFocus();
                    }
                    
                    
                    
                    
               }
               
               if(!RadioButtonCongreso.isSelected() && !RadioButtonJornada.isSelected() && !RadioButtonBanquete.isSelected()){
                   errorFormato = true;
                   cadenaAlert += "No has elegido ningun tipo de evento, porfavor elige uno.\n";
               }
               
               if(errorFormato){
                  alert.setContentText(cadenaAlert);
                  alert.showAndWait();
                  salir = true;
               }
               
               if(nuevaPersona && !salir){
                    //Iniciamos de nuevo la transaccion
                    entityManager.getTransaction().begin();
                    
                    entityManager.persist(persona);
                    entityManager.persist(reservaSalon);
                    Alert alertNuevaPersona = new Alert(AlertType.INFORMATION, "Nuevo cliente insertado y datos de reserva insertados con exito\n");
                    alertNuevaPersona.showAndWait();
                    entityManager.getTransaction().commit();
                }
                if(!nuevaPersona && !salir){
                    //Iniciamos de nuevo la transaccion
                    entityManager.getTransaction().begin();
                    
                    entityManager.merge(persona);
                    entityManager.persist(reservaSalon);
                    Alert alertReserva = new Alert(AlertType.INFORMATION, "Datos de reserva insertados con exito\n");
                    alertReserva.showAndWait();
                    entityManager.getTransaction().commit();
                }        
                
                
            }
            catch(RollbackException ex) // Los datos introducidos no cumplen los requisitos
           {
               Alert alertErrorEnBD = new Alert(AlertType.ERROR);
               alertErrorEnBD.setHeaderText(" No se han podido guardar los cambios. Compruebe que los datos cumplen los requisitos\n");
               alertErrorEnBD.setContentText(ex.getLocalizedMessage());
               alertErrorEnBD.showAndWait();
               
           }
           catch(IllegalArgumentException e){
               Alert alertErrorEnBD = new Alert(AlertType.ERROR);
               alertErrorEnBD.setHeaderText("Argumento Ilegal, No se ha podido encontrar ningun Cliente o no se ha podido crear\n");
               alertErrorEnBD.setContentText(e.getLocalizedMessage());
               alertErrorEnBD.showAndWait();
               
           }
        }
        
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
        StackPane rootMain = (StackPane) rootSalonView.getScene().getRoot();
        rootMain.getChildren().remove(rootSalonView);
        rootPrincipalView.setVisible(true);
        
    }
    
    public boolean comprobarDNI(){
        Pattern patronDNI = Pattern.compile("[0-9]{8}[A-Za-z]");
        Matcher match = patronDNI.matcher(textFieldDNI.getText());
      
        return match.matches();

     }
    
    public boolean comprobarTelefono(){
        Pattern patronTelefono = Pattern.compile("[0-9]{9}");
        Matcher match = patronTelefono.matcher(textFieldTelefono.getText());
      
        return match.matches();
    }
    
    
     public void mostrarDatos() {
        
        Query queryTipoCocinaFindAll = entityManager.createNamedQuery("TipoCocina.findAll");
        List listaTipoCocina = queryTipoCocinaFindAll.getResultList();
        ComboBoxCocina.setItems(FXCollections.observableList(listaTipoCocina));
        
        
        
        //Para que muestre los nombres de las provincias en vez de las IDs
        ComboBoxCocina.setCellFactory(
                (ListView<TipoCocina> l) -> new ListCell<TipoCocina>() {
            @Override
            protected void updateItem(TipoCocina tipoCocina, boolean empty) {
                super.updateItem(tipoCocina, empty);
                if (tipoCocina == null || empty) {
                    setText("");
                } else {
                    setText(tipoCocina.getNombre());
                }
            }
        });
        
       
        //Al cerrar el comboBox despues de haber selecionado muestra un formato APPAgenda2.Nombre de provincia. 
        //Para eso cambiamos el formato para que solo muestre el nombre de la provincia
        ComboBoxCocina.setConverter(new StringConverter<TipoCocina>() {
            @Override
            public String toString(TipoCocina tipoCocina) {
                if (tipoCocina == null) {
                    return null;
                } else {
                    return  tipoCocina.getNombre();
                }
            }

            @Override
            public TipoCocina fromString(String userId) {
                return null;
            }
        });
    }
     
     
      public void LimpiarDatos(){
        
        textFieldNombre.setText("");
        textFieldNombre.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
        
        textFieldDireccion.setText("");
        textFieldDireccion.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
        
        textFieldTelefono.setText("");
        textFieldTelefono.setEditable(true);// Para que despues que encuentra un dni ya creado se pueda editar despues de darle al boton limpiar
       
    }
      
    public void desabilitarDatosAlPrincipio(){
        
        textFieldNumPersonas.setEditable(false);
        textFieldNumPersonas.setDisable(true);
        ComboBoxCocina.setEditable(false);
        ComboBoxCocina.setDisable(true);
        hBoxHabitaciones.setDisable(true);
        textFieldNumDias.setEditable(false);
        textFieldNumDias.setDisable(true);
        datePickerFechaEvento.setEditable(false);
        
    }

    @FXML
    private void onActionButtonCHHabitacion(ActionEvent event) {
        
        textFieldCantidadHabitaciones.setEditable(true);
        
    }
    
    public void limpiarDatosInternosAlCambiarDeRadioButton(){
  
        textFieldNumPersonas.setText("");
        ComboBoxCocina.getSelectionModel().clearSelection();
        textFieldCantidadHabitaciones.setText("");
        datePickerFechaEvento.setValue(null);
        textFieldNumDias.setText("");
        checkBoxhabitaciones.setSelected(false);   
    }
      

    
}
