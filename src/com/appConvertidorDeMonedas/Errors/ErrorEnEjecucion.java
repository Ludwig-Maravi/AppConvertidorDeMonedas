
package com.appConvertidorDeMonedas.Errors;


public class ErrorEnEjecucion extends RuntimeException {
    
    private String mensaje;
    public ErrorEnEjecucion (String mensaje){
        this.mensaje = mensaje;
    }
    
    @Override
    public String getMessage() {
        return this.mensaje;
    }
    
}
