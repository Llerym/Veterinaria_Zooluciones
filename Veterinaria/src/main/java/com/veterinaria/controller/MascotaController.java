
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
import org.springframework.web.bind.annotation.ModelAttribute;


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
            mascotaDao.insertarMascota(nombreMascota, edad, peso, fechaNacimiento, idRaza, idColor, idCliente);
            model.addAttribute("mensaje", "Mascota insertado con éxito");
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al insertar mascota: " + e.getMessage());
        }
        return "mascota/insertar";  
    }
    
    @PostMapping("/eliminar")
    public String eliminarMascota(@RequestParam("idMascota") int idMascota, Model model) {
        mascotaDao.eliminarMascota(idMascota);
        model.addAttribute("mensaje", "Mascota eliminada con éxito");
        return "mascota/listadoMascota";
    }
    
    
    @GetMapping("/actualizar")
    public String mostrarActualizar(@RequestParam("idMascota") int idMascota, Model model) {
        
        Mascota mascota = mascotaDao.buscarPorId(idMascota);
        model.addAttribute("mascota", mascota);
        model.addAttribute("razas",   razaDao.listarRazas());
        model.addAttribute("colores", colorDao.listarColores());
        model.addAttribute("clientes",clienteDao.listarClientes());
        model.addAttribute("estados", List.of(1,2));
        return "mascota/actualizar"; // mostrar el formulario para actualizar
    }
    
    @PostMapping("/actualizar")
    public String actualizarMascota(@ModelAttribute("mascota") Mascota mascota,
                                    Model model) {
        try {
            
        mascotaDao.actualizarMascota(mascota.getIdMascota(),
                                     mascota.getNombreMascota(),
                                     mascota.getEdad(),
                                     mascota.getPeso(),
                                     mascota.getFechaNacimiento(),
                                     mascota.getIdRaza(),
                                     mascota.getIdColor(),
                                     mascota.getIdCliente(),
                                     mascota.getIdEstado()
                                     );
       
        model.addAttribute("mensaje", "Mascota actualizada con éxito");
        model.addAttribute("razas",   razaDao.listarRazas());
        model.addAttribute("colores", colorDao.listarColores());
        model.addAttribute("clientes",clienteDao.listarClientes());
        model.addAttribute("estados", List.of(1,2));
        
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al actualizar mascota: " + e.getMessage());
        }
        
        Mascota actualizada = mascotaDao.buscarPorId(mascota.getIdMascota());
        model.addAttribute("mascota", actualizada);
        model.addAttribute("razas",  razaDao.listarRazas());
        model.addAttribute("colores",colorDao.listarColores());
        model.addAttribute("clientes",clienteDao.listarClientes());
        model.addAttribute("estados", List.of(1,2));
        
        return "mascota/actualizar";
    
      
    }
    
}
