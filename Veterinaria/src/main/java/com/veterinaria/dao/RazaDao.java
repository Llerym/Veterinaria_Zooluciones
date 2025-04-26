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
 
     public void eliminarRaza(Integer idRaza) {
        String sql = "{call pkg_fide_veterinaria.eliminar_raza_sp(?)}";
        jdbcTemplate.update(sql,  idRaza);
    }
    
    public void actualizarRaza(Integer idRaza, String descripcionRaza,
                                  Integer idEstado, String estado) {
        String sql = "{call pkg_fide_veterinaria.actualizar_raza_sp(?,?,?,?)}";
        jdbcTemplate.update(sql, idRaza, descripcionRaza, idEstado, estado);
    }
    
    
        public Raza buscarPorId(Integer idRaza) {
        String sql =
                "SELECT c.ID_Raza        AS idRaza, " +
            "       c.DESCRIPCION_Raza AS descripcionRaza, " +
            "       c.ID_ESTADO         AS idEstado, " +
            "       e.DESCRIPCION_ESTADO AS estado " +
            "  FROM FIDE_Raza_TB c " +
            "  JOIN FIDE_ESTADO_TB e  ON c.ID_ESTADO  = e.ID_ESTADO " +
            " WHERE c.ID_Raza = ?";
        return jdbcTemplate.queryForObject(
            sql,
            new BeanPropertyRowMapper<>(Raza.class),
            idRaza
        );
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @PostConstruct

    public void init() {

        this.listarRazasCall = new SimpleJdbcCall(jdbcTemplate)

            .withCatalogName("pkg_fide_veterinaria")   

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
