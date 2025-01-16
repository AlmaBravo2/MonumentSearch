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

    @Autowired
    private GuardarDatosService guardarDatosService;

    public String cargarDatos(boolean todos, boolean cv, boolean eus, boolean cyl)
    {
        String informe = "";


        if(todos)
        {
            MonumentosDTO valencia = valenciaWrapperClient.cargarDatos().getBody();
            informe += valencia.getInforme();
            informe += guardarDatosService.guardarDatos(valencia.getMonumentos());
            MonumentosDTO euskadi = euskadiWrapperClient.cargarDatos().getBody();
            informe += euskadi.getInforme();
            informe += guardarDatosService.guardarDatos(euskadi.getMonumentos());
            MonumentosDTO castillaYLeon = castillaYLeonWrapperClient.cargarDatos().getBody();
            informe += castillaYLeon.getInforme();
            informe += guardarDatosService.guardarDatos(castillaYLeon.getMonumentos());

        }
        else
        {
            if(cv)
            {
                MonumentosDTO valencia = valenciaWrapperClient.cargarDatos().getBody();
                System.out.println(valencia.getMonumentos());
                informe = valencia.getInforme();
                informe += guardarDatosService.guardarDatos(valencia.getMonumentos());
            }
            if(eus)
            {
                MonumentosDTO euskadi = euskadiWrapperClient.cargarDatos().getBody();
                informe = euskadi.getInforme();
                informe += guardarDatosService.guardarDatos(euskadi.getMonumentos());
            }
            if(cyl)
            {
                MonumentosDTO castillaYLeon = castillaYLeonWrapperClient.cargarDatos().getBody();
                informe = castillaYLeon.getInforme();
                informe += guardarDatosService.guardarDatos(castillaYLeon.getMonumentos());
            }
        }
        return informe;
    }
}
