

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
    
    private SimpleJdbcCall listarMascotasCall;


    public void insertarMascota( String nombreMascota, Integer edad, double peso, 
                                Date fechaNacimiento, Integer idRaza, Integer idColor, Integer idCliente) {
        String sql = "{call pkg_fide_veterinaria.insertar_mascota_sp(?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql,  nombreMascota, edad, peso, fechaNacimiento, 
                            idRaza, idColor, idCliente);
    }
    
    public void eliminarMascota(Integer idMascota) {
        String sql = "{call pkg_fide_veterinaria.eliminar_mascota_sp(?)}";
        jdbcTemplate.update(sql,  idMascota);
    }
    
    public void actualizarMascota(Integer idMascota, String nombreMascota, Integer edad, double peso,
                                  Date fechaNacimiento, Integer idRaza, Integer idColor, Integer idCliente,
                                  Integer idEstado) {
        String sql = "{call pkg_fide_veterinaria.actualizar_mascota(?,?,?,?,?,?,?,?,?)}";
        jdbcTemplate.update(sql, idMascota, nombreMascota, edad, peso,
                            new java.sql.Timestamp(fechaNacimiento.getTime()),
                            idRaza, idColor, idCliente, idEstado);
    }
 
    // cargar una sola mascota por ID
    public Mascota buscarPorId(Integer idMascota) {
        String sql =
                "SELECT m.ID_MASCOTA        AS idMascota, " +
            "       m.NOMBRE_MASCOTA    AS nombreMascota, " +
            "       m.EDAD              AS edad, " +
            "       m.PESO              AS peso, " +
            "       m.FECHA_NACIMIENTO  AS fechaNacimiento, " +
            "       m.ID_RAZA           AS idRaza, " +
            "       m.ID_COLOR          AS idColor, " +
            "       m.ID_CLIENTE        AS idCliente, " +
            "       m.ID_ESTADO         AS idEstado, " +
            "       r.DESCRIPCION_RAZA   AS descripcionRaza, " +
            "       cl.DESCRIPCION_COLOR AS descripcionColor, " +
            "       c.NOMBRE_CLIENTE     AS nombreCliente, " +
            "       e.DESCRIPCION_ESTADO AS estado " +
            "  FROM FIDE_MASCOTA_TB m " +
            "  JOIN FIDE_RAZA_TB r    ON m.ID_RAZA    = r.ID_RAZA " +
            "  JOIN FIDE_COLOR_TB cl  ON m.ID_COLOR   = cl.ID_COLOR " +
            "  JOIN FIDE_CLIENTE_TB c ON m.ID_CLIENTE = c.ID_CLIENTE " +
            "  JOIN FIDE_ESTADO_TB e  ON m.ID_ESTADO  = e.ID_ESTADO " +
            " WHERE m.ID_MASCOTA = ?";
        return jdbcTemplate.queryForObject(
            sql,
            new BeanPropertyRowMapper<>(Mascota.class),
            idMascota
        );
    }
    
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