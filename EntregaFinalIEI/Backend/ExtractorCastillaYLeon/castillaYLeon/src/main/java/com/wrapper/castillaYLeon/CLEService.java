package com.wrapper.castillaYLeon;

import com.wrapper.castillaYLeon.Logic.ConvertidorCLE;
import com.wrapper.castillaYLeon.Models.MonumentoCLE;
import com.wrapper.castillaYLeon.Models.MonumentosDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CLEService {
    public MonumentosDTO cargarDatosCLE(){
        ConvertidorCLE convertidorCLE = new ConvertidorCLE();
        String filePath =  "monumentosFinal.xml";
        return convertidorCLE.getMonumentosCLE(filePath);
    }
}
