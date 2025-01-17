document.addEventListener("DOMContentLoaded", async function() {



const buttonBorrar = document.getElementById("buttonBorrar");
    const buttonCargar = document.getElementById("buttonCargar");
const selectAllCheckbox = document.getElementById("selectAll");
const regionCheckboxes = document.querySelectorAll(".region");
const regionCastilla = document.getElementById("castillaYLeon");
const regionValencia = document.getElementById("comunitatValenciana");
const regionEuskadi = document.getElementById("euskadi");

reporte = "";
const apiCarga = "http://localhost:810/carga/";
const apiBorrar = "http://localhost:810/vaciar";

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
        console.log(apiCarga);
        const response = await axios.post(apiCarga, params,{
            timeout: 120000000000
        })

         reporteRAW = reporteRAW.replace(/(?:\r\n|\r|\n)/g, '<br>').replace(/"/g, '');

                // Cambiamos el texto del informe de fallos
                const informeElement = document.getElementById("informe fallos");
                if (informeElement) {
                    informeElement.innerHTML = reporteRAW; // Usamos innerHTML para interpretar <br>
                    informeElement.style.whiteSpace = "pre-wrap";
                }
        console.log("Respuesta del servidor:", response.data); // Manejar la respuesta
    } catch (error) {
        console.error("Error en la solicitud:", error);
    }
}

async function borrar() {
    try {
        const response = await axios.delete(apiBorrar);
        document.getElementById("informe fallos").textContent = "Se ha borrado la base de datos correctamente.";
        console.log(response.data);
    }catch (error) {
        document.getElementById("informe fallos").textContent = "Se ha producido un error en el borrado.";
        console.error("Error en la solicitud de borrado.")
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

buttonCargar.addEventListener("click", cargar);
buttonBorrar.addEventListener("click", borrar)

});