
package com.veterinaria.controller;


import com.veterinaria.dao.DireccionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.veterinaria.dao.VeterinarioDao;
import com.veterinaria.domain.Direccion;
import com.veterinaria.domain.Veterinario;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {

    @Autowired
    private VeterinarioDao veterinarioDao;
    
    @Autowired
    private DireccionDao direccionDao;
    

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<Veterinario> veterinarios = veterinarioDao.listarVeterinarios();
        model.addAttribute("veterinarios", veterinarios);
        
        return "veterinario/listadoVeterinario";  // Muestra la lista de los datos
    }
    
    @GetMapping("/insertar")
    public String mostrarFormulario(Model model) {
        List<Direccion> direcciones = direccionDao.listarDirecciones();
        model.addAttribute("direcciones", direcciones);
        
        return "veterinario/insertar"; // mostrar el formulario para insertar
    }

    @PostMapping("/insertar")
    public String insertarVeterinario(
                                  @RequestParam String nombreVeterinario, 
                                  @RequestParam String telefonoVeterinario, 
                                  @RequestParam String emailVeterinario, 
                                  @RequestParam Integer idDireccion, Model model) {
        try {
            veterinarioDao.insertarVeterinario(nombreVeterinario, telefonoVeterinario, emailVeterinario, idDireccion);
            model.addAttribute("mensaje", "Veterinario insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar veterinario: " + e.getMessage());
        }
        return "veterinario/insertar";  
    }
    
    @PostMapping("/eliminar")
    public String eliminarVeterinario(@RequestParam("idVeterinario") int idVeterinario, Model model) {
        veterinarioDao.eliminarVeterinario(idVeterinario);
        model.addAttribute("mensaje", "Veterinario eliminada con éxito");
        return "redirect:/veterinario/listado";
    }
    
    
    @GetMapping("/actualizar")
    public String mostrarActualizar(@RequestParam("idVeterinario") int idVeterinario, Model model) {
        
        Veterinario veterinario = veterinarioDao.buscarPorId(idVeterinario);
        model.addAttribute("veterinario", veterinario);
        
        model.addAttribute("direcciones", direccionDao.listarDirecciones());
        model.addAttribute("estados", List.of(1, 2));
        return "veterinario/actualizar"; // mostrar el formulario para actualizar
    }

    @PostMapping("/actualizar")
    public String actualizarVeterinario(@ModelAttribute("veterinario") Veterinario veterinario,
                                    Model model) {
        try {
            
        veterinarioDao.actualizarVeterinario(veterinario.getIdVeterinario(),
                                                veterinario.getNombreVeterinario(),
                                                veterinario.getTelefonoVeterinario(),
                                                veterinario.getEmailVeterinario(),
                                                veterinario.getIdDireccion(),
                                                veterinario.getIdEstado()
                                                );
       
       
        model.addAttribute("mensaje", "Veterinario actualizado con éxito");
        
        model.addAttribute("direcciones", direccionDao.listarDirecciones());

        model.addAttribute("estados", List.of(1,2));
        
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar mascota: " + e.getMessage());
        }
        
        Veterinario actualizada = veterinarioDao.buscarPorId(veterinario.getIdVeterinario());
        model.addAttribute("veterinario", actualizada);
        
        model.addAttribute("direcciones", direccionDao.listarDirecciones());

        model.addAttribute("estados", List.of(1,2));
        
        return "veterinario/actualizar";
    
      
    }
    
}
