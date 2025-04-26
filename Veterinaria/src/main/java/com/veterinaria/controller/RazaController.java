
package com.veterinaria.controller;

import com.veterinaria.dao.RazaDao;
import com.veterinaria.domain.Mascota;
import com.veterinaria.domain.Raza;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/raza")
public class RazaController {

    @Autowired
    private RazaDao razaDao;

    @GetMapping("/listado")
    public String mostrarFormulario() {
        return "mascota/listadoRaza";  // Muestra el formulario
    }

    @PostMapping("/listado")
    public String insertarRaza(@RequestParam String descripcionRaza, Model model) {
        try {
            razaDao.insertarRaza(descripcionRaza);
            model.addAttribute("mensaje", "Raza insertada con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar raza: " + e.getMessage());
        }
        return "mascota/listadoRaza";  // Vuelve a mostrar el formulario con el mensaje
    }
    
    
    @GetMapping("/insertar")
    public String mostrarFormulario(Model model) {
       
        return "mascota/insertarRaza"; // mostrar el formulario para insertar
    }
    
    
 
      @PostMapping("/eliminar")
    public String eliminarRaza(@RequestParam("idRaza") int idRaza, Model model) {
        razaDao.eliminarRaza(idRaza);
        model.addAttribute("mensaje", "Raza eliminada con éxito");
        return "redirect:/raza/listado";
    }
    
    
        @GetMapping("/actualizar")
    public String mostrarActualizar(@RequestParam("idRaza") int idRaza, Model model) {
        
        Raza raza = razaDao.buscarPorId(idRaza);
        model.addAttribute("razas",   razaDao.listarRazas());
        model.addAttribute("estados", List.of(1,2));
        return "mascota/actualizarRaza"; // mostrar el formulario para actualizar
    }
    
    @PostMapping("/actualizar")
    public String actualizarRaza(@ModelAttribute("raza") Raza raza,
                                    Model model) {
        try {
            
        razaDao.actualizarRaza(raza.getIdRaza(),
                                     raza.getDescripcionRaza(),
                                     raza.getIdEstado(),
                                     raza.getEstado()
                                     );
       
        model.addAttribute("mensaje", "Mascota actualizada con éxito");
        model.addAttribute("razas",   razaDao.listarRazas());
        model.addAttribute("estados", List.of(1,2));
        
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar raza: " + e.getMessage());
        }
        
        Raza actualizada = razaDao.buscarPorId(raza.getIdRaza());
        
        model.addAttribute("raza", actualizada);

   
        model.addAttribute("razas",  razaDao.listarRazas());
        
        model.addAttribute("estados", List.of(1,2));

        
        return "mascota/actualizar";
    
      
    }
    
    
    
    
    
}