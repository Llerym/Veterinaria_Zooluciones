/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.veterinaria.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;


@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_RAZA_TB")
public class Raza implements Serializable{

       private static final long serialVersionUID = 1L;
 
       private Integer idRaza;
       private String descripcionRaza;
       private Integer idEstado;
        private String estado;
       
       public Raza() {
       }
       
       public Raza(Integer idRaza, String descripcionRaza, Integer idEstado, String estado) {
           
           this.idRaza = idRaza;
           this.descripcionRaza = descripcionRaza;
           this.idEstado = idEstado;
           this.estado = estado;
       }
    
   
}
