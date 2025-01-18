
package com.appConvertidorDeMonedas.Modelos;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConsultaMoneda {
    public Moneda buscarMoneda(String base_code, String target_code, Double quantily ){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/7311fe35491fc7710c221f6c/pair/"+base_code+"/"+target_code+"/"+quantily);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class );
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
