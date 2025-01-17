const buttonCargar = document.getElementById("buttonCargar");
const selectAllCheckbox = document.getElementById("selectAll");
const regionCheckboxes = document.querySelectorAll(".region");
const regionCastilla = document.getElementById("castillaYLeon");
const regionValencia = document.getElementById("comunitatValenciana");
const regionEuskadi = document.getElementById("euskadi");

reporte = "";
const apiBusqueda = "http://localhost:810/carga/";

async function cargar(){
    try {
        const params ={} ;
        if(selectAllCheckbox.checked == true){
        params.todos = true;
        }else{
            params.todos = false;
            if(regionCastilla.checked == true){params.cyl = true}else{params.cyl = false}
            if(regionValencia.checked == true){params.cv = true}else{params.cv = false}
            if(regionEuskadi.checked == true){params.eus = true}else{params.eus = false}
        }
        // Realizar la solicitud POST
        const response = await axios.post(apiBusqueda, params)
        reporte = response;
        document.getElementById("informe fallos").textContent = JSON.stringify(reporte.data, null, 2);
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

buttonCargar.addEventListener("click", cargar);

