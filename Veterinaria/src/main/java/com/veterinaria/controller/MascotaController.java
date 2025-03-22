
package com.veterinaria.controller;


import com.veterinaria.dao.MascotaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;


@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    private MascotaDao mascotaDao;

    @GetMapping("/listado")
    public String mostrarFormulario() {
        return "mascota/listadoMascota";  // Muestra el formulario
    }

    @PostMapping("/listado")
    public String insertarMascota(@RequestParam int idMascota, @RequestParam String nombreMascota, @RequestParam int edad, @RequestParam double peso, 
                                   @RequestParam Date fechaNacimiento, @RequestParam int idRaza, @RequestParam int idColor, Model model) {
        try {
            mascotaDao.insertarMascota(idMascota, nombreMascota, edad, peso, fechaNacimiento, idRaza, idColor, 1, "admin", "admin", "insert");
            model.addAttribute("mensaje", "Mascota insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar mascota: " + e.getMessage());
        }
        return "mascota/listadoMascota";  // Vuelve a mostrar el formulario con el mensaje
    }
}
