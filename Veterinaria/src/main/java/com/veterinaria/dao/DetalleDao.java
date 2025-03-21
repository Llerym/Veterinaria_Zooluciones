

package com.veterinaria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DetalleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarDetalle(int idDetalle, String descripcion, double precioUnitario, 
                                double pagoTotal, int cantidad, int idFactura, 
                                int idProducto, int idEstado, String creadoPor, 
                                String modificadoPor, String accion) {
        String sql = "{call insertar_detalle(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idDetalle, descripcion, precioUnitario, pagoTotal, 
                            cantidad, idFactura, idProducto, idEstado, creadoPor, 
                            modificadoPor, accion);
    }
}