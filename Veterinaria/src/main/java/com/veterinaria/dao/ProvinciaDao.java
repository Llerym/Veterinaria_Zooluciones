

package com.veterinaria.dao;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinciaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarProvincia( String nombreProvincia) {
        String sql = "{call pkg_fide_veterinaria.insertar_provincia_sp(?)}";
        jdbcTemplate.update(sql, nombreProvincia);
    }
}
