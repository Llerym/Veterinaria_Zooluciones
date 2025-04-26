
package com.veterinaria.dao;


import com.veterinaria.domain.Color;
import com.veterinaria.domain.Color;
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
public class ColorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall listarColoresCall;

    public void insertarColor(String descripcionColor, Integer idEstado) {
        String sql = "{call  pkg_fide_veterinaria.insertar_color_sp(?, ?)}";
        jdbcTemplate.update(sql,  descripcionColor, idEstado);
    }
    
    
    
    public void eliminarColor(Integer idColor) {
        String sql = "{call pkg_fide_veterinaria.eliminar_color_sp(?)}";
        jdbcTemplate.update(sql,  idColor);
    }
    
    public void actualizarColor(Integer idColor, String descripcionColor,
                                  Integer idEstado) {
        String sql = "{call pkg_fide_veterinaria.actualizar_color_sp(?,?,?)}";
        jdbcTemplate.update(sql, idColor, descripcionColor, idEstado);
    }
 
    // cargar un solo color por ID
    public Color buscarPorId(Integer idColor) {
        String sql =
                "SELECT c.ID_COLOR        AS idColor, " +
            "       c.DESCRIPCION_COLOR AS descripcionColor, " +
            "       c.ID_ESTADO         AS idEstado, " +
            "       e.DESCRIPCION_ESTADO AS estado " +
            "  FROM FIDE_COLOR_TB c " +
            "  JOIN FIDE_ESTADO_TB e  ON c.ID_ESTADO  = e.ID_ESTADO " +
            " WHERE c.ID_COLOR = ?";
        return jdbcTemplate.queryForObject(
            sql,
            new BeanPropertyRowMapper<>(Color.class),
            idColor
        );
    }
    
    
    
    
    
    
    
 
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
