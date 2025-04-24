
package com.veterinaria.controller;

import com.veterinaria.dao.CantonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/canton")
public class CantonController {

    @Autowired
    private CantonDao cantonDao;

    @GetMapping("/listado")
    public String mostrarFormulario() {
        return "direccion/listadoCanton";  // Muestra el formulario
    }

    @PostMapping("/listado")
    public String insertarColor(@RequestParam String nombreCanton,@RequestParam int idProvincia,  Model model) {
        try {
            cantonDao.insertarCanton(nombreCanton, idProvincia);
            model.addAttribute("mensaje", "Canton insertada con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar Canton: " + e.getMessage());
        }
        return "direccion/listadoCanton";  // Vuelve a mostrar el formulario con el mensaje
    }
}