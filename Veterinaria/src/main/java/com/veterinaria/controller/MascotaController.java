
package com.veterinaria.controller;


import com.veterinaria.dao.ClienteDao;
import com.veterinaria.dao.ColorDao;
import com.veterinaria.dao.MascotaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.veterinaria.dao.RazaDao;
import com.veterinaria.domain.Cliente;
import com.veterinaria.domain.Color;
import com.veterinaria.domain.Mascota;
import com.veterinaria.domain.Raza;
import java.util.List;


@Controller
@RequestMapping("/mascota")
public class MascotaController {

    @Autowired
    private MascotaDao mascotaDao;
    
    @Autowired
    private RazaDao razaDao;
    
    @Autowired
    private ColorDao colorDao;
    
    @Autowired
    private ClienteDao clienteDao;
    

    @GetMapping("/listado")
    public String mostrarListado(Model model) {
        List<Mascota> mascotas = mascotaDao.listarMascotas();
        model.addAttribute("mascotas", mascotas);
        
        return "mascota/listadoMascota";  // Muestra la lista de los datos
    }
    
    @GetMapping("/insertar")
    public String mostrarFormulario(Model model) {
        List<Raza> razas = razaDao.listarRazas();
        model.addAttribute("razas", razas);
        
        List<Color> colores = colorDao.listarColores();
        model.addAttribute("colores", colores);
        
        List<Cliente> clientes = clienteDao.listarClientes();
        model.addAttribute("clientes", clientes);
        
        return "mascota/insertar"; // mostrar el formulario para insertar
    }

    @PostMapping("/insertar")
    public String insertarMascota(
                                  @RequestParam String nombreMascota, 
                                  @RequestParam int edad, 
                                  @RequestParam double peso, 
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento, 
                                  @RequestParam int idRaza, @RequestParam int idColor,@RequestParam int idCliente,  Model model) {
        try {
            mascotaDao.insertarMascota( nombreMascota, edad, peso, fechaNacimiento, idRaza, idColor, idCliente);
            model.addAttribute("mensaje", "Mascota insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar mascota: " + e.getMessage());
        }
        return "mascota/insertar";  
    }
}
