

//Creamos una variable documentos para guardar los monumentos buscados
    var monumentos = []

    var mapa = L.map("contenedor-del-mapa").setView([40.41692954150457, -3.667879444630583], 6)
    L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png?", {}).addTo(mapa)



//Cogemos los valores por los que queremos filtrar
    var localidadElement = document.getElementById("localidad")
    var codigo_postalElement = document.getElementById("codigo_postal")
    var provinciaElement = document.getElementById("provincia")
    var tipoElement = document.getElementById("tipo")

//Identificamos el botón de búsqueda y la cancelar
    const buttonSearch = document.getElementById("search")
    const buttonCancel = document.getElementById("cancel")


//Realizamos la búsqueda a la API de búsqueda con los filtros
    async function buscarMonumentos() {

        //Cogemos los valoresque vamos a filtrar
        const localidad = localidadElement.value
        const codigoPostal = codigo_postalElement.value
        const provincia = provinciaElement.value
        const tipo = tipoElement.value

        //Añadimos a los paramtros los campos que estén llenos
        // Creamos un objeto URLSearchParams para construir los parámetros
        const params = new URLSearchParams();

        if (localidad) {
            params.append("localidad", localidad.toUpperCase());
        }

        if (codigoPostal) {
            params.append("codigoPostal", codigoPostal);
        }

        if (provincia) {
            params.append("provincia", provincia.toUpperCase());
        }

        if (tipo && tipo !== "Todos") {
            params.append("tipo", tipo);
        }

        // Construimos la URL con los parámetros
        const url = `http://localhost:2930/monumentos/?${params.toString()}`;
        console.log("URL construida:", url);
        monumentos=[]
        //Realizamos la petición a la API
        await axios.get(url)
            .then(response => {
                console.log(response.data);
                monumentos = response.data.map(item => {
                    const m = item.monumento; // Acceder al objeto "monumento"


                    return new Monumento(
                        m.id,
                        m.nombre,
                        m.direccion,
                        m.codigoPostal, // Asegúrate de que el nombre del campo coincida
                        m.longitud,
                        m.latitud,
                        m.descripcion,
                        m.tipo,
                        m.localidad.nombre, // Acceder al nombre de la localidad
                        m.localidad.provincia.nombre // Acceder al nombre de la provincia
                    );
                });
                crearTabla(monumentos);
            })
            .catch(error => {
                console.error("Error en la solicitud:", error);
            });


    }

    //Accionamos la búsqueda al hacer click en el botón
    buttonSearch.addEventListener("click", buscarMonumentos)








//Método para cargar los datos en el mapa
    var markers = []

    function cargarDatosMapa(monumentosCargar) {

        //Borramos los markers del mapa
        for (const marker of markers) {
            marker.remove()
        }


        for (const monumento of monumentosCargar) {
            //Creamos un marcador en el mapa para cada monumento
            var marcador = L.marker([monumento.longitud, monumento.latitud]).addTo(mapa)
            markers.push(marcador)
            marcador.bindPopup(monumento.nombre + "/n/n" +
                monumento.direccion + "/n/n" +
                "Tipo: " + monumento.tipo + "/n/n" +
                monumento.descripcion + "/n/n" +
                monumento.longitud + " " + monumento.latitud)

        }
    }

    //Se cargan todos los datos de la base de datos
    async function cargarDatosPorDefecto() {

        const res = await axios.get("http://localhost:2930/monumentos/")
            .then(response => {

                console.log(response.data);
                monumentosDefecto = response.data.map(item => {
                    const m = item.monumento; // Acceder al objeto "monumento"


                    return new Monumento(
                        m.id,
                        m.nombre,
                        m.direccion,
                        m.codigoPostal, // Asegúrate de que el nombre del campo coincida
                        m.longitud,
                        m.latitud,
                        m.descripcion,
                        m.tipo,
                        m.localidad.nombre, // Acceder al nombre de la localidad
                        m.localidad.provincia.nombre // Acceder al nombre de la provincia
                    );
                });

                cargarDatosMapa(monumentosDefecto);
                crearTabla(monumentosDefecto);

            })
            .catch(error => {
                console.error("Error en la solicitud:", error);
            });


    }

    //Accionamos el reseteo de monumentos cuando se hace click
    buttonCancel.addEventListener("click", cargarDatosPorDefecto)
    console.log(mapa)

    //Cargamos los datos de la búsqueda en el mapa al seleccionar el checkbox
    const checkBox = document.getElementById("showResults")
    checkBox.addEventListener("change", function () {
        if (checkBox.checked) {
            cargarDatosMapa(monumentos)
        }
        else {
            cargarDatosMapaPorDefecto()
        }
    })

    //Método para cargar la tabla
    const tabla = document.getElementById("table")
    var monumentosTableBody = document.getElementById("table").getElementsByTagName("tbody")[0];
    function crearTabla(monumentosCargar){
        //Borramos los datos de la tabla

        for (let i = monumentosTableBody.rows.length - 1; i > 0; i--) {
            monumentosTableBody.deleteRow(i);
        }


        for (const monumento of monumentosCargar) {
            //Creamos una fila de la tabla para cada monumento
            const nuevaFila = document.createElement("tr")
            const celdaNombre = document.createElement("td")
            const celdaTipo = document.createElement("td")
            const celdaDireccion = document.createElement("td")
            const celdaLocalidad = document.createElement("td")
            const celdaCodPostal = document.createElement("td")
            const celdaProvincia = document.createElement("td")
            const celdaDescripcion = document.createElement("td")
            const celdaLongitud = document.createElement("td")
            const celdaLatitud = document.createElement("td")
            celdaNombre.textContent = monumento.nombre
            celdaTipo.textContent = monumento.tipo
            celdaDireccion.textContent = monumento.direccion
            celdaLocalidad.textContent = monumento.localidad
            celdaCodPostal.textContent = monumento.codigo_postal
            celdaProvincia.textContent = monumento.provincia
            celdaDescripcion.textContent = monumento.descripcion
            celdaLongitud.textContent = monumento.longitud
            celdaLatitud.textContent = monumento.latitud
            nuevaFila.appendChild(celdaNombre)
            nuevaFila.appendChild(celdaTipo)
            nuevaFila.appendChild(celdaDireccion)
            nuevaFila.appendChild(celdaLocalidad)
            nuevaFila.appendChild(celdaCodPostal)
            nuevaFila.appendChild(celdaProvincia)
            nuevaFila.appendChild(celdaDescripcion)
            nuevaFila.appendChild(celdaLongitud)
            nuevaFila.appendChild(celdaLatitud)

            const cuerpoTabla = tabla.querySelector("tbody")
            cuerpoTabla.appendChild(nuevaFila)
        }
    }

    async function cargarDatosMapaPorDefecto(){
        const res = await axios.get("http://localhost:2930/monumentos/")
            .then(response => {

                console.log(response.data);
                monumentosDefecto = response.data.map(item => {
                    const m = item.monumento; // Acceder al objeto "monumento"


                    return new Monumento(
                        m.id,
                        m.nombre,
                        m.direccion,
                        m.codigoPostal, // Asegúrate de que el nombre del campo coincida
                        m.longitud,
                        m.latitud,
                        m.descripcion,
                        m.tipo,
                        m.localidad.nombre, // Acceder al nombre de la localidad
                        m.localidad.provincia.nombre // Acceder al nombre de la provincia
                    );
                });

                cargarDatosMapa(monumentosDefecto);
            })
            .catch(error => {
                console.error("Error en la solicitud:", error);
            });
    }



//Cragamos los datos al principio
     cargarDatosPorDefecto()



