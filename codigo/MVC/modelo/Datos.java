
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupofp.modelo;
import Conectors.MiConexion;
import java.sql.*; 
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author pcsalvador
 */

/*FUENTE

    INSERTAR DATOS DE JAVA A BD
    https://www.cablenaranja.com/como-insertar-datos-desde-una-aplicacion-java-hacia-mysql/
*/

public class Datos {
    //articulo
    private Articulo articulo; 
    private String codigo;
    private String nombre;
    private Double precioVenta;
    private Double gastosEnvio;
    private double tPrep; // ES el tiempo en minutos
    
    //Cliente
    private Cliente cliente;
    private String nif;
    private ArrayList<String> dniClientesPremium = new ArrayList<String>();
    
    //Pedido
    private Pedido pedido; 
    private int i;
    private boolean eliminado;
    
    
    private MiConexion conector;
    private Connection con;
    
    public Datos() throws ClassNotFoundException, SQLException{
        
    conector = new MiConexion("bdmarcsalvador","root","1997");
        
    }
    
    
    //
    // ARTICULOS
    //
    public void addArticulo(Articulo a) throws ClassNotFoundException, SQLException{
       //listaArticulos.addArticulo(a);
        conector.insertarArticulo(a);
    }

    public ObservableList<Articulo> selectArticulos() throws SQLException{
        return conector.selectArticulos(); 
    }

    public Articulo getArticuloDadoCodigo(String codigo) throws ClassNotFoundException{

        articulo = conector.selectArticuloDadoCodigo(codigo); 

        return articulo;
    }
    
    public void eliminarArticulo(String codigo) throws SQLException{

        conector.eliminarArticulo(codigo); 

    }
    

    
    
    //
    // CLIENTES
    //
    public void addClienteE(ClienteEstandard c) throws SQLException{
        conector.insertarClienteE(c);  
    }
    
    public void addClienteP(ClientePremium c) throws SQLException{
        conector.insertarClienteP(c); 
    }
    
    public ObservableList<ClienteEstandard> selectClientesEstandard() throws SQLException{

        return conector.selectClientesEstandard(); 
    }
    
    public ObservableList<ClientePremium> selectClientesPremium() throws SQLException{
        return conector.selectClientesPremium(); 
    }
    

    public void eliminarCliente(String nif) throws SQLException{
        conector.eliminarCliente(nif);

    }
    
    
    //
    // PEDIDOS
    // 
    public void addPedido(Pedido p) throws SQLException{
        conector.insertarPedido(p);
    }
    
    public ObservableList<Pedido> selectPedidos() throws SQLException{
        return conector.selectPedidos(); 
    }
    
    public void eliminarPedido(int n) throws SQLException{
        
        conector.eliminarPedido(n); 
        
}

    public void eliminarClienteE(String nif) throws SQLException {
        
        conector.eliminarClienteE(nif);
    }
    
    
    public void eliminarClienteP(String nif) throws SQLException {
        
        conector.eliminarClienteP(nif);
    }
    
}