

package com.veterinaria.dao;
import com.veterinaria.domain.Canton;
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
public class CantonDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private SimpleJdbcCall listarCantonesCall; 
    

    public void insertarCanton(String nombreCanton, Integer idProvincia) {
        String sql = "{call pkg_fide_veterinaria.insertar_canton_sp(?, ?)}";
        jdbcTemplate.update(sql,  nombreCanton, idProvincia);
    }
    
    ///////
    ///
    ///
    
    
    @PostConstruct
    public void init() {
        this.listarCantonesCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")       
            .withProcedureName("listar_cantones_cr")
            .declareParameters(
                new SqlOutParameter("rc_cantones", OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<>(Canton.class))
            );
    }
 
    @SuppressWarnings("unchecked")
    public List<Canton> listarCantones() {
        Map<String, Object> out = listarCantonesCall.execute();
        return (List<Canton>) out.get("rc_cantones");
    }
}