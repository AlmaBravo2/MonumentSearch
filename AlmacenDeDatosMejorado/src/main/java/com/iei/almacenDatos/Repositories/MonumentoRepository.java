package com.iei.almacenDatos.Repositories;

import com.iei.almacenDatos.Models.Monumento;
import com.iei.almacenDatos.Models.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonumentoRepository extends JpaRepository<Monumento, Integer> {
    Optional<Monumento> findByNombre(String nombre);
}
