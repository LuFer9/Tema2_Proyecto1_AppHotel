/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Profe2
 */
public class AppHotel extends Application {
    
    //Declaramos la variable para inicializar la conexion con la base de datos
    private EntityManagerFactory emf;
    //Declaramos la varibale para poder realizar las operaciones que queramos dentro de la base de datos
    private EntityManager em;
    private StackPane root_Main;
    private Pane root_Hotel_Principal;

    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        //Layout principal creamos un stackPane para suporponer las ventanas
        root_Main = new StackPane();
        
        //Cargamos el archivo FXML AppHotelPrincipalView
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/fxml/AppHotelPrincipalView.fxml"));
        
        //Creamos un pane donde cargaremos nuestra ventana principal
        root_Hotel_Principal = fxmlLoader.load();//Controla la gestion de excepciones del loader
        
        //Contenido recogido en root_Hotel_Principal_View lo introducimos en el root_Main_view
        root_Main.getChildren().add(root_Hotel_Principal);
        
        
        //Nos conectamos a la BD instanciando los objetos emf y em
        emf = Persistence.createEntityManagerFactory("AppHotelPU");
        em = emf.createEntityManager();
        
        //Instanciamos el objeto controller que lo necesitaremos para poder gestionar los eventos
        AppHotelPrincipalViewController principalViewController = (AppHotelPrincipalViewController) fxmlLoader.getController();
        principalViewController.setEntityManager(em); //El controlador debe tener acceso al objeto em que permite el acceso a los datos de la BD
        
        Scene scene = new Scene(root_Main);
        primaryStage.setTitle("App Hotel - Focal");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    
    
    //Con el metodo Stop cuando termine la ejecucion del programa terminaremos la conexion con la base de datos 
    @Override
    public void stop() throws Exception {
        //Cerramos los objetos creados anteriormente em y emf
        em.close();
        emf.close();
        
        //Cerramos la base de datos endebida
        try{
            DriverManager.getConnection("jdbc:derby:BDHotel;shutdown=true");
        } catch(SQLException ex){}
    }
    
    
    
    //Metodo main para iniciar el programa
    public static void main(String[] args) {
        launch(args);
    }
    
}
