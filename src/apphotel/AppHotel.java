/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apphotel;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
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

    
    @Override
    public void start(Stage primaryStage) {
        
        //Nos conectamos a la BD instanciando los objetos emf y em
        emf = Persistence.createEntityManagerFactory("AppHotelPU");
        em = emf.createEntityManager();
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
