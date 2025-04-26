

package com.veterinaria.domain;

import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;


@Data
@Table(name = "FIDE_PROVINCIA_TB")
public class Provincia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer idProvincia;
    private String nombreProvincia;
    private Integer idEstado;
    private String estado;
    
    public Provincia() {
        
        
    }

    public Provincia(Integer idProvincia, String nombreProvincia, Integer idEstado, String estado) {
        this.idProvincia = idProvincia;
        this.nombreProvincia = nombreProvincia;
        this.idEstado = idEstado;
        this.estado = estado;
    }
    
    
    
}
