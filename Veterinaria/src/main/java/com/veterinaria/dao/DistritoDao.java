
package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DistritoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarDistrito(String nombreDistrito, Integer idCanton, Integer idProvincia ) {
        String sql = "{call pkg_fide_veterinaria.insertar_distrito_sp(?, ?, ?)}";
        jdbcTemplate.update(sql, nombreDistrito, idCanton, idProvincia);
    }
}