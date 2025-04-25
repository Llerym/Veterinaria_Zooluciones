/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.veterinaria.dao;

import com.veterinaria.domain.Raza;
import java.util.List;
import java.util.Map;
import jakarta.annotation.PostConstruct;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class RazaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarRaza(String descripcionRaza) {
        String sql = "{call pkg_fide_veterinaria.insertar_raza_sp(?)}";
        jdbcTemplate.update(sql, descripcionRaza);
    }
    

    private SimpleJdbcCall listarRazasCall;
 
    @PostConstruct

    public void init() {

        this.listarRazasCall = new SimpleJdbcCall(jdbcTemplate)

            .withCatalogName("vet_solicitudes_pkg")   

            .withProcedureName("listar_razas_cr")

            .declareParameters(

                new SqlOutParameter("rc_razas", OracleTypes.CURSOR,

                    new BeanPropertyRowMapper<>(Raza.class))

            );

    }
 
    @SuppressWarnings("unchecked")

    public List<Raza> listarRazas() {

        Map<String, Object> out = listarRazasCall.execute();

        return (List<Raza>) out.get("rc_razas");

    }
 
    
    
    
    
    
    
}
