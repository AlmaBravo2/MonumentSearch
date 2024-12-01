package com.iei.almacenDatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AlmacenDatosApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AlmacenDatosApplication.class, args);

		CargarDatos cargarDatos = context.getBean(CargarDatos.class);

		cargarDatos.cargarDatos();

	}

}
