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

    public void insertarRaza(String descripcionRaza) {
        String sql = "{call pkg_fide_veterinaria.insertar_raza_sp(?)}";
        jdbcTemplate.update(sql, descripcionRaza);
    }
}
