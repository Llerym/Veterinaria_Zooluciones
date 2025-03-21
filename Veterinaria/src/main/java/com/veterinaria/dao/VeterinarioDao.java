
package com.veterinaria.dao;


import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VeterinarioDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarVeterinario(int idVeterinario, String nombreVeterinario, String telefonoVeterinario,
                                     String emailVeterinario, int idDireccion, int idHistorial, int idEstado,
                                     String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_veterinario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idVeterinario, nombreVeterinario, telefonoVeterinario, 
                            emailVeterinario, idDireccion, idHistorial, idEstado, 
                            creadoPor, modificadoPor, accion);
    }
}
