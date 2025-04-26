
package com.veterinaria.dao;
import com.veterinaria.domain.Direccion;
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
    
    public void eliminarDireccion(Integer idDireccion) {
        String sql = "{call pkg_fide_veterinaria.eliminar_direccion_sp(?)}";
        jdbcTemplate.update(sql,  idDireccion);
    }
    
    public void actualizarDireccion(Integer idDireccion, String descripcion,
                                    Integer idDistrito, Integer idCanton, Integer idProvincia,
                                    Integer idEstado) {
        String sql = "{call pkg_fide_veterinaria.actualizar_direccion_sp(?,?,?,?,?,?)}";
        jdbcTemplate.update(sql, idDireccion, descripcion, idDistrito, idCanton,
                            idProvincia, idEstado);
    }
 
    
    public Direccion buscarPorId(Integer idDireccion) {
    String sql =
        "SELECT d.ID_DIRECCION AS idDireccion, " +
        "       d.DESCRIPCION AS descripcion, " +
        "       d.ID_DISTRITO AS idDistrito, " +
        "       d.ID_CANTON AS idCanton, " +
        "       d.ID_PROVINCIA AS idProvincia, " +
        "       d.ID_ESTADO AS idEstado, " +
        "       dis.NOMBRE_DISTRITO AS nombreDistrito, " +
        "       can.NOMBRE_CANTON AS nombreCanton, " +
        "       prov.NOMBRE_PROVINCIA AS nombreProvincia, " +
        "       e.DESCRIPCION_ESTADO AS estado " +
        "  FROM FIDE_DIRECCION_TB d " +
        "  JOIN FIDE_DISTRITO_TB dis ON d.ID_DISTRITO = dis.ID_DISTRITO " +
        "  JOIN FIDE_CANTON_TB can   ON d.ID_CANTON = can.ID_CANTON " +
        "  JOIN FIDE_PROVINCIA_TB prov ON d.ID_PROVINCIA = prov.ID_PROVINCIA " +
        "  JOIN FIDE_ESTADO_TB e ON d.ID_ESTADO = e.ID_ESTADO " +
        " WHERE d.ID_DIRECCION = ?";

    return jdbcTemplate.queryForObject(
        sql,
        new BeanPropertyRowMapper<>(Direccion.class),
        idDireccion
    );
}
    
    @PostConstruct
    public void init() {
        this.listarDireccionesCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")       
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