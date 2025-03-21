
package com.veterinaria.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DireccionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarDireccion(int idDireccion, String descripcion, int idDistrito, 
                                  int idCanton, int idProvincia, int idEstado, 
                                  String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_direccion(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idDireccion, descripcion, idDistrito, idCanton, 
                            idProvincia, idEstado, creadoPor, modificadoPor, accion);
    }
}