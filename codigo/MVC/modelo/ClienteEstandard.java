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


public class ClienteEstandard implements Serializable{
    

    private String NIF; 
    

    private String nombre; 
    

    private String domicilio; 
    
 
    private String email;
    
    public ClienteEstandard(String n, String nif, String d, String email ){
        nombre = n;
        NIF= nif; 
        domicilio = d; 
        this.email=email; 
    }
    
    public ClienteEstandard(){
        
    }

    public String getNIF() {
        return NIF;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClienteEstandard{" + "NIF=" + NIF + ", nombre=" + nombre + ", domicilio=" + domicilio + ", email=" + email + '}';
    }
    
    
   
    
    
   
    
}