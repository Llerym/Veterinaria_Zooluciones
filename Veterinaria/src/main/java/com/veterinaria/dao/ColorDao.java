
package com.veterinaria.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ColorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarColor(String descripcion, int idEstado) {
        String sql = "{call  pkg_fide_veterinaria.insertar_color_sp(?, ?)}";
        jdbcTemplate.update(sql,  descripcion, idEstado);
    }
}
