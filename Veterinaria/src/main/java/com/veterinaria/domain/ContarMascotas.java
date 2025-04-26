

package com.veterinaria.domain;

import lombok.Data;


@Data
public class ContarMascotas {
    
    private Integer idCliente;
    private String nombreCliente;
    private Integer cantidadMascotas;    
}
