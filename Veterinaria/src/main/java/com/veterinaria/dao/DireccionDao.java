
package com.veterinaria.dao;
import com.veterinaria.domain.Direccion;
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
public class DireccionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private SimpleJdbcCall listarDireccionesCall; 
    
    
    public void insertarDireccion(String descripcion, int idDistrito, 
                                  int idCanton, int idProvincia
                                  ) {
        String sql = "{call pkg_fide_veterinaria.insertar_direccion_sp(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, descripcion, idDistrito, idCanton, 
                            idProvincia);
    }
    
    @PostConstruct
    public void init() {
        this.listarDireccionesCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")       // tu paquete
            .withProcedureName("listar_direcciones_cr")
            .declareParameters(
                new SqlOutParameter("rc_direcciones", OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<>(Direccion.class))
            );
    }
 
    @SuppressWarnings("unchecked")
    public List<Direccion> listarDirecciones() {
        Map<String, Object> out = listarDireccionesCall.execute();
        return (List<Direccion>) out.get("rc_direcciones");
    }
}