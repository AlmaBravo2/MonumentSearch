package com.iei.apiCarga.Clients;

import com.iei.apiCarga.Models.MonumentosDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "euskadiWrapperClient", url = "http://localhost:2202")
public interface EuskadiWrapperClient {

    @GetMapping("/carga")
    public ResponseEntity<MonumentosDTO> cargarDatos();
}
