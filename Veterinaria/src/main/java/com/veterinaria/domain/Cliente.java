

package com.veterinaria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

    
@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_CLIENTE_TB")
public class Cliente {
    
       private static final long serialVersionUID = 1L;
 
       private Long idCliente;
       private String nombreCliente;
       private int telefono;
       private String email; 
       private int idMascota; 
       private int idDireccion;
       
       public Cliente() {
       }
       
       public Cliente(String nombreCliente) {
           this.nombreCliente = nombreCliente;
           // this.telefono..... etc etc
       }
    
}
