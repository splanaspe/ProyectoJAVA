
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupofp.controlador;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/*
TUTORIAL: Coim fer un projecte normal a FX
https://www.youtube.com/watch?v=Bk42DbVBzXM
*/


/**
 *
 * @author Marc
 */
public class OnlineStore  extends Application {  
    public static void main(String[] args) 
    {
        launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
       Scene menuPrincipal = new Scene(root);
       stage.setTitle("Menu Principal");
       stage.setScene(menuPrincipal);
       stage.show();
       
    }
    

}


