package com.iei.apiCarga.Services;

public class CargaService {

    public void cargarDatos(boolean todos, boolean cv, boolean eus, boolean cyl)
    {
        if(todos)
        {
            //Cargar todos los datos
        }
        else
        {
            if(cv)
            {
                //Cargar datos de la Comunidad Valenciana
            }
            if(eus)
            {
                //Cargar datos de Euskadi
            }
            if(cyl)
            {
                //Cargar datos de Castilla y León
            }
        }
    }
}
