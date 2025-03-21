


package com.veterinaria.dao;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FacturaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarFactura(int idFactura, Date fecha, int idCita, int idModoPago, 
                                int idDetalle, int idEstado, String creadoPor, 
                                String modificadoPor, String accion) {
        String sql = "{call insertar_factura(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idFactura, fecha, idCita, idModoPago, idDetalle, 
                            idEstado, creadoPor, modificadoPor, accion);
    }
}