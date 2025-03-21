

package com.veterinaria.dao;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CitaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarCita(int idCita, Date fecha, String hora, Double montoTotal, 
                             int idMotivo, int idCliente, int idVeterinario, 
                             int idTratamiento, int idDiagnostico, int idEstado, 
                             String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_cita(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idCita, fecha, hora, montoTotal, idMotivo, idCliente, 
                            idVeterinario, idTratamiento, idDiagnostico, idEstado, 
                            creadoPor, modificadoPor, accion);
    }
}