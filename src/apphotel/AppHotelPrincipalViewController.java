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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    private Button btn1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //debemos crear un vinculo entre el controlador y el modelo.
    public void setEntityManager(EntityManager entityManager){
        this.entityManager = entityManager;
    } 
    
}
