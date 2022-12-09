/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Conectors;
import grupofp.modelo.*;
import grupofp.modelo.Articulo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


public class MiConexion {
	private Connection conn;
	private Statement stnt;
	private String bbdd;
	private String user;
	private String contra;

        
        private String nombre;
        private Double precioVenta;
        private Double gastosEnvio;
        private Double tPrep; 
        private Articulo articulo; 
        private String codigo;
           
        
        PreparedStatement ps;
        String sql;
            
           
	public MiConexion(String bbdd, String user, String contra) throws SQLException, ClassNotFoundException {
		this.bbdd = bbdd;
		this.user = user;
		this.contra = contra;
		Class.forName("com.mysql.jdbc.Driver");
	}

	public void abrirConexion() throws SQLException {
            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdmarcsalvador","root","1997");
                JOptionPane.showMessageDialog(null, "Conexion exitosa");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        
	public void cerrarConexion() throws SQLException {
		conn.close();
	}

	public ResultSet ejecutarQuery(String query) throws SQLException {
		abrirConexion();
		ResultSet rs = stnt.executeQuery(query);
		return rs;
	}

    public Articulo selectArticuloDadoCodigo(String codigo) {
        
        try{
           abrirConexion();
           Statement consulta = conn.createStatement();
           
           ResultSet rs = consulta.executeQuery("select * from articulo where articulo.codigo='"+codigo+"'");
                
           //Omplim les dades
           nombre = rs.getString("nombre");
           
           precioVenta=rs.getDouble("precioVenta");
           gastosEnvio = rs.getDouble("gastosEnvio");
           tPrep = rs.getDouble("tPrep"); 
      
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        articulo = new Articulo(codigo, nombre,precioVenta, gastosEnvio, tPrep);
        return articulo; 
    }

        public void eliminarArticulo(String codigo) throws SQLException {
        
            abrirConexion(); 
            try{
            
                sql = "DELETE FROM articulo WHERE  nif ='"+codigo+"'";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se ha eliminado el articulo");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
            }
        
            
            cerrarConexion();
    }

    public void insertarArticulo(Articulo a) throws SQLException {
        abrirConexion();
        
        try{
            sql = "insert into articulo(codigo,nombre,precioVenta,gastosEnvio,tPrep) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getCodigo());
            ps.setString(2, a.getNombre());
            ps.setDouble(3, a.getPrecioVenta());
            ps.setDouble(4, a.getGastosEnvio());
            ps.setDouble(5, a.gettPrep());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha insertado el articulo");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
    }

    public ObservableList<Articulo> selectArticulos() throws SQLException {
        ResultSet rs = null; 
        ObservableList<Articulo> articulos = FXCollections.observableArrayList();
        
        abrirConexion();
        try{
           Statement consulta = conn.createStatement();
           rs = consulta.executeQuery("select * from articulo");   
           System.out.println(rs.getString(0));
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        while(rs.next()){
            codigo = rs.getString("codigo");
            nombre = rs.getString("nombre");
            precioVenta = rs.getDouble("precioVenta");
            gastosEnvio = rs.getDouble("gastosEnvio");
            tPrep = rs.getDouble("tPrep");
            
            Articulo a = new Articulo(codigo, nombre, precioVenta,gastosEnvio, tPrep);
            articulos.add(a);
        }
       
        cerrarConexion();
        return articulos; 
    }

    public void insertarClienteE(ClienteEstandard c) throws SQLException {
        
        abrirConexion();
        try{
           
            sql = "insert into clienteestandard(nombre,NIF,domicilio,email) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getNIF());
            ps.setString(3, c.getDomicilio());
            ps.setString(4, c.getEmail());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha insertado el cliente");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }   
        
        cerrarConexion();
    }
    
    public void insertarClienteP(ClientePremium c) throws SQLException {
        
        abrirConexion();
        try{
           
            sql = "insert into clientepremium(nombre,NIF,domicilio,email) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getNIF());
            ps.setString(3, c.getDomicilio());
            ps.setString(4, c.getEmail());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha insertado el cliente");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }   
        
        cerrarConexion();
    }

    public ObservableList<ClienteEstandard> selectClientesEstandard() throws SQLException {
        ResultSet rs = null; 
        ObservableList<ClienteEstandard> cli = FXCollections.observableArrayList();

        abrirConexion();
        
        try{
               
           Statement consulta = conn.createStatement();
           rs = consulta.executeQuery("select * from clienteestandard");      
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        
        while(rs.next()){
            String NIF= rs.getString("NIF");
            String nombre = rs.getString("nombre");
            String domicilio = rs.getString("domicilio");
            String email = rs.getString("email");

           ClienteEstandard c = new ClienteEstandard(NIF, nombre, domicilio, email);
            cli.add(c);
        }
        
        
        cerrarConexion();
        return cli; 
    }
    
    
     public ObservableList<ClientePremium> selectClientesPremium() throws SQLException {
        ResultSet rs = null; 
        ObservableList<ClientePremium> cli = FXCollections.observableArrayList();

        abrirConexion();
        
        try{
               
           Statement consulta = conn.createStatement();
           rs = consulta.executeQuery("select * from clientepremium");      
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        
        while(rs.next()){
            String NIF= rs.getString("NIF");
            String nombre = rs.getString("nombre");
            String domicilio = rs.getString("domicilio");
            String email = rs.getString("email");

           ClientePremium c = new ClientePremium(NIF, nombre, domicilio, email);
           cli.add(c);
        }
        
        
        cerrarConexion();
        return cli; 
    }

    public void insertarPedido(Pedido p) throws SQLException {
        
        abrirConexion();
        try{
            
            sql = "insert into pedido values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getNumPedido());
            ps.setString(2, p.getNIF());
            ps.setString(3, p.getCodigoArticulo());
            ps.setInt(4, p.getUnidades());
            ps.setString(5, p.getFecha());
            ps.setString(6,p.getHora());
            ps.setDouble(7,p.getPrecioPedido());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha insertado el pedido");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
        
        cerrarConexion();
        
    }

    public ObservableList<Pedido> selectPedidos() throws SQLException {
        ResultSet rs = null; 
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        
        abrirConexion();
        try{
           Statement consulta = conn.createStatement();
           rs = consulta.executeQuery("select * from articulo");   
            System.out.println(rs.getString(0));
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        while(rs.next()){
            int n = rs.getInt("numPedudo");
            String NIF = rs.getString("NIF");
            String codigo = rs.getString("codigoArticulo");
            int u = rs.getInt("unidades");
            String f = rs.getString("fecha");
            String h = rs.getString("hora");
            double precio = rs.getDouble("precioPedido");
            
            
            
            //public Pedido(int n, String nif, String c, int unidades, String f, String h, double p)
            Pedido p = new Pedido(n,NIF,codigo,u,f,h,precio);
            pedidos.add(p);
        }
       
        cerrarConexion();
        return pedidos; 
    }

    public void eliminarPedido(int n) throws SQLException {
        
       
       abrirConexion();
        try{
            
            sql = "DELETE FROM pedido  WHERE  numPedido = '"+n+"'";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha eliminado el pedido");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
          } 
        
        cerrarConexion();
    }

    public void eliminarCliente(String nif) throws SQLException {
        PreparedStatement ps;
        String sql;
        
        
        abrirConexion();
        try{
            
            sql = "DELETE FROM clienteestandard  WHERE  NIF = '"+nif+"'";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha eliminado el cliente");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
        cerrarConexion();
        
        abrirConexion();
        try{
            sql = "DELETE FROM clientepremium  WHERE  NIF = '"+nif+"'";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha eliminado el cliente");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
    }

    public void eliminarClienteE(String nif) throws SQLException {
            abrirConexion(); 
            try{
            
                sql = "DELETE FROM clienteestandard WHERE  NIF ='"+nif+"'";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se ha eliminado el cliente");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
            }
        
            
            cerrarConexion();
    }
    
    public void eliminarClienteP(String nif) throws SQLException {
            abrirConexion(); 
            try{
            
                sql = "DELETE FROM clientepremium WHERE  NIF ='"+nif+"'";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se ha eliminado el cliente");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
            }
        
            
            cerrarConexion();
    }
    
    
     
    
    
}