
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupofp.modelo;
import java.io.Serializable;




/**
 *
 * @author pcsalvador
 */



public class Pedido implements Serializable{
    

    private int numPedido; 
    private String NIF; 
    private String codigoArticulo; 
    private int unidades; 
    private String fecha; 
    private String hora;  
    private Double precioPedido; 
    
    //IMPORTANT: Inicialitzem el pedido sense calcular el preu
    public Pedido(int n, String nif, String c, int unidades, String f, String h, double p){
        numPedido=n;
        NIF=nif;
        codigoArticulo=c;
        this.unidades=unidades; 
        fecha=f;
        hora=h;
        precioPedido = p;
    }

    public Pedido(){
        
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setPrecioPedido(Double precioPedido) {
        this.precioPedido = precioPedido;
    }
 

    public int getNumPedido() {
        return numPedido;
    }

    public String getNIF() {
        return NIF;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Double getPrecioPedido() {
        return precioPedido;
    }
    
     @Override
    public String toString() {
        return "Pedido{" + "numPedido=" + numPedido + ", NIF=" + NIF + ", codigoArticulo=" + codigoArticulo + ", unidades=" + unidades + ", fecha=" + fecha + ", hora=" + hora + ", precioPedido=" + precioPedido + '}';
    }
}