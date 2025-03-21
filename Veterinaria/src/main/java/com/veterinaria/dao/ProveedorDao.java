

package com.veterinaria.dao;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProveedorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarProveedor(int idProveedor, String nombreProveedor, String telefono, 
                                  String email, int idProducto, int idEstado, 
                                  String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_proveedor(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idProveedor, nombreProveedor, telefono, email, 
                            idProducto, idEstado, creadoPor, modificadoPor, accion);
    }
}
