//Creamos el mapa con el marcador en España

var monumentos = []

var mapa = L.map("contenedor-del-mapa").setView([40.41692954150457, -3.667879444630583], 6)
    L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png?", {}).addTo(mapa)

//Declaramos la API de búsqueda que tenemos lanzada en la máquina virtual
    const apiBusqueda = "http://172.23.186.185:2930/monumentos/"

//Cogemos los valores por los que queremos filtrar
    var localidadElement = document.getElementById("localidad")
    var codigo_postalElement = document.getElementById("codigo_postal")
    var provinciaElement = document.getElementById("provincia")
    //var tipoElement = document.getElementById("tipo")

//Identificamos el botón de búsqueda y la cancelar
    const buttonSearch = document.getElementById("search")
    const buttonCancel = document.getElementById("cancel")

//Realizamos la búsqueda a la API
    async function buscarMonumentos() {

        //Cogemos los valoresque vamos a filtrar
        const localidad = localidadElement.value
        const codigoPostal = codigo_postalElement.value
        const provincia = provinciaElement.value

        //Añadimos a los paramtros los campos que estén llenos
        const params ={} ;

        if(localidad){
            params.localidad = localidad.toUpperCase();
        }

        if(codigoPostal){
            console.log("Entra")
            params.codigoPostal = codigoPostal;
        }

        if (provincia){
            console.log("Entra")
            params.provincia = provincia.toUpperCase();
        }
       /* if(tipoElement.value || tipoElement.value!=="Todos"){
            params.tipo = tipoElement.value;
        }*/


        //Realizamos la petición a la API
        axios.get("http://172.23.186.185:2930/monumentos/",{
            params: params
        })
            .then(response => {
                monumentos = []
                console.log(response.data);
                monumentos = response.data.map(item => {
                    const m = item.monumento; // Acceder al objeto "monumento"

                    if (m.latitud && m.longitud) {
                        const marker = L.marker([m.latitud, m.longitud]).addTo(mapa);
                        marker.bindPopup(m.nombre);
                    } else {
                        console.warn("Latitud o longitud no disponibles para el monumento:", m);
                    }

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
            })
            .catch(error => {
                console.error("Error en la solicitud:", error);
            });


    }

    //Accionamos la búsqueda al hacer click en el botón
    buttonSearch.addEventListener("click", buscarMonumentos)


    //Limpiamos los campos al hacer clic en el botón
    var marcador = L.marker([4.6281045, -74.0654527]).addTo(mapa)
    marcador.bindPopup("Hola GeoCositas")

    const circulo = L.circle([4.613573, -74.063889], {
        radius: 1000,
        color: "green"
    }).addTo(mapa)
    circulo.bindPopup("Programación en SIG")

    const tabla = document.getElementById("table")


    function cargarDatos(){
        for (const monumento of monumentos) {
            var marcador = L.marker([monumento.longitud, monumento.latitud]).addTo(mapa)
            marcador.bindPopup(monumento.nombre + "/n/n" +
                monumento.direccion + "/n/n" +
                "Tipo: " + monumento.tipo + "/n/n" +
                monumento.descripcion + "/n/n" +
                monumento.longitud + " " + monumento.latitud)
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

    async function cargarDatosPorDefecto(){

        axios.get("http://172.23.186.185:2930/monumentos/")
            .then( response => {
                monumentos= []
                console.log(response.data);
                monumentos = response.data.map(item => {
                    const m = item.monumento; // Acceder al objeto "monumento"


                        if (m.latitud && m.longitud) {
                            const marker = L.marker([m.latitud, m.longitud]).addTo(mapa);
                            marker.bindPopup(m.nombre);
                        } else {
                            console.warn("Latitud o longitud no disponibles para el monumento:", m);
                        }


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

                cargarDatos();
                // Si deseas devolver la lista de monumentos, puedes hacerlo aquí
                console.log(monumentos);

                         })
            .catch(error => {
                console.error("Error en la solicitud:", error);
            });



    }

    //Accionamos el reseteo de monumentos cuando se hace click
        buttonCancel.addEventListener("click", cargarDatosPorDefecto)



    function clicSobreMapa(evento) {
        alert("Diste clic en el punto con coordenadas latitud: " + evento.latlng.lat + " y longitud: " + evento.latlng.lng)
    }

    mapa.on("click", clicSobreMapa);

    console.log(mapa)
    console.log(marcador)
//console.log(circulo)

