package com.iei.almacenDatos.Repositories;

import com.iei.almacenDatos.Models.Localidad;
import com.iei.almacenDatos.Models.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {
    Optional<Localidad> findByNombre(String nombre);
}
