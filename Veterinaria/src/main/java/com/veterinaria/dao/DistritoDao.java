
package com.veterinaria.dao;


import com.veterinaria.domain.Distrito;
import com.veterinaria.domain.Provincia;
import jakarta.annotation.PostConstruct;
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
public class DistritoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private SimpleJdbcCall listarDistritosCall;

    public void insertarDistrito(String nombreDistrito, Integer idCanton, Integer idProvincia ) {
        String sql = "{call pkg_fide_veterinaria.insertar_distrito_sp(?, ?, ?)}";
        jdbcTemplate.update(sql, nombreDistrito, idCanton, idProvincia);
    }
    
    /////
    ///
    ///
    
    @PostConstruct
    public void init() {
        this.listarDistritosCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")      
            .withProcedureName("listar_distritos_cr")
            .declareParameters(
                new SqlOutParameter("rc_distritos", OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<>(Distrito.class))
            );
    }
 
    @SuppressWarnings("unchecked")
    public List<Distrito> listarDistritos() {
        Map<String, Object> out = listarDistritosCall.execute();
        return (List<Distrito>) out.get("rc_distritos");
    }   
}