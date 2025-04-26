

package com.veterinaria.domain;

import java.util.Date;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data   
@Table(name = "FIDE_VETERINARIO_TB")
public class Veterinario implements Serializable       
{
    private static final long serialVersionUID = 1L;
    
    private Integer idVeterinario;
    private String nombreVeterinario; 
    private String telefonoVeterinario;
    private String emailVeterinario;
    private Integer idDireccion;
    private String descripcion;
    private Integer idEstado;
    private String estado;
    
    
    public Veterinario() {
       }

    public Veterinario(Integer idVeterinario, String nombreVeterinario, String telefonoVeterinario, String emailVeterinario, Integer idDireccion, String descripcion, Integer idEstado, String estado) {
        this.idVeterinario = idVeterinario;
        this.nombreVeterinario = nombreVeterinario;
        this.telefonoVeterinario = telefonoVeterinario;
        this.emailVeterinario = emailVeterinario;
        this.idDireccion = idDireccion;
        this.descripcion = descripcion;
        this.idEstado = idEstado;
        this.estado = estado;
    }
       
    
       
       
}


  
    