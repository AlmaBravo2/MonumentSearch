package com.iei.apiCarga.Repositories;

import com.iei.apiCarga.Models.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{
    boolean existsByNombre(String nombre);
}
