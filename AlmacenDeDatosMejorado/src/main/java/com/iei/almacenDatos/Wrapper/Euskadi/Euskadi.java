package com.iei.almacenDatos.Wrapper.Euskadi;


import com.iei.almacenDatos.Wrapper.ComunitatValenciana.Logic.Convertidor;
import com.iei.almacenDatos.Wrapper.Euskadi.Logic.ConversorEuskadi;

public class Euskadi {

    public static String main(String filePath){
        return ConversorEuskadi.convertirEuskadiToJson(filePath);
    }

}
