package com.iei.almacenDatos.Repositories;

import com.iei.almacenDatos.Models.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    Optional<Provincia> findByNombre(String nombre);
}
