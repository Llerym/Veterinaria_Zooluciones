

package com.veterinaria.domain;

import java.util.Date;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data   //Generar por dejabo los set y get
@Table(name = "FIDE_PRODUCTO_TB")
public class Producto implements Serializable       
{
    private static final long serialVersionUID = 1L;
    
    private Integer idProducto;
    private String nombreProducto; 
    private Integer precioVenta;
    private Integer precioCompra; 
    private Integer cantidad; 
     private Integer idEstado; 
    private String estado; 
    
    
    
    public Producto() {
    }

    public Producto(Integer idProducto, String nombreProducto, Integer precioVenta, Integer precioCompra, Integer cantidad, Integer idEstado, String estado) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.idEstado = idEstado;
        this.estado = estado;
    }

   
       
    
    
    
       
       
}


  
    