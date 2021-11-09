/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
    private ComboBox<?> ComboBoxCocina;
    @FXML
    private HBox hBoxHabitaciones;
    @FXML
    private TextField textFieldCantidadPersona;
    @FXML
    private Button buttonCancelar;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setRootPrincipalView(Pane rootPrincipalView){
        this.rootPrincipalView = rootPrincipalView;
    }

    @FXML
    private void OnActionButtonBanquete(ActionEvent event) {
    }

    @FXML
    private void onActionButtonJornada(ActionEvent event) {
    }

    @FXML
    private void OnActionButtonCongreso(ActionEvent event) {
    }

    @FXML
    private void onActionButtonLimpiar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonAceptar(ActionEvent event) {
    }

    @FXML
    private void onActionButtonCancelar(ActionEvent event) {
    }
    
}
