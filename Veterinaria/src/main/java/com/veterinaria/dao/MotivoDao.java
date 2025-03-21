

package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MotivoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarMotivo(int idMotivo, String descripcionMotivo, int idEstado, 
                               String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_motivo(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idMotivo, descripcionMotivo, idEstado, creadoPor, modificadoPor, accion);
    }
}

