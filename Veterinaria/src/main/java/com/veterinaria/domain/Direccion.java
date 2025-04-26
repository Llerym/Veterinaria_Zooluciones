
package com.veterinaria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Table(name = "FIDE_DIRECCION_TB")
public class Direccion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer idDireccion;
    private String descripcion;
    private Integer idDistrito;
    private Integer idCanton;
    private Integer idProvincia;
    private Integer idEstado;
    
    public Direccion() {
        
    }

    public Direccion(Integer idDireccion, String descripcion, Integer idDistrito, Integer idCanton, Integer idProvincia, Integer idEstado) {
        this.idDireccion = idDireccion;
        this.descripcion = descripcion;
        this.idDistrito = idDistrito;
        this.idCanton = idCanton;
        this.idProvincia = idProvincia;
        this.idEstado = idEstado;
    }
    
    
    
}
