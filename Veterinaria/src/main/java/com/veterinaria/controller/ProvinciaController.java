
package com.veterinaria.controller;

import com.veterinaria.dao.ProvinciaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/provincia")
public class ProvinciaController {

    @Autowired
    private ProvinciaDao provinciaDao;

    @GetMapping("/listado")
    public String mostrarFormulario() {
        return "direccion/listadoProvincia";  // Muestra el formulario
    }

    @PostMapping("/listado")
    public String insertarColor(@RequestParam String nombreProvincia, Model model) {
        try {
            provinciaDao.insertarProvincia(nombreProvincia);
            model.addAttribute("mensaje", "Provincia insertada con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar provincia: " + e.getMessage());
        }
        return "direccion/listadoProvincia";  // Vuelve a mostrar el formulario con el mensaje
    }
}