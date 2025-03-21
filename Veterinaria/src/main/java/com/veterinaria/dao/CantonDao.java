

package com.veterinaria.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CantonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarCanton(int idCanton, String nombreCanton, Integer idProvincia, int idEstado, 
                               String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_canton(?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idCanton, nombreCanton, idProvincia, idEstado, creadoPor, 
                            modificadoPor, accion);
    }
}