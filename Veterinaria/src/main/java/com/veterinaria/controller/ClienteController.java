
package com.veterinaria.controller;


import com.veterinaria.dao.DireccionDao;
import com.veterinaria.dao.ClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.veterinaria.domain.Cliente;
import com.veterinaria.domain.Direccion;
import com.veterinaria.domain.Mascota;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteDao clienteDao;
    
    @Autowired
    private DireccionDao direccionDao;

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<Cliente> clientes = clienteDao.listarClientes();
        model.addAttribute("clientes", clientes);
        
        return "cliente/listadoCliente";  // Muestra la lista de los datos
    }
    
    @GetMapping("/insertar")
    public String mostrarFormulario(Model model) {
        List<Direccion> direcciones = direccionDao.listarDirecciones();
        model.addAttribute("direcciones", direcciones);
        
        return "cliente/insertar"; // mostrar el formulario para insertar
    }

    
    
 
    @PostMapping("/insertar")
    public String insertarCliente(
                                  @RequestParam String nombreCliente, 
                                  @RequestParam String telefono, 
                                  @RequestParam String email, 
                                  @RequestParam Integer idDireccion, Model model) {
        try {
            clienteDao.insertarCliente(nombreCliente, telefono, email, idDireccion);
            model.addAttribute("mensaje", "Cliente insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar cliente: " + e.getMessage());
        }
        return "cliente/insertar";  
    }
    
    @PostMapping("/eliminar")
    public String eliminarCliente(@RequestParam("idCliente") int idCliente, Model model) {
        clienteDao.eliminarCliente(idCliente);
        model.addAttribute("mensaje", "Cliente eliminada con éxito");
        return "redirect:/cliente/listado";
    }
    
    
    @GetMapping("/actualizar")
    public String mostrarActualizar(@RequestParam("idCliente") int idCliente, Model model) {
        
        Cliente cliente = clienteDao.buscarPorId(idCliente);
        model.addAttribute("cliente", cliente);
        
        model.addAttribute("direcciones",direccionDao.listarDirecciones());
        model.addAttribute("estados", List.of(1,2));
        return "cliente/actualizar"; // mostrar el formulario para actualizar
    }
    
    @PostMapping("/actualizar")
    public String actualizarCliente(@ModelAttribute("cliente") Cliente cliente,
                                    Model model) {
        try {
            
        clienteDao.actualizarCliente(cliente.getIdCliente(),
                                     cliente.getNombreCliente(),
                                     cliente.getTelefono(),
                                     cliente.getEmail(),
                                     cliente.getIdDireccion(),
                                     cliente.getIdEstado()
                                     );
       
        model.addAttribute("mensaje", "Cliente actualizada con éxito");
        model.addAttribute("direcciones", direccionDao.listarDirecciones());
        model.addAttribute("estados", List.of(1,2));
        
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar cliente: " + e.getMessage());
        }
        
        Cliente actualizada = clienteDao.buscarPorId(cliente.getIdCliente());
        model.addAttribute("cliente", actualizada);
        model.addAttribute("direcciones", direccionDao.listarDirecciones());
        model.addAttribute("estados", List.of(1,2));
        
        return "cliente/actualizar";
    
      
    }
    
}
