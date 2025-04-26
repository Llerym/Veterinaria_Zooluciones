

package com.veterinaria.dao;

import com.veterinaria.domain.Mascota;

import jakarta.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class MascotaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarMascota( String nombreMascota, int edad, double peso, 
                                Date fechaNacimiento, int idRaza, int idColor, int idCliente) {
        String sql = "{call pkg_fide_veterinaria.insertar_mascota_sp(?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql,  nombreMascota, edad, peso, fechaNacimiento, 
                            idRaza, idColor, idCliente);
    }
    
    
    private SimpleJdbcCall listarMascotasCall;
 
    @PostConstruct

    public void init() {

        this.listarMascotasCall = new SimpleJdbcCall(jdbcTemplate)

            .withCatalogName("pkg_fide_veterinaria")       // tu paquete

            .withProcedureName("listar_mascotas_cr")

            .declareParameters(

                new SqlOutParameter("rc_mascotas", OracleTypes.CURSOR,

                    new BeanPropertyRowMapper<>(Mascota.class))

            );

    }
 
    @SuppressWarnings("unchecked")

    public List<Mascota> listarMascotas() {

        Map<String, Object> out = listarMascotasCall.execute();

        return (List<Mascota>) out.get("rc_mascotas");

    }

 
    
    
    
    
    
    
    
    
}