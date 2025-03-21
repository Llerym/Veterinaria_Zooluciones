

package com.veterinaria.controller;

import com.veterinaria.dao.ColorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ColorController {

    @Autowired
    private ColorDao colorDao;

    @GetMapping("/color")
    public String mostrarFormulario() {
        return "index";  // Muestra el formulario
    }

    @PostMapping("/color/insertar")
    public String insertarColor(@RequestParam int idColor, @RequestParam String descripcion, Model model) {
        try {
            colorDao.insertarColor(idColor, descripcion, 1, "admin", "admin", "insert");
            model.addAttribute("mensaje", "Color insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar color: " + e.getMessage());
        }
        return "index";  // Vuelve a mostrar el formulario con el mensaje
    }
}