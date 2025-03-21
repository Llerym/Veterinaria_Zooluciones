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
public class TratamientoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarTratamiento(int idTratamiento, String nombreTratamiento, String descripcionTratamiento,
                                    double montoTotal, int idEstado, String creadoPor, 
                                    String modificadoPor, String accion) {
        String sql = "{call insertar_tratamiento(?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idTratamiento, nombreTratamiento, descripcionTratamiento, 
                            montoTotal, idEstado, creadoPor, modificadoPor, accion);
    }
}
