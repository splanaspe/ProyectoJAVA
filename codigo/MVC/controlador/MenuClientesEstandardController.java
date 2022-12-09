/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupofp.controlador;

import grupofp.modelo.Articulo;
import grupofp.modelo.ClienteEstandard;
import grupofp.modelo.Datos;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splan
 */
public class MenuClientesEstandardController implements Initializable {

    
    Datos datos; 
    ObservableList<ClienteEstandard> clientesE;
    @FXML
    private Pane paneInsertarEstandard;
    @FXML
    private TextField txtNIF;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDomicilio;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button botoInsertarE;
    @FXML
    private Label menuArticles;
    @FXML
    private Button botoInsertarClienteE;
    @FXML
    private Button botoEliminarE;
    @FXML
    private Button botoMostrarClientesE;
    @FXML
    private Pane paneEliminar;
    @FXML
    private TextField txtNIF2;
    @FXML
    private Button botoEliminar;
    @FXML
    private TableView<ClienteEstandard> taulaClientesE;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colNIF;
    @FXML
    private TableColumn colDomicilio;
    @FXML
    private TableColumn colEmail;
    @FXML
    private Button botoSortirTaula;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        //Inicialitzem la instancia de DATOS
        try {
            datos = new Datos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuArticulosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Inicialitzem la taula, la configurem per a després omplir-la 
        clientesE = FXCollections.observableArrayList();
        this.colNombre.setCellFactory(new PropertyValueFactory("nombre"));
        this.colNIF.setCellFactory(new PropertyValueFactory("NIF"));
        this.colDomicilio.setCellFactory(new PropertyValueFactory("domicilio"));
        this.colEmail.setCellFactory(new PropertyValueFactory("email"));
        
    }       

    
    // Añadir cliente
    @FXML
    private void añadirClienteE() throws SQLException {
    
        //Fem visibles
        paneInsertarEstandard.setVisible(true);
        botoInsertarClienteE.setVisible(true);
        
        
        //Agafem les dades de la casella
        String nombre = this.txtNombre.getText();
        String NIF = this.txtNIF.getText();
        String domicilio = this.txtDomicilio.getText();
        String email = this.txtEmail.getText();
        
        
        String errores = "";
        
        if(nombre == null){
            errores += "debes introducir el nombre\n";
        }
        if(NIF == null){
            errores += "debes introducir el NIF\n";
        }
        if(domicilio == null){
            errores += "debes introducir el domicilio\n";
        }
        if(email == null){
            errores += "debes introducir el email\n";
        }
        
        if(errores.isEmpty()){
            ClienteEstandard c = new ClienteEstandard(nombre, NIF, domicilio, email);
            insertarE(c);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    
    }
    
    @FXML
    private void insertarE(ClienteEstandard c) throws SQLException {
        datos.addClienteE(c);
        paneInsertarEstandard.setVisible(false);
        botoInsertarClienteE.setVisible(false);
    }
    
    
    //Eliminar
    @FXML
    private void eliminarClienteE() throws SQLException {
        paneEliminar.setVisible(true);
        botoEliminar.setVisible(true);
        String NIF2 = this.txtNIF2.getText();
        
        //Fem la comprovacio de les dades
        String errores = "";
        
        if(NIF2 == null){
            errores += "debes introducir el NIF correcto\n";
        }
        
        if(errores.isEmpty()){
            eliminar(NIF2);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }
    
    @FXML
    private void eliminar(String nif) throws SQLException {
        datos.eliminarClienteE(nif);
        paneEliminar.setVisible(false);
        botoEliminar.setVisible(false);
    }
    
    //MostrAR cLIENTES ESTANDARD
    @FXML
    private void mostrarClientesE() throws SQLException { 
        //importem els articles de la BD a través de datos en format ObservableList<Articulo>
        clientesE = datos.selectClientesEstandard();
        //omplim la taula
        this.taulaClientesE.setItems(clientesE);
        
        //Carreguem la taula per a que sigui visible
        taulaClientesE.setVisible(true);
        botoSortirTaula.setVisible(true);
        sortirTaulaClientesE();
        
    }

   
    @FXML
    private void sortirTaulaClientesE() {
        taulaClientesE.setVisible(false);
        botoSortirTaula.setVisible(false);
    }
    
    
    
    
    void closeWindows() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuClientes.fxml"));
        Parent root = loader.load();
        MenuClientesController controlador = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    
    
}
