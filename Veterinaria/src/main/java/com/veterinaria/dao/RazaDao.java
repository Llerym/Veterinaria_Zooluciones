/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.veterinaria.dao;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RazaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarRaza(int idRaza, String descripcionRaza, int idEstado, 
                             String creadoPor, String modificadoPor, String accion) {
        String sql = "{call vet_solicitudes_pkg.insertar_raza(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idRaza, descripcionRaza, idEstado, 
                            creadoPor, modificadoPor, accion);
    }
}
