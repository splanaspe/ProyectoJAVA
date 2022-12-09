/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupofp.modelo;
import java.io.Serializable;



public class Articulo implements Serializable{
    
   
   
    private String codigo;
    
   
    private String nombre;
    

    private Double precioVenta;
    

    private Double gastosEnvio;
    
 
    private double tPrep; // ES el tiempo en minutos

    public Articulo(String codigo, String nombre, Double precioVenta, Double gastosEnvio, double tPrep) {
        this.codigo = codigo;
        this.nombre= nombre;
        this.precioVenta = precioVenta;
        this.gastosEnvio = gastosEnvio;
        this.tPrep = tPrep;
    }

    public Articulo(){
        
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public Double getGastosEnvio() {
        return gastosEnvio;
    }

    public double gettPrep() {
        return tPrep;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setGastosEnvio(Double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public void settPrep(double tPrep) {
        this.tPrep = tPrep;
    }

    @Override
    public String toString() {
        return "Articulo{" + "codigo=" + codigo + ", nombre=" + nombre + ", precioVenta=" + precioVenta + ", gastosEnvio=" + gastosEnvio + ", tPrep=" + tPrep + '}';
    }
    
   
}