
package com.veterinaria.dao;



import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class HistorialMedicoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarHistorialMedico(int idHistorial, Date fechaConsulta, int idCliente, 
                                        int idVeterinario, int idDiagnostico, int idTratamiento, 
                                        int idEstado, String creadoPor, String modificadoPor, 
                                        String accion) {
        String sql = "{call insertar_historial_medico(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idHistorial, fechaConsulta, idCliente, idVeterinario, 
                            idDiagnostico, idTratamiento, idEstado, creadoPor, 
                            modificadoPor, accion);
    }
}