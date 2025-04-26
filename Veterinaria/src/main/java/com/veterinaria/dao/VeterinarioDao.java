
package com.veterinaria.dao;


import com.veterinaria.domain.Veterinario;
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
public class VeterinarioDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private SimpleJdbcCall listarVeterinariosCall;

    public void insertarVeterinario(String nombreVeterinario, String telefonoVeterinario,
                                     String emailVeterinario, int idDireccion) {
        String sql = "{call pkg_fide_veterinaria.insertar_veterinario_sp(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, nombreVeterinario, telefonoVeterinario, 
                            emailVeterinario, idDireccion);
    }
    
    public void eliminarVeterinario(Integer idVeterinario) {
        String sql = "{call pkg_fide_veterinaria.eliminar_veterinario_sp(?)}";
        jdbcTemplate.update(sql,  idVeterinario);
    }
    
    public void actualizarVeterinario(Integer idVeterinario, String nombreVeterinario,
                                        String telefonoVeterinario, String emailVeterinario, int idDireccion,
                                        Integer idEstado) {
        String sql = "{call pkg_fide_veterinaria.actualizar_veterinario_sp(?,?,?,?,?,?)}";
        jdbcTemplate.update(sql, idVeterinario, nombreVeterinario, telefonoVeterinario, emailVeterinario,
                            idDireccion, idEstado);
    }
    
    
    // solo trae un registro
    public Veterinario buscarPorId(Integer idVeterinario) {
    String sql =
        "SELECT v.ID_VETERINARIO     AS idVeterinario, " +
        "       v.NOMBRE_VETERINARIO AS nombreVeterinario, " +
        "       v.TELEFONO_VETERINARIO AS telefonoVeterinario, " +
        "       v.EMAIL_VETERINARIO   AS emailVeterinario, " +
        "       v.ID_DIRECCION        AS idDireccion, " +
        "       v.ID_ESTADO           AS idEstado, " +
        "       e.DESCRIPCION_ESTADO  AS estado " +
        "  FROM FIDE_VETERINARIO_TB v " +
        "  JOIN FIDE_ESTADO_TB e ON v.ID_ESTADO = e.ID_ESTADO " +
        " WHERE v.ID_VETERINARIO = ?";
    
    return jdbcTemplate.queryForObject(
        sql,
        new BeanPropertyRowMapper<>(Veterinario.class),
        idVeterinario
    );
}
    
    @PostConstruct
    public void init() {
        this.listarVeterinariosCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")       // tu paquete
            .withProcedureName("listar_veterinarios_cr")
            .declareParameters(
                new SqlOutParameter("rc_veterinarios", OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<>(Veterinario.class))
            );
    }
 
    @SuppressWarnings("unchecked")
    public List<Veterinario> listarVeterinarios() {
        Map<String, Object> out = listarVeterinariosCall.execute();
        return (List<Veterinario>) out.get("rc_veterinarios");
    }
    
    
    
    
    

    
    
}
