

package com.veterinaria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_COLOR_TB")
public class Color implements Serializable{

       private static final long serialVersionUID = 1L;
 
       private Long idColor;
       private String descripcionColor;
       
       public Color() {
       }
       
       public Color(String descripcionColor) {
           this.descripcionColor = descripcionColor;
       }
    
   
}