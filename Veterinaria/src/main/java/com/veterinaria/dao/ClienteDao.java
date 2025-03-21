

package com.veterinaria.dao;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ClienteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarCliente(int idCliente, String nombreCliente, String telefono, String email, 
                                Date fechaRegistro, int idMascota, int idHistorial, 
                                int idFacturas, int idDireccion, int idEstado, 
                                String creadoPor, String modificadoPor, String accion) {
        String sql = "{call insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idCliente, nombreCliente, telefono, email, fechaRegistro, 
                            idMascota, idHistorial, idFacturas, idDireccion, idEstado, 
                            creadoPor, modificadoPor, accion);
    }
}

