

package com.veterinaria.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarCategoria(int idCategoria, String nombreCategoria, String descripcion, 
                                  Integer idProducto, int  idEstado, String creadoPor, 
                                  String modificadoPor, String accion) {
        String sql = "{call insertar_categoria(?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idCategoria, nombreCategoria, descripcion, idProducto, 
                            idEstado, creadoPor, modificadoPor, accion);
    }
}