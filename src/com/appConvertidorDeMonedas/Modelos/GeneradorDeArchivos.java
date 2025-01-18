
package com.appConvertidorDeMonedas.Modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class GeneradorDeArchivos {
    private List<RegistroConversiones> listaMoneda = new ArrayList<>();
    
    public GeneradorDeArchivos() {
        listaMoneda = new ArrayList<>();
    }
    
    public void guardaeJson(Moneda moneda, double quantity, String  timestamp) throws IOException{

       RegistroConversiones registroMoneda = new RegistroConversiones(moneda.base_code(),moneda.target_code(),moneda.conversion_rate(),moneda.conversion_result(),quantity,timestamp);
       
       listaMoneda.add(registroMoneda);
       Gson gson = new GsonBuilder().setPrettyPrinting().create();
       FileWriter escritura = new FileWriter("LOGConversorMOneda.json");
       escritura.write(gson.toJson(listaMoneda));
       escritura.close();
   }
    
    
    public void leerJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("LOGConversorMoneda.json")) {
            
            RegistroConversiones[] registrosArray = gson.fromJson(reader, RegistroConversiones[].class);
            
            if (registrosArray != null) {
                System.out.println("Registros leidos desde el archivo JSON:");
                for (RegistroConversiones registro : registrosArray) {
                    System.out.println("");
                    System.out.printf("[%s] se convirtio de %.2f - [%s] equivalente a %.2f - [%s%n]",
                            registro.timestamp(), 
                            registro.quantity(),   
                            registro.base_code(),  
                            registro.conversion_result(), 
                            registro.target_code());
                }
            } else {
                System.out.println("No se encontraron registros en el archivo JSON.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}
