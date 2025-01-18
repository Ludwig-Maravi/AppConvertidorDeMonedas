
package com.appConvertidorDeMonedas.Modelos;


public record Moneda(   String base_code,
                        String target_code,
                        Double conversion_rate,
                        Double conversion_result
                    ) {
    
    
/*"result": "success",
    "documentation": "https://www.exchangerate-api.com/docs",
    "terms_of_use": "https://www.exchangerate-api.com/terms",
    "time_last_update_unix": 1736467201,
    "time_last_update_utc": "Fri, 10 Jan 2025 00:00:01 +0000",
    "time_next_update_unix": 1736553601,
    "time_next_update_utc": "Sat, 11 Jan 2025 00:00:01 +0000",
    "base_code": "EUR",
    "target_code": "GBP",
    "conversion_rate": 0.8371,
    "conversion_result": 1.6742*/
    
    
}
