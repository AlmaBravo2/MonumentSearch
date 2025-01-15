package com.iei.apiCarga.Repositories;

import com.iei.apiCarga.Models.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalidadRepository extends JpaRepository<Localidad, Long>{
    boolean existsByNombre(String nombre);
}
