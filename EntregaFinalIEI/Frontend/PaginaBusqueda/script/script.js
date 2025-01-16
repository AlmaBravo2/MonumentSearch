var mapa = L.map("contenedor-del-mapa").setView([40.41692954150457, -3.667879444630583], 6)
L.tileLayer("https://tile.openstreetmap.org/{z}/{x}/{y}.png?", {}).addTo(mapa)

/*var marcador = L.marker([4.6281045, -74.0654527]).addTo(mapa)
marcador.bindPopup("Hola GeoCositas")

const circulo = L.circle([4.613573, -74.063889], {
    radius: 1000,
    color: "green"
}).addTo(mapa)
circulo.bindPopup("Programación en SIG")*/

const monumentos = []
const tabla = document.getElementById("table")

for (const monumento of monumentos){
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
    nuevaFila.appendChild(celdaDescripcion)
    nuevaFila.appendChild(celdaLongitud)
    nuevaFila.appendChild(celdaLatitud)

    const cuerpoTabla = tabla.querySelector("tbody")
    cuerpoTabla.appendChild(nuevaFila)
}


function clicSobreMapa(evento){
    alert("Diste clic en el punto con coordenadas latitud: " + evento.latlng.lat + " y longitud: " + evento.latlng.lng)
}

mapa.on("click", clicSobreMapa);

console.log(mapa)
console.log(marcador)
//console.log(circulo)

