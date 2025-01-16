package com.iei.apiCarga.Clients;

import com.iei.apiCarga.Models.MonumentosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "castelloYLeonWrapperClient", url = "http://localhost:2603")
public interface CastillaYLeonWrapperClient {

    @GetMapping("/carga")
    public ResponseEntity<MonumentosDTO> cargarDatos();
}
