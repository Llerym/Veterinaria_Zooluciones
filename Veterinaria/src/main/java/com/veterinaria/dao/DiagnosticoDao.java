

package com.veterinaria.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DiagnosticoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarDiagnostico(int idDiagnostico, String observacion, int idEstado, 
                                    String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_diagnostico(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idDiagnostico, observacion, idEstado, 
                            creadoPor, modificadoPor, accion);
    }
}
