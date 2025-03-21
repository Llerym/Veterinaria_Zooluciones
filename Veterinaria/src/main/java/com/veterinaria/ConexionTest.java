
package com.veterinaria; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component 
public class ConexionTest implements CommandLineRunner {
    
    @Autowired 
    private Conexion conexion;

    @Override
    public void run(String... args) {
        if (conexion != null) { 
            conexion.testConnection();
        } else {
            System.err.println("Error: La conexión es NULL");
        }
    }
}