package com.iei.apiCarga.Services;

import com.iei.apiCarga.Clients.CastillaYLeonWrapperClient;
import com.iei.apiCarga.Clients.EuskadiWrapperClient;
import com.iei.apiCarga.Clients.ValenciaWrapperClient;
import com.iei.apiCarga.Models.MonumentosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargaService {

    @Autowired
    private CastillaYLeonWrapperClient castillaYLeonWrapperClient;

    @Autowired
    private EuskadiWrapperClient euskadiWrapperClient;

    @Autowired
    private ValenciaWrapperClient valenciaWrapperClient;

    public String cargarDatos(boolean todos, boolean cv, boolean eus, boolean cyl)
    {
        String informe = "";
        if(todos)
        {
            MonumentosDTO valencia = valenciaWrapperClient.cargarDatos().getBody();
            informe += valencia.getInforme();
            MonumentosDTO euskadi = euskadiWrapperClient.cargarDatos().getBody();
            informe += euskadi.getInforme();
            MonumentosDTO castillaYLeon = castillaYLeonWrapperClient.cargarDatos().getBody();
            informe += castillaYLeon.getInforme();

        }
        else
        {
            if(cv)
            {
                MonumentosDTO valencia = valenciaWrapperClient.cargarDatos().getBody();
                informe = valencia.getInforme();
            }
            if(eus)
            {
                MonumentosDTO euskadi = euskadiWrapperClient.cargarDatos().getBody();
                informe = euskadi.getInforme();
            }
            if(cyl)
            {
                MonumentosDTO castillaYLeon = castillaYLeonWrapperClient.cargarDatos().getBody();
                informe = castillaYLeon.getInforme();
            }
        }
        return informe;
    }
}
