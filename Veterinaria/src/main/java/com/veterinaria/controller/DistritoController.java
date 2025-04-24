
package com.veterinaria.controller;

import com.veterinaria.dao.DistritoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/distrito")
public class DistritoController {

    @Autowired
    private DistritoDao distritoDao;

    @GetMapping("/listado")
    public String mostrarFormulario() {
        return "direccion/listadoDistrito";  // Muestra el formulario
    }

    @PostMapping("/listado")
    public String insertarColor(@RequestParam String nombreDistrito,@RequestParam int idCanton, @RequestParam int idProvincia, Model model) {
        try {
            distritoDao.insertarDistrito(nombreDistrito, idCanton, idProvincia);
            model.addAttribute("mensaje", "Distrito insertada con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar Distrito: " + e.getMessage());
        }
        return "direccion/listadoDistrito";  // Vuelve a mostrar el formulario con el mensaje
    }
}