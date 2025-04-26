
package com.veterinaria.controller;


import com.veterinaria.dao.ClienteDao;
import com.veterinaria.dao.ColorDao;
import com.veterinaria.dao.ProductoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.veterinaria.dao.RazaDao;
import com.veterinaria.domain.Cliente;
import com.veterinaria.domain.Color;
import com.veterinaria.domain.Producto;
import com.veterinaria.domain.Raza;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoDao productoDao;
    
 

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        var productos = productoDao.listarProductosView();
        model.addAttribute("productos", productos);
        
        int total = productoDao.contarProductos();
        model.addAttribute("total", total);
        
        return "producto/listadoProducto";  // Muestra la lista de los datos
    }
    
    
    
    
    
    
    @GetMapping("/insertar")
    public String mostrarFormulario(Model model) {
        
        return "producto/insertar"; // mostrar el formulario para insertar
    }

    @PostMapping("/insertar")
    public String insertarProducto(
                                  @RequestParam String nombreProducto, 
                                  @RequestParam int precioVenta, 
                                  @RequestParam int precioCompra, 
                                   @RequestParam int cantidad,Model model) {
        try {
            productoDao.insertarProducto(nombreProducto, precioVenta, precioCompra, cantidad);
            model.addAttribute("mensaje", "Producto insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar producto: " + e.getMessage());
        }
        return "producto/insertar";  
    }
    
    
    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam("idProducto") int idProducto, Model model) {
        productoDao.eliminarProducto(idProducto);
        model.addAttribute("mensaje", "Producto eliminada con éxito");
        return "redirect:/producto/listado";
    }
    
    
    @GetMapping("/actualizar")
    public String mostrarActualizar(@RequestParam("idProducto") int idProducto, Model model) {
        
        Producto producto = productoDao.buscarPorId(idProducto);
        model.addAttribute("producto", producto);
        model.addAttribute("estados", List.of(1,2));
        return "producto/actualizar"; // mostrar el formulario para actualizar
    }
    
    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute("producto") Producto producto,
                                    Model model) {
        try {
            
        productoDao.actualizarProducto(producto.getIdProducto(),
                                     producto.getNombreProducto(),
                                     producto.getPrecioVenta(),
                                     producto.getPrecioCompra(),
                                     producto.getCantidad(),
                                     producto.getIdEstado()
                                     );
       
        model.addAttribute("mensaje", "Producto actualizada con éxito");
        model.addAttribute("estados", List.of(1,2));
        
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar producto: " + e.getMessage());
        }
        
        Producto actualizada = productoDao.buscarPorId(producto.getIdProducto());
        model.addAttribute("producto", actualizada);
        model.addAttribute("estados", List.of(1,2));
        
        return "producto/actualizar";
    
      
    }
    
}
