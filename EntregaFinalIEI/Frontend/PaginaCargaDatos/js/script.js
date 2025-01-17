document.addEventListener("DOMContentLoaded", async function() {





    const buttonCargar = document.getElementById("buttonCargar");
const selectAllCheckbox = document.getElementById("selectAll");
const regionCheckboxes = document.querySelectorAll(".region");
const regionCastilla = document.getElementById("castillaYLeon");
const regionValencia = document.getElementById("comunitatValenciana");
const regionEuskadi = document.getElementById("euskadi");

reporte = "";
const apiBusqueda = "http://localhost:810/carga/";
const hola = "http://localhost:2202/hola";

async function cargar(){
    try {
        var params ={} ;
        if(selectAllCheckbox.checked == true){
        params.todos = true;
        }else{
            params.todos = false;
            params.cyl = regionCastilla.checked;
            params.cv = regionValencia.checked;
            params.eus = regionEuskadi.checked;
        }
        // Realizar la solicitud POST
        console.log(JSON.stringify(params, null, 2));
        console.log(apiBusqueda);
        const response = await axios.post(apiBusqueda, params)

        //Prueba
        const response2 = await axios.get(hola)
        console.log(response2.data);

        //Cambiamos el texto del informe de fallos
        document.getElementById("informe fallos").textContent = JSON.stringify(response.data, null, 2);
        console.log("Respuesta del servidor:", response.data); // Manejar la respuesta
    } catch (error) {
        console.error("Error en la solicitud:", error);
    }
}


selectAllCheckbox.addEventListener("change", function() {
   if(selectAllCheckbox.checked){
       regionCheckboxes.forEach(function(checkbox){
           checkbox.checked = true;
       });
   }
});

regionCheckboxes.forEach(checkbox => {
    checkbox.addEventListener('change', () => {
        // Si se selecciona una región, desmarcar "Seleccionar todas"
        if (checkbox.checked) {
            selectAllCheckbox.checked = false;
        }

        // Si todas las regiones están seleccionadas, marcar "Seleccionar todas"
        const allSelected = Array.from(regionCheckboxes).every(cb => cb.checked);
        if (allSelected) {
            selectAllCheckbox.checked = true;
            regionCheckboxes.forEach(cb => cb.checked = false);
        }
    });
});

buttonCargar.addEventListener("click", cargar);});

