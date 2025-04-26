
package com.veterinaria.dao;


import com.veterinaria.domain.Color;
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
public class ColorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarColor(String descripcionColor, int idEstado) {
        String sql = "{call  pkg_fide_veterinaria.insertar_color_sp(?, ?)}";
        jdbcTemplate.update(sql,  descripcionColor, idEstado);
    }
    
    private SimpleJdbcCall listarColoresCall;
 
    @PostConstruct
    public void init() {
        this.listarColoresCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")   
            .withProcedureName("listar_colores_cr")
            .declareParameters(
                new SqlOutParameter("rc_colores", OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<>(Color.class))
            );
    }

    @SuppressWarnings("unchecked")
    public List<Color> listarColores() {
        Map<String, Object> out = listarColoresCall.execute();
        return (List<Color>) out.get("rc_colores");
    }
    
}
