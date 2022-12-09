/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupofp.controlador;

import grupofp.modelo.Articulo;
import grupofp.modelo.ClienteEstandard;
import grupofp.modelo.ClientePremium;
import grupofp.modelo.Datos;
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splan
 */
public class MenuClientesController implements Initializable {
    Datos datos; 
    private Pane paneClientEstandard;
    private Pane paneInsertarEstandard;
    private Pane paneClientPremium;
    private Pane paneInsertarPremium;

    private TextField txtNIF;
    private TextField txtNombre;
    private TextField txtDomicilio;
    private TextField txtEmail;
    
    
    private TextField txtNIF1;
    private TextField txtNombre1;
    private TextField txtDomicilio1;
    private TextField txtEmail1;
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private Button botoClientPremium;
    @FXML
    private Button botoClientEstandard;

    
    
    
    
    /*
                CLIENTS ESTANDARD
    */
    //Submenu de client estandard: dos botons
    @FXML 
    public void clientsEstandard() throws SQLException, IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuClientesEstandard.fxml"));
        Parent root = loader.load();
        MenuClientesEstandardController controlador = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                controlador.closeWindows();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(MenuPrincipalController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        Stage myStage = (Stage) this.botoClientEstandard.getScene().getWindow();
        myStage.close();
    }
    
    
    @FXML 
    public void clientsPremium() throws SQLException, IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuClientesPremium.fxml"));
        Parent root = loader.load();
        MenuClientesEstandardController controlador = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> {
            try {
                controlador.closeWindows();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(MenuPrincipalController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        });
        Stage myStage = (Stage) this.botoClientPremium.getScene().getWindow();
        myStage.close();
    }
    
    
    void closeWindows() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/grupofp.vista/MenuArticulos.fxml"));
        Parent root = loader.load();
        MenuPrincipalController controlador = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        /*Stage myStage = (Stage) this.botoArticulos.getScene().getWindow();
        myStage.close();
        */
    }
    
    
}
