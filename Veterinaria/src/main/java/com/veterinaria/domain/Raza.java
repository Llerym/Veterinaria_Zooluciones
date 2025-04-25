/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.veterinaria.domain;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
/**
 *
 * @author llech
 */


@Data   //Generar por dejabo los set y get
@Entity
@Table(name = "FIDE_RAZA_TB")
public class Raza implements Serializable{

       private static final long serialVersionUID = 1L;
 
    
       private String descripcionRaza;
       
       public Raza() {
       }
       
       public Raza(String descripcionRaza) {
           this.descripcionRaza = descripcionRaza;
       }
    
    
    
    
    
    
    
    
}
