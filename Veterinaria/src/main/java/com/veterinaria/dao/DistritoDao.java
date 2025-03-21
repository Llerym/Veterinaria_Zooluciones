
package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DistritoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarDistrito(int idDistrito, String nombreDistrito, int idEstado, 
                                 String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_distrito(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idDistrito, nombreDistrito, idEstado, creadoPor, 
                            modificadoPor, accion);
    }
}