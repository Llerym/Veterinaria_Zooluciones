

package com.veterinaria.dao;
import com.veterinaria.domain.Cliente;
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
public class ClienteDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private SimpleJdbcCall listarClientesCall;

    public void insertarCliente(String nombreCliente, String telefono, String email, 
                                Integer idDireccion) {
        String sql = "{call pkg_fide_veterinaria.insertar_cliente_sp(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, nombreCliente, telefono, email, idDireccion);
    }
    
    public void eliminarCliente(Integer idCliente) {
        String sql = "{call pkg_fide_veterinaria.eliminar_cliente_sp(?)}";
        jdbcTemplate.update(sql,  idCliente);
    }
    
    public void actualizarCliente(Integer idCliente, String nombreCliente, String telefono, String email, 
                                Integer idDireccion, Integer idEstado) {
        String sql = "{call pkg_fide_veterinaria.actualizar_cliente_sp(?,?,?,?,?,?)}";
        jdbcTemplate.update(sql, idCliente, nombreCliente, telefono, email, idDireccion, idEstado);
    }
    
    public Cliente buscarPorId(Integer idCliente) {
    String sql =
        "SELECT c.ID_CLIENTE       AS idCliente, " +
        "       c.NOMBRE_CLIENTE   AS nombreCliente, " +
        "       c.TELEFONO         AS telefono, " +
        "       c.EMAIL            AS email, " +
        "       c.ID_DIRECCION     AS idDireccion, " +
        "       d.DESCRIPCION      AS descripcion, " +
        "       c.ID_ESTADO        AS idEstado, " +
        "       e.DESCRIPCION_ESTADO AS estado " +
        "  FROM FIDE_CLIENTE_TB c " +
        "  JOIN FIDE_ESTADO_TB e ON c.ID_ESTADO = e.ID_ESTADO " +
        "  JOIN FIDE_DIRECCION_TB d ON c.ID_DIRECCION = d.ID_DIRECCION " +
        " WHERE c.ID_CLIENTE = ?";
    
    return jdbcTemplate.queryForObject(
        sql,
        new BeanPropertyRowMapper<>(Cliente.class),
        idCliente
    );
}

 
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

