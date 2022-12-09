/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package grupofp.controlador;

import grupofp.modelo.Articulo;
import grupofp.modelo.Datos;
import grupofp.modelo.Pedido;
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
public class MenuPedidosController implements Initializable {
    Datos datos;
    ObservableList<Pedido> pedidos;
    
    @FXML
    private TableColumn colCodigo;
    private TableColumn colNombre;
    private TableColumn colPrecioVenta;
    private TableColumn colGastos;
    private TableColumn colTiempo;
   
    @FXML
    private Button botoSortirTaula;

    private Button botoInsertarPedido;
    @FXML
    private Button botoEliminarPedido;
    @FXML
    private Button botoMostrarPedidos;
    @FXML
    private javafx.scene.control.TextField txtNumPedido;
    @FXML
    private javafx.scene.control.TextField txtNIF;
    @FXML
    private javafx.scene.control.TextField txtCodigoArticulo;
    @FXML
    private javafx.scene.control.TextField txtUnidades;
    @FXML
    private javafx.scene.control.TextField txtFecha;
    @FXML
    private javafx.scene.control.TextField txtHora;
    @FXML
    private javafx.scene.control.TextField txtPrecioPedido;
    @FXML
    private javafx.scene.control.TextField txtNumPedido2;
    @FXML
    private Button insertarPedido;
    @FXML
    private TableView<Pedido> taulaPedidos;
    @FXML
    private TableColumn colNumPedidos;
    @FXML
    private TableColumn colNIF;
    @FXML
    private TableColumn colCodigoArticulo;
    @FXML
    private TableColumn colUnidades;
    @FXML
    private TableColumn colFecha;
    @FXML
    private TableColumn colHora;
    @FXML
    private TableColumn colPrecio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        //Inicialitzem la instancia de DATOS
        try {
            datos = new Datos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) { 
            Logger.getLogger(MenuPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        //Inicialitzem la taula, la configurem per a després omplir-la 
        pedidos = FXCollections.observableArrayList();
        this.colNumPedidos.setCellFactory(new PropertyValueFactory("numPedido"));
        this.colNIF.setCellFactory(new PropertyValueFactory("NIF"));
        this.colCodigoArticulo.setCellFactory(new PropertyValueFactory("codigoArticulo"));
        this.colUnidades.setCellFactory(new PropertyValueFactory("unidades"));
        this.colFecha.setCellFactory(new PropertyValueFactory("fecha"));
        this.colHora.setCellFactory(new PropertyValueFactory("hora"));
        this.colPrecio.setCellFactory(new PropertyValueFactory("precioPedido"));
    }    
  
    @FXML
    private Button botoEliminar;
    private Button insertarArticulo;
    @FXML
    private Pane paneInsertar;
    @FXML
    private Pane paneEliminar;
   
    public void añadirPedido() throws ClassNotFoundException, SQLException{
        
        paneInsertar.setVisible(true);
        insertarPedido.setVisible(true);
        
        int numPedido=0; 
        numPedido = Integer.parseInt(this.txtNumPedido.getText());
        String NIF = this.txtNIF.getText();
        String codigoArticulo = this.txtCodigoArticulo.getText();
        int unidades = Integer.parseInt(this.txtUnidades.getText());
        String fecha = this.txtFecha.getText();
        String hora = this.txtHora.getText();
        double precioPedido = 0;
        precioPedido = Double.parseDouble(this.txtPrecioPedido.getText());
        
        String errores = "";
        
        if(numPedido==0){
            errores += "debes introducir el nº de pedido\n";
        } 
        if(NIF== null){
            errores += "debes introducir el NIF de la empresa\n";
        }
        if(codigoArticulo == null){
            errores += "debes introducir el codigo del articulo del pedido\n";
        }
        if(unidades== 0){
            errores += "no pueden ser 0 las unidades, minimo 1\n";
        }
        if(fecha == null){
            errores += "formato fecha: aaaa-mm-dd \n";
        }
        if(hora == null){
            errores += "formato hora: hh:mm \n";
        }
        if(precioPedido == 0){
            errores += "formato hora: hh:mm \n";
        }
        
        if(errores.isEmpty()){
            Pedido p = new Pedido(numPedido,NIF,codigoArticulo,unidades,fecha,hora,precioPedido);
            insertar(p);
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        } 
    }
    

    private void insertar(Pedido p) throws ClassNotFoundException, SQLException {
        datos.addPedido(p);
        paneInsertar.setVisible(false);
        insertarPedido.setVisible(false);
    }
    
    
    
    @FXML
    public void eliminarPedido() throws SQLException{
        paneEliminar.setVisible(true);
        botoEliminar.setVisible(true);
        int n = 0;
        
        n=Integer.parseInt(this.txtNumPedido2.getText() );
        
        String errores = "";
        if(n==0){
            errores += "debes introducir el nº de pedido\n";
        }
        if(errores.isEmpty()){
            eliminar(n);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(errores);
            alert.showAndWait();
        }
    }
    
    
    public void eliminar(int n) throws SQLException {
        datos.eliminarPedido(n);
        paneEliminar.setVisible(false);
        botoEliminar.setVisible(false);
    }
    
    
    @FXML
    public void mostrarPedidos() throws SQLException{
        
        //importem els pedidos de la BD en format ObservableList 
        pedidos = datos.selectPedidos();
        //omplim la taula
        this.taulaPedidos.setItems(pedidos);
        
        //Carreguem la taula
        taulaPedidos.setVisible(true);
        botoSortirTaula.setVisible(true);
        sortirTaulaPedidos();
    }
    
    private void sortirTaulaPedidos() {
        taulaPedidos.setVisible(false);
        botoSortirTaula.setVisible(false);
    }
    
    
    void closeWindows() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grupofp.vista.MenuPedidos.fxml"));
        Parent root = loader.load();
        MenuPrincipalController controlador = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
