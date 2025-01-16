package com.wrapper.Euskadi.Service;

import com.wrapper.Euskadi.ConversorEuskadi;
import com.wrapper.Euskadi.Models.Monumento;
import com.wrapper.Euskadi.Models.MonumentoConvertidoEuskadi;
import com.wrapper.Euskadi.Models.MonumentosDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EuskadiService {

    public MonumentosDTO getMonumentos(){
        List<Monumento> monumentos = new ArrayList<>();
        List<Object> monumentosEInforme = ConversorEuskadi.convertirEuskadi();
        List<MonumentoConvertidoEuskadi> monumentoConvertidoEuskadi = (List<MonumentoConvertidoEuskadi>) monumentosEInforme.get(0);
        String informe = (String) monumentosEInforme.get(1);
        for(MonumentoConvertidoEuskadi monumento : monumentoConvertidoEuskadi){
            Monumento monumentoNuevo = convertirAMonumento(monumento);
            monumentos.add(monumentoNuevo);
        }
        MonumentosDTO res = new MonumentosDTO();
        res.setMonumentos(monumentos);
        res.setInforme(informe);
        return res;
    }

    private static Monumento convertirAMonumento(MonumentoConvertidoEuskadi monumento){
        Monumento res = new Monumento();
        res.setNombre(monumento.getNombre());
        res.setTipo(monumento.getTipo());
        res.setDireccion(monumento.getDireccion());
        res.setCodigoPostal(monumento.getCodigo_postal());
        res.setLatitud(monumento.getLatitud());
        res.setLongitud(monumento.getLongitud());
        res.setDescripcion(monumento.getDescripcion());
        res.setLocalidad(monumento.getLocalidad());
        return res;

    }

    public static void main(String[] args) {
        EuskadiService euskadiService = new EuskadiService();
        euskadiService.getMonumentos();
    }
}
