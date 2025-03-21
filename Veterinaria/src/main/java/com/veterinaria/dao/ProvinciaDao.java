

package com.veterinaria.dao;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinciaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarProvincia(int idProvincia, String nombreProvincia, int idEstado, 
                                  String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_provincia(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idProvincia, nombreProvincia, idEstado, 
                            creadoPor, modificadoPor, accion);
    }
}
