package com.iei.apiCarga.Clients;

import com.iei.apiCarga.Models.MonumentosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "valenciaWrapperClient", url = "http://localhost:1502")
public interface ValenciaWrapperClient {

    @GetMapping("/carga")
    public ResponseEntity<MonumentosDTO> cargarDatos();
}
