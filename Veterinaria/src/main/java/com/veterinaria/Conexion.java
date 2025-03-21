
package com.veterinaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Conexion {
@Autowired//inyectar-colocar dependencias del proyecto
    private JdbcTemplate jdbcTemplate;

    public void testConnection() {
        try {
            String result = jdbcTemplate.queryForObject("SELECT 'Conexion Exitosa' FROM dual", String.class);
            System.out.println(" Resultado: " + result);
        } catch (Exception e) {
            System.err.println(" Error en la conexión: " + e.getMessage());
        }
    }

}
