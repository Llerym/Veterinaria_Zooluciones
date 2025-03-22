
package com.veterinaria.controller;

import com.veterinaria.dao.RazaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String insertarColor(@RequestParam int idRaza, @RequestParam String descripcionRaza, Model model) {
        try {
            razaDao.insertarRaza(idRaza, descripcionRaza, 1, "admin", "admin", "insert");
            model.addAttribute("mensaje", "Raza insertada con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar raza: " + e.getMessage());
        }
        return "mascota/listadoRaza";  // Vuelve a mostrar el formulario con el mensaje
    }
}