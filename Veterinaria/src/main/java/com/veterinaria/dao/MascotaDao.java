

package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MascotaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarMascota( String nombreMascota, int edad, double peso, 
                                Date fechaNacimiento, int idRaza, int idColor, int idCliente) {
        String sql = "{call pkg_fide_veterinaria.insertar_mascota_sp(?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql,  nombreMascota, edad, peso, fechaNacimiento, 
                            idRaza, idColor);
    }
}