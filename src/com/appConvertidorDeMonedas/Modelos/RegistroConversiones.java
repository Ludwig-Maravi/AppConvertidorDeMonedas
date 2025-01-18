
package com.appConvertidorDeMonedas.Modelos;

public record RegistroConversiones(  String base_code,
                                     String target_code,
                                     double conversion_rate,
                                     double conversion_result,
                                     double quantity,
                                     String timestamp) {

}
