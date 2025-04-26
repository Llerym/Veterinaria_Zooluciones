

package com.veterinaria.domain;

import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;


@Data
@Table(name = "FIDE_DISTRITO_TB")
public class Distrito implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer idDistrito;
    private String nombreDistrito;
    private Integer idCanton;
    private String nombreCanton;
    private Integer idProvincia;
    private String nombreProvincia;
    private Integer idEstado;
    private String estado;
    
   
    
    public Distrito() {
        
        
    }

    public Distrito(Integer idDistrito, String nombreDistrito, Integer idCanton, String nombreCanton, Integer idProvincia, String nombreProvincia, Integer idEstado, String estado) {
        this.idDistrito = idDistrito;
        this.nombreDistrito = nombreDistrito;
        this.idCanton = idCanton;
        this.nombreCanton = nombreCanton;
        this.idProvincia = idProvincia;
        this.nombreProvincia = nombreProvincia;
        this.idEstado = idEstado;
        this.estado = estado;
    }

    
    
    
    
    
}
