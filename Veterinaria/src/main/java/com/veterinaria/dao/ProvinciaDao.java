

package com.veterinaria.dao;

import com.veterinaria.domain.Mascota;
import com.veterinaria.domain.Provincia;
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
public class ProvinciaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall listarProvinciasCall;
    
    public void insertarProvincia( String nombreProvincia) {
        String sql = "{call pkg_fide_veterinaria.insertar_provincia_sp(?)}";
        jdbcTemplate.update(sql, nombreProvincia);
    }
    
    /////
    ///
    ///
    
    @PostConstruct
    public void init() {
        this.listarProvinciasCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")      
            .withProcedureName("listar_provincias_cr")
            .declareParameters(
                new SqlOutParameter("rc_provincias", OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<>(Provincia.class))
            );
    }
 
    @SuppressWarnings("unchecked")
    public List<Provincia> listarProvincias() {
        Map<String, Object> out = listarProvinciasCall.execute();
        return (List<Provincia>) out.get("rc_provincias");
    }
}
