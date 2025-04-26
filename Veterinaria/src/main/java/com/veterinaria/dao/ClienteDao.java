

package com.veterinaria.dao;
import com.veterinaria.domain.Cliente;
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
public class ClienteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarCliente(String nombreCliente, String telefono, String email, 
                                int idMascota, 
                                int idDireccion) {
        String sql = "{call insertar_cliente(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, nombreCliente, telefono, email, 
                            idMascota, idDireccion 
                            );
    }
    
    private SimpleJdbcCall listarClientesCall;
 
    @PostConstruct
    public void init() {
        this.listarClientesCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")   
            .withProcedureName("listar_clientes_cr")
            .declareParameters(
                new SqlOutParameter("rc_clientes", OracleTypes.CURSOR,
                    new BeanPropertyRowMapper<>(Cliente.class))
            );
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> listarClientes() {
        Map<String, Object> out = listarClientesCall.execute();
        return (List<Cliente>) out.get("rc_clientes");
    }
}

