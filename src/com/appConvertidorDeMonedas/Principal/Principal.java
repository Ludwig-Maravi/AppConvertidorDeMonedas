
package com.appConvertidorDeMonedas.Principal;

import com.appConvertidorDeMonedas.Errors.ErrorEnEjecucion;
import com.appConvertidorDeMonedas.Modelos.ConsultaMoneda;
import com.appConvertidorDeMonedas.Modelos.GeneradorDeArchivos;
import com.appConvertidorDeMonedas.Modelos.Moneda;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Principal {
    public static void main(String[] args) {
        Scanner capturaOption = new Scanner(System.in);
        Scanner capturaContunue = new Scanner(System.in);
        var option = 0;
        String toContinue ="";
        
        GeneradorDeArchivos generador = new GeneradorDeArchivos();

        do {
            
            mostrarMenu();
            try {
                option = Integer.valueOf(capturaOption.nextLine());
                          
                switch (option) {
                    case 1:
                        realizarConversion("ARS", "BOB", generador);
                        break;
                    case 2:
                        realizarConversion("BOB", "COP", generador);
                        break;
                    case 3:
                        realizarConversion("COP", "CLP", generador);
                        break;
                    case 4:
                        realizarConversion("CLP", "COP", generador);
                        break;
                    case 5:
                        realizarConversion("COP", "USD", generador);
                        break;
                    case 6:
                        System.out.println("Has seleccionado la Opción 7. Saliendo...");
                        break;
                    case 7:
                        generador.leerJson();
                        break;
                    case 8:
                        System.out.println("Saliendo del programa...");
                        break; // Opción para salir
                         
                    default:
                        throw new ErrorEnEjecucion("Opción no válida. Por favor, intente de nuevo.");
                }
                
                System.out.println("\n***************************************************\n");
                
                if (option != 8) {
                boolean validInput = false; // Variable para controlar la validez de la entrada
                while (!validInput) {
                    System.out.print("¿Desea continuar? (s/n): ");
                    toContinue = capturaContunue.next();
                    if (toContinue.equalsIgnoreCase("s")) {
                        validInput = true; // Entrada válida, salir del bucle
                    } else if (toContinue.equalsIgnoreCase("n")) {
                        option = 8; // Salir del programa
                        validInput = true; // Entrada válida, salir del bucle
                    } else {
                        System.out.println("Entrada no válida. Por favor, ingrese 's' para continuar o 'n' para salir.");
                    }
                }
            }
            
            } catch (NumberFormatException e) {
                System.out.println("Numero no encontrado " + e.getMessage());
            }  catch (IllegalArgumentException  e ) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }  
        } while (option != 8 ); 

        capturaOption.close();  
    }
    
    private static void mostrarMenu() {
        System.out.println("***************************************************");
        System.out.println("\tBienvenido al Conversor de Monedas");
        System.out.println("***************************************************");
        System.out.println("----- Menu -----");
        System.out.println("1. Opcion 1 <-> Peso argentino ==>> Boliviano boliviano");
        System.out.println("2. Opcion 2 <-> Boliviano boliviano ==>> Real brasilenio");
        System.out.println("3. Opcion 3 <-> Real brasilenio ==>> Peso chileno");
        System.out.println("4. Opcion 4 <-> Peso chileno ==>> Peso colombiano");
        System.out.println("5. Opcion 5 <-> Peso colombiano ==>> Dolar estadounidense");
        System.out.println("6. Opcion 6 <-> Otra opcion de conversion");
        System.out.println("7. Opcion 7 Mostrar historial de conversiones");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opcion: ");
    }
    
    private static void realizarConversion(String monedaOrigen, String monedaDestino, GeneradorDeArchivos generador) {
        System.out.println("----------------------------------------");
        System.out.println("Has seleccionado la conversion de [" + monedaOrigen + "] a [" + monedaDestino + "].");
        Scanner captaquantity = new Scanner(System.in);
        System.out.println("Ingrese ca cntidad a convertir: ");

        
        try {
            
            var cantidad = Double.parseDouble(captaquantity.nextLine());
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaHoraComoString = fechaHoraActual.format(formato);
            
            ConsultaMoneda consulta = new ConsultaMoneda();
            Moneda moneda = consulta.buscarMoneda(monedaOrigen, monedaDestino, cantidad);

            String baseCode = moneda.base_code();
            String targetCode = moneda.target_code();
            Double conversionResult = moneda.conversion_result();
            
            System.out.println("El valor de " + cantidad + " [" + baseCode + "] equivale a "+ conversionResult+" [" + targetCode + "].");

            generador.guardaeJson(moneda, cantidad, fechaHoraComoString);


        } catch (NumberFormatException e) {
            System.out.println("Número incorrecto: " + e.getMessage());
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
