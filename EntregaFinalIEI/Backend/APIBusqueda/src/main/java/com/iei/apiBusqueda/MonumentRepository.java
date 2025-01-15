package com.iei.apiBusqueda;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iei.apiBusqueda.Models.*;
public interface MonumentRepository extends JpaRepository<Monumento,Long> {


}
