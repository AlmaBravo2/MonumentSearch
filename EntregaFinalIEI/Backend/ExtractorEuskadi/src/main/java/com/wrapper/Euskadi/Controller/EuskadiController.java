package com.wrapper.Euskadi.Controller;

import com.wrapper.Euskadi.Service.EuskadiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EuskadiController {

    @Autowired
    private EuskadiService euskadiService;

    @GetMapping("/carga")
    public ResponseEntity<?> carga(){
        return new ResponseEntity<>(euskadiService.getMonumentos(), HttpStatus.OK);
    }

}
