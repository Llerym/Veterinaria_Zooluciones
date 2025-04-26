

package com.veterinaria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

    
@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_CLIENTE_TB")
public class Cliente {
    
       private static final long serialVersionUID = 1L;
 
       private Integer idCliente;
       private String nombreCliente;
       private String telefono;
       private String email; 
       private Integer idMascota; 
       private String nombreMascota;
       private Integer idDireccion;
       private String descripcion;
       private Integer idEstado;
       private String estado;
               
       
    public Cliente() {
    }

    public Cliente(Integer idCliente, String nombreCliente, String telefono, String email, Integer idMascota, String nombreMascota, Integer idDireccion, String descripcion, Integer idEstado, String estado) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.email = email;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.idDireccion = idDireccion;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.estado = estado;
    }

    

    
}
