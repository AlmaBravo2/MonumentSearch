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
for (const monumento of monumentos){
    var marcador = L.marker([monumento.longitud, monumento.latitud]).addTo(mapa)
    marcador.bindPopup(monumento.nombre + "/n/n" +
        monumento.direccion + "/n/n" +
        "Tipo: " + monumento.tipo + "/n/n" +
        monumento.descripcion + "/n/n" +
        monumento.longitud + " " + monumento.latitud)
}

function clicSobreMapa(evento){
    alert("Diste clic en el punto con coordenadas latitud: " + evento.latlng.lat + " y longitud: " + evento.latlng.lng)
}

mapa.on("click", clicSobreMapa);

console.log(mapa)
console.log(marcador)
//console.log(circulo)

