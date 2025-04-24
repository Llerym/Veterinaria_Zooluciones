

package com.veterinaria.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CantonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarCanton(String nombreCanton, Integer idProvincia) {
        String sql = "{call pkg_fide_veterinaria.insertar_canton_sp(?, ?)}";
        jdbcTemplate.update(sql,  nombreCanton, idProvincia);
    }
}