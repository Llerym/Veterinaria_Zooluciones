

package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MascotaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarMascota(int idMascota, String nombreMascota, int edad, double peso, 
                                Date fechaNacimiento, int idRaza, int idColor, int idEstado, 
                                String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_mascota(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idMascota, nombreMascota, edad, peso, fechaNacimiento, 
                            idRaza, idColor, idEstado, creadoPor, modificadoPor, accion);
    }
}