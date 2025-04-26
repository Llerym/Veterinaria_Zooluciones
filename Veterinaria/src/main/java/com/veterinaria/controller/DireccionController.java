
package com.veterinaria.controller;


import com.veterinaria.dao.CantonDao;
import com.veterinaria.dao.DireccionDao;
import com.veterinaria.dao.DistritoDao;
import com.veterinaria.dao.ProvinciaDao;
import com.veterinaria.domain.Canton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.veterinaria.domain.Direccion;
import com.veterinaria.domain.Distrito;
import com.veterinaria.domain.Provincia;
import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private DireccionDao direccionDao;
    
    @Autowired
    private ProvinciaDao provinciaDao;
    
    @Autowired
    private CantonDao cantonDao;
    
    @Autowired
    private DistritoDao distritoDao;
    

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<Direccion> direcciones = direccionDao.listarDirecciones();
        model.addAttribute("direcciones", direcciones);
        
        return "direccion/listadoDireccion";  // Muestra la lista de los datos
    }
    
    @GetMapping("/insertar")
    public String mostrarFormulario(Model model) {
        List<Provincia> provincias = provinciaDao.listarProvincias();
        model.addAttribute("provincias", provincias);
        
        List<Canton> cantones = cantonDao.listarCantones();
        model.addAttribute("cantones", cantones);
        
        List<Distrito> distritos = distritoDao.listarDistritos();
        model.addAttribute("distritos", distritos);
        
        return "direccion/insertar"; // mostrar el formulario para insertar
    }

    @PostMapping("/insertar")
    public String insertarDireccion(
                                  @RequestParam String descripcion, 
                                  @RequestParam int idDistrito, 
                                  @RequestParam int idCanton, 
                                  @RequestParam int idProvincia, Model model) {
        try {
            direccionDao.insertarDireccion(descripcion, idDistrito, idCanton, idProvincia);
            model.addAttribute("mensaje", "Direccion insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar direccion: " + e.getMessage());
        }
        return "direccion/insertar";  
    }
    
    
    
    
    @PostMapping("/eliminar")
    public String eliminarDireccion(@RequestParam("idDireccion") int idDireccion, Model model) {
        direccionDao.eliminarDireccion(idDireccion);
        model.addAttribute("mensaje", "Direccion eliminada con éxito");
        return "redirect:/direccion/listado";
    }
    
    
    @GetMapping("/actualizar")
    public String mostrarActualizar(@RequestParam("idDireccion") int idDireccion, Model model) {
        
        Direccion direccion = direccionDao.buscarPorId(idDireccion);
        model.addAttribute("direccion", direccion);
        
        model.addAttribute("provincias", provinciaDao.listarProvincias());
        model.addAttribute("cantones", cantonDao.listarCantones());
        model.addAttribute("distritos", distritoDao.listarDistritos());
        
        model.addAttribute("estados", List.of(1,2));
        return "direccion/actualizar"; // mostrar el formulario para actualizar
    }
    
    @PostMapping("/actualizar")
    public String actualizarDireccion(@ModelAttribute("direccion") Direccion direccion,
                                    Model model) {
        try {
            
        direccionDao.actualizarDireccion(direccion.getIdDireccion(),
                                         direccion.getDescripcion(),
                                         direccion.getIdDistrito(),
                                         direccion.getIdCanton(),
                                         direccion.getIdProvincia(),
                                         direccion.getIdEstado()
                                     );
        
        
       
        model.addAttribute("mensaje", "Direccion actualizada con éxito");
        
        model.addAttribute("provincias", provinciaDao.listarProvincias());
        model.addAttribute("cantones", cantonDao.listarCantones());
        model.addAttribute("distritos", distritoDao.listarDistritos());
        
        model.addAttribute("estados", List.of(1,2));
        
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar direccion: " + e.getMessage());
        }
        
        Direccion actualizada = direccionDao.buscarPorId(direccion.getIdDireccion());
        model.addAttribute("direccion", actualizada);
        
        model.addAttribute("provincias", provinciaDao.listarProvincias());
        model.addAttribute("cantones", cantonDao.listarCantones());
        model.addAttribute("distritos", distritoDao.listarDistritos());
        
        model.addAttribute("estados", List.of(1,2));
        
        return "direccion/actualizar";
    
      
    }
    
}
