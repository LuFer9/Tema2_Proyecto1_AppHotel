/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setRootPrincipalView(Pane rootPrincipalView){
        this.rootPrincipalView = rootPrincipalView;
    }
    
}
