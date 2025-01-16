package com.iei.apiBusqueda;
/**
 * En esta clase definimos el método main que servirá de punto de entrada a la aplicación.
 * @author M12.01
 * @version 1.0
 * */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class ApiBusquedaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBusquedaApplication.class, args);
	}

}
