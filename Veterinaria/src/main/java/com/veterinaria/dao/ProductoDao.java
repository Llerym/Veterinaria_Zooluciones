
package com.veterinaria.dao;


import com.veterinaria.domain.Mascota;
import com.veterinaria.domain.Producto;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collections;
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
public class ProductoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
        private SimpleJdbcCall contarProductosCall;

    public void insertarProducto( String nombreProducto, Integer precioVenta,Integer precioCompra, Integer cantidad ) {
        String sql = "{call pkg_fide_veterinaria.insertar_producto_sp(?, ?, ?, ?)}";
        jdbcTemplate.update(sql,  nombreProducto, precioVenta, 
                            precioCompra, cantidad);
    }
    
    public void eliminarProducto(Integer idProducto) {
        String sql = "{call pkg_fide_veterinaria.eliminar_producto_sp(?)}";
        jdbcTemplate.update(sql,  idProducto);
    }
    
    
    public void actualizarProducto(Integer idProducto,String nombreProducto, Integer precioVenta, Integer precioCompra, Integer cantidad, 
                                  Integer idEstado) {
        String sql = "{call pkg_fide_veterinaria.actualizar_producto_sp(?,?,?,?,?,?)}";
        jdbcTemplate.update(sql, idProducto, nombreProducto, precioVenta, precioCompra, cantidad, idEstado);
    }
    
        public Producto buscarPorId(Integer idProducto) {
        String sql =
                "SELECT m.ID_Producto       AS idProducto, " +
            "       m.NOMBRE_PRODUCTO  AS nombreProducto, " +
            "       m.PRECIO_VENTA      AS precioVenta, " +
            "       m.PRECIO_COMPRA     AS precioCompra, " +
            "       m.CANTIDAD          AS cantidad, " +   
            "       m.ID_ESTADO         AS idEstado, " +
            "       e.DESCRIPCION_ESTADO AS estado " +
            "  FROM FIDE_PRODUCTO_TB m " +
            "  JOIN FIDE_ESTADO_TB e  ON m.ID_ESTADO  = e.ID_ESTADO " +
            " WHERE m.ID_PRODUCTO = ?";
        return jdbcTemplate.queryForObject(
            sql,
            new BeanPropertyRowMapper<>(Producto.class),
            idProducto
        );
    }
    
//        @PostConstruct
//    public void init() {
//        this.listarProductosCall = new SimpleJdbcCall(jdbcTemplate)
//            .withCatalogName("pkg_fide_veterinaria")     
//            .withProcedureName("listar_productos_cr")
//            .declareParameters(
//                new SqlOutParameter("rc_productos", OracleTypes.CURSOR,
//                    new BeanPropertyRowMapper<>(Producto.class))
//            );
//    }
//    
//     @SuppressWarnings("unchecked")
//    public List<Producto> listarProductos() {
//        Map<String, Object> out = listarProductosCall.execute();
//        return (List<Producto>) out.get("rc_productos");
//    }
    
        // vista en oracle
    public List<Producto> listarProductosView() {
        String sql = "SELECT * FROM FIDE_PRODUCTO_VIEW";
        return jdbcTemplate.query(
            sql,
            new BeanPropertyRowMapper<>(Producto.class)
        );
    }
    
    // funcion total de productos
    @PostConstruct
    public void init() {
        this.contarProductosCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName("pkg_fide_veterinaria")    
            .withFunctionName("contar_productos_fn");
    }
 
    public int contarProductos() {
        BigDecimal bd = contarProductosCall.executeFunction(BigDecimal.class, Collections.emptyMap());

            return (bd != null) ? bd.intValue() : 0;

    }
}

