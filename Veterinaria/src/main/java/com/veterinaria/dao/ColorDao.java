
package com.veterinaria.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ColorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarColor(int idColor, String descripcion, int idEstado, String creadoPor, String modificadoPor, String accion) {
        String sql = "{call vet_solicitudes_pkg.insertar_color(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idColor, descripcion, idEstado, creadoPor, modificadoPor, accion);
    }
}
