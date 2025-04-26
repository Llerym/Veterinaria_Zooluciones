

package com.veterinaria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_COLOR_TB")
public class Color implements Serializable{

       private static final long serialVersionUID = 1L;
 
       private Integer idColor;
       private String descripcionColor;
       private Integer idEstado;
       private String estado;
       
       public Color() {
       }
       
       public Color(Integer idColor, String descripcionColor, Integer idEstado, String estado ) {
           this.idColor = idColor;
           this.descripcionColor = descripcionColor;
           this.idEstado = idEstado;
           this.estado = estado;
       }
    
   
}