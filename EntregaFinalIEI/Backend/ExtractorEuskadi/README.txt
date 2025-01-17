El proceso de funcionamiento de este extractor es el siguiente:

1. Se recibe una petición de extracción de datos en el Controller.
2. Se llama al Wrapper Euskadi que obtiene los datos en el formato establecido deseado pero manteniendo el esquema original. [Dicho Wrapper se encuentra en la carpeta src/main/java/com/wrapper/Euskadi/Wrapper]
3. La clase Converter se encarga de transformar los datos obtenidos en un formato JSON que se pueda almacenar en la base de datos.