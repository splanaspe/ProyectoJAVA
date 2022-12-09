/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupofp.controlador;

import grupofp.controlador.MenuArticulosController;
import grupofp.controlador.MenuClientesController;
import grupofp.modelo.ClienteEstandard;
import grupofp.modelo.ClientePremium;
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
public class MenuClientesPremiumController implements Initializable {
    Datos datos; 
    ObservableList<ClientePremium> clientesP;
    @FXML
    private Label menuClientsPremium;
    @FXML
    private Button botoInsertarClienteP;
    @FXML
    private Button botoEliminarP;
    @FXML
    private Button botoMostrarClientesP;
    @FXML
    private Pane paneInsertarPremium;
    @FXML
    private TextField txtNIF;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDomicilio;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button botoInsertarP;
    @FXML
    private Pane paneEliminar;
    @FXML
    private TextField txtNIF2;
    @FXML
    private Button botoEliminar;
    @FXML
    private TableView<ClientePremium> taulaClientesP;
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
        clientesP = FXCollections.observableArrayList();
        this.colNombre.setCellFactory(new PropertyValueFactory("nombre"));
        this.colNIF.setCellFactory(new PropertyValueFactory("NIF"));
        this.colDomicilio.setCellFactory(new PropertyValueFactory("domicilio"));
        this.colEmail.setCellFactory(new PropertyValueFactory("email"));
        
    }    

    @FXML
    private void añadirClienteP() throws SQLException {
        //Fem visibles
        paneInsertarPremium.setVisible(true);
        botoInsertarClienteP.setVisible(true);
        
        
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
            ClientePremium c = new ClientePremium(nombre, NIF, domicilio, email);
            insertarP(c);
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }
    
    @FXML
    private void insertarP(ClientePremium c) throws SQLException {
        datos.addClienteP(c);
        paneInsertarPremium.setVisible(false);
        botoInsertarClienteP.setVisible(false);
    }
    
    

    
    //Eliminar
    
    @FXML
    private void eliminarClienteP() throws SQLException {
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
        datos.eliminarClienteP(nif);
        paneEliminar.setVisible(false);
        botoEliminar.setVisible(false);
    }
    
    
    //Mostrar clients

    @FXML
    private void mostrarClientesP(ActionEvent event) throws SQLException {
        //importem els articles de la BD a través de datos en format ObservableList<Articulo>
        clientesP = datos.selectClientesPremium();
        //omplim la taula
        this.taulaClientesP.setItems(clientesP);
        
        //Carreguem la taula per a que sigui visible
        taulaClientesP.setVisible(true);
        botoSortirTaula.setVisible(true);
        sortirTaulaClientesP();
    }

   

    @FXML
    private void sortirTaulaClientesP() {
          taulaClientesP.setVisible(false);
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
