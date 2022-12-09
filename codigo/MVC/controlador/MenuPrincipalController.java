/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupofp.controlador;

import com.sun.javafx.logging.PlatformLogger.Level;
import java.awt.Label;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splan
 */
public class MenuPrincipalController implements Initializable {

    @FXML 
    private Label MenuPrincipal; 
    
    @FXML
    private Button botoArticulos;
    
    @FXML
    private Button botoClientes;
    
    @FXML
    private Button botoPedidos;
    
    @FXML
    private void menuArticulos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuArticulos.fxml"));
        Parent root = loader.load();
        MenuArticulosController controlador = loader.getController();
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
        Stage myStage = (Stage) this.botoArticulos.getScene().getWindow();
        myStage.close();
    }
    
    @FXML
    private void menuClientes(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuClientes.fxml"));
        Parent root = loader.load();
        MenuClientesController controlador = loader.getController();
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
        Stage myStage = (Stage) this.botoClientes.getScene().getWindow();
        myStage.close();
        
    }
    
    @FXML
    private void menuPedidos() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuPedidos.fxml"));
        Parent root = loader.load();
        MenuPedidosController controlador = loader.getController();
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
        Stage myStage = (Stage) this.botoPedidos.getScene().getWindow();
        myStage.close();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    
    
    
    
}
