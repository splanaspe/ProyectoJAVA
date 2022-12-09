/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupofp.controlador;

import grupofp.modelo.Articulo;
import grupofp.modelo.Datos;
import java.awt.Label;
import java.awt.TextField;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author splan
 */
public class MenuArticulosController implements Initializable {
    Datos datos;
    @FXML
    private javafx.scene.control.Label menuArticles;
    @FXML
    private TableView<Articulo> taulaArticles;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colPrecioVenta;
    @FXML
    private TableColumn colGastos;
    @FXML
    private TableColumn colTiempo;
    @FXML
    private Button botoSortirTaula;

    private ObservableList<Articulo> articles;
    
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
        articles = FXCollections.observableArrayList();
        this.colCodigo.setCellFactory(new PropertyValueFactory("codigo"));
        this.colNombre.setCellFactory(new PropertyValueFactory("nombre"));
        this.colPrecioVenta.setCellFactory(new PropertyValueFactory("precioVenta"));
        this.colGastos.setCellFactory(new PropertyValueFactory("gastosEnvio"));
        this.colTiempo.setCellFactory(new PropertyValueFactory("tPrep"));
        
    }    
    
    /*
    Please go to Platform Manager, create a non-default Java SE platform, 
    then go to the JavaFX tab, enable JavaFX and fill in the paths to valid
     JavaFX SDK and JavaFX Runtime. Note: JavaFX SDK can be downloaded from
    */

    
    @FXML
    private Button botoInsertarArticle;
    @FXML
    private Button botoEliminarArticle;
    @FXML
    private Button botoMostrarArticles;
    @FXML
    private Button botoEliminar;
    @FXML
    private Button insertarArticulo;
    @FXML
    private Pane paneInsertar;
    @FXML
    private Pane paneEliminar;
    
    
    @FXML
    private javafx.scene.control.TextField txtCodigo;
    @FXML
    private javafx.scene.control.TextField txtNombre;
    @FXML
    private javafx.scene.control.TextField txtPrecioVenta;
    @FXML
    private javafx.scene.control.TextField txtGastosEnvio;
    @FXML
    private javafx.scene.control.TextField txtTPrep;
    @FXML
    private javafx.scene.control.TextField txtCodigo2;
    
    @FXML
    public void añadirArticulo() throws ClassNotFoundException, SQLException{
        
        //Fem visibles
        paneInsertar.setVisible(true);
        insertarArticulo.setVisible(true);

        //Agafem les dades de la casella
        String nombre = this.txtNombre.getText();
        String codigo = this.txtCodigo.getText();
        double precioVenta = 0;
        precioVenta= Double.parseDouble(this.txtPrecioVenta.getText());
        double gastosEnvio = 0;
        gastosEnvio= Double.parseDouble(this.txtGastosEnvio.getText());
        double tPrep = 0;
        tPrep = Double.parseDouble(this.txtTPrep.getText());
    
        String errores = "";
        
        if(nombre == null){
            errores += "debes introducir el nombre\n";
        }
        if(codigo == null){
            errores += "debes introducir el codigo\n";
        }
        if(precioVenta == 0){
            errores += "debes introducir el precio de venta\n";
        }
        if(gastosEnvio == 0){
            errores += "debes introducir los gastos de envio\n";
        }
        if(tPrep == 0){
            errores += "debes introducir el tiempo de preparacion\n";
        }
        
        if(errores.isEmpty()){
            Articulo a = new Articulo(codigo, nombre, precioVenta, gastosEnvio, tPrep);
            insertar(a);
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }
    
    private void insertar(Articulo a) throws ClassNotFoundException, SQLException {
        datos.addArticulo(a);
        paneInsertar.setVisible(false);
        insertarArticulo.setVisible(false);
    }
    
    
    
    @FXML
    public void eliminarArticulo() throws SQLException{
        paneEliminar.setVisible(true);
        botoEliminar.setVisible(true);
        String codigo2 = this.txtCodigo2.getText();
        
        
        String errores = "";
        
        if(codigo2 == null){
            errores += "debes introducir el nombre\n";
        }
        
        if(errores.isEmpty()){
            eliminar(codigo2);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }
    
    public void eliminar(String codigo) throws SQLException {
        datos.eliminarArticulo(codigo);
        paneEliminar.setVisible(false);
        botoEliminar.setVisible(false);
    }
    
    
    @FXML
    public void mostrarArticulos() throws SQLException{
        
        //importem els articles de la BD a través de datos en format ObservableList<Articulo>
        articles = datos.selectArticulos();
        //omplim la taula
        this.taulaArticles.setItems(articles);
        
        //Carreguem la taula per a que sigui visible
        taulaArticles.setVisible(true);
        botoSortirTaula.setVisible(true);
        sortirTaulaArticles();

    }
    
    @FXML
    private void sortirTaulaArticles() {
        taulaArticles.setVisible(false);
        botoSortirTaula.setVisible(false);
    }
    
    void closeWindows() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuArticulos.fxml"));
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
