
package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ProductoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarProducto(int idProducto, String nombreProducto, String descripcion, 
                                 double precioVenta, double precioCompra, int cantidad, 
                                 int idCategoria, int idProveedor, int idEstado, 
                                 String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_producto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idProducto, nombreProducto, descripcion, precioVenta, 
                            precioCompra, cantidad, idCategoria, idProveedor, idEstado, 
                            creadoPor, modificadoPor, accion);
    }
}

