

package com.veterinaria.controller;

import com.veterinaria.dao.ColorDao;
import com.veterinaria.domain.Cliente;
import com.veterinaria.domain.Color;
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
@RequestMapping("/color")
public class ColorController {

    @Autowired
    private ColorDao colorDao;

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<Color> colores = colorDao.listarColores();
        model.addAttribute("colores", colores);
        
        return "mascota/listadoColor";  // Muestra la lista de los datos
    }
    
    @GetMapping("/insertar")
    public String mostrarFormulario(Model model) {
       
        return "mascota/insertarColor"; // mostrar el formulario para insertar
    }
    
    @PostMapping("/insertar")
    public String insertarColor(
                                  @RequestParam String descripcionColor, 
                                  Model model) {
        try {
            colorDao.insertarColor(descripcionColor, 1);
            model.addAttribute("mensaje", "Color insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar color: " + e.getMessage());
        }
        return "mascota/insertarColor";  
    }

    @PostMapping("/eliminar")
    public String eliminarColor(@RequestParam("idColor") int idColor, Model model) {
        colorDao.eliminarColor(idColor);
        model.addAttribute("mensaje", "Color eliminada con éxito");
        return "redirect:/color/listado";
    }
    
    
    
    
    
    
    
    @GetMapping("/actualizar")
    public String mostrarActualizar(@RequestParam("idColor") int idColor, Model model) {
        
        Color color = colorDao.buscarPorId(idColor);
        model.addAttribute("color", color);
        
        model.addAttribute("estados", List.of(1,2));
        return "mascota/actualizarColor"; // mostrar el formulario para actualizar
    }
    
    @PostMapping("/actualizar")
    public String actualizarColor(@ModelAttribute("color") Color color,
                                    Model model) {
        try {
            
        colorDao.actualizarColor(color.getIdColor(),
                                     color.getDescripcionColor(),
                                     color.getIdEstado()
                                     );
       
        model.addAttribute("mensaje", "Color actualizada con éxito");
        
        model.addAttribute("estados", List.of(1,2));
        
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar color: " + e.getMessage());
        }
        
        Color actualizada = colorDao.buscarPorId(color.getIdColor());
        model.addAttribute("color", actualizada);
        
        model.addAttribute("estados", List.of(1,2));
        
        return "mascota/actualizarColor";
    
      
    }
}