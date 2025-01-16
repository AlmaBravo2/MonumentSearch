package com.iei.apiCarga.Repositories;

import com.iei.apiCarga.Models.Monumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentoRepository extends JpaRepository<Monumento, Long> {
    boolean existsByNombre(String nombre);
}
