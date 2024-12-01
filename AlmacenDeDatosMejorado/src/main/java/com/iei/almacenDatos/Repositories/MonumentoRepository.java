package com.iei.almacenDatos.Repositories;

import com.iei.almacenDatos.Models.Monumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonumentoRepository extends JpaRepository<Monumento, Integer> {
}
