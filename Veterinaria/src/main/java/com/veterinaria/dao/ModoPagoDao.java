
package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ModoPagoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarModoPago(int idMetodoPago, String descripcionPago, int idEstado, 
                                 String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_modo_pago(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idMetodoPago, descripcionPago, idEstado, creadoPor, modificadoPor, accion);
    }
}