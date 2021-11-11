/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import entidades.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class AppHotelPrincipalViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private EntityManager entityManager;
    @FXML
    private MenuBar MenuMain;
    @FXML
    private Menu MenuClientes;
    @FXML
    private MenuItem MenuItem_MostrarClientes;
    @FXML
    private Menu MenuReservas;
    @FXML
    private MenuItem MenuItem_Habitaciones;
    @FXML
    private MenuItem MenuItem_Salon;
    @FXML
    private Menu ManuAyuda;
    @FXML
    private MenuItem MenuItem_Informacion;
    @FXML
    private MenuItem MenuItem_Salir;
    @FXML
    private AnchorPane AppHotelPrincipalView;
    private Persona persona;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //debemos crear un vinculo entre el controlador y el modelo.
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    } 

    //Con este metodo podemos abrir la ventana de reservas de Habitaciones cuando le demos clic a su respectivo menu
    @FXML
    private void AbreHabitaciones(ActionEvent event) {
        
        try{
            //Cargamos la vista Habitaciones
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/AppHotelReservaHabitacionesView.fxml"));
            Pane rootHabitacionesView = fxmlLoader.load();
            
            //Ocultamos la vista principal de la AppHotelPrincipalView
            AppHotelPrincipalView.setVisible(false);
            
            //Añadimos la vista Habitaciones al Stackpane Principal
            StackPane root_AppHotelPrincipalView = (StackPane) AppHotelPrincipalView.getScene().getRoot();
            root_AppHotelPrincipalView.getChildren().add(rootHabitacionesView);
            
            //Añadimos el controlador de del AppHotelReservaHabitaciones
            AppHotelReservaHabitacionesViewController HabitacionesController = (AppHotelReservaHabitacionesViewController) fxmlLoader.getController();
            HabitacionesController.setRootPrincipalView(AppHotelPrincipalView);
            
            //Persona nueva o existente
            persona = new Persona();
            HabitacionesController.setPersona(entityManager, persona);
            
            //Mostramos datos
            HabitacionesController.mostrarDatos();
        }
        catch(IOException ex){
            Logger.getLogger(AppHotelPrincipalViewController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }

    //Con este metodo podemos abrir la ventana de reservas de salon cuando le demos clic a su respectivo menu
    @FXML
    private void AbreSalon(ActionEvent event) {
        
        try{
            //Cargamos la vista Habitaciones
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/AppHotelReservaSalonView.fxml"));
            Pane rootSalonView = fxmlLoader.load();
            
            //Ocultamos la vista principal de la AppHotelPrincipalView
            AppHotelPrincipalView.setVisible(false);
            
            //Añadimos la vista Habitaciones al Stackpane Principal
            StackPane root_AppHotelPrincipalView = (StackPane) AppHotelPrincipalView.getScene().getRoot();
            root_AppHotelPrincipalView.getChildren().add(rootSalonView);
            
            //Añadimos el controlador de del AppHotelReservaHabitaciones
            AppHotelReservaSalonViewController salonController = (AppHotelReservaSalonViewController) fxmlLoader.getController();
            salonController.setRootPrincipalView(AppHotelPrincipalView);
            
            persona = new Persona();
            salonController.setPersona(entityManager, persona);
            
            //Mostramos datos
            salonController.mostrarDatos();
            
            //Desabilitamos ciertos nodos al abrir la ventana
            salonController.desabilitarDatosAlPrincipio();
        }
        catch(IOException ex){
            Logger.getLogger(AppHotelPrincipalViewController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void AbreInformacion(ActionEvent event) {
        
        
    }

    //Cerramos la aplicacion al darle la menu Item cerrar
    @FXML
    private void Cerrar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void MostrarTabla(ActionEvent event) {
    }
    
}
