const buttonCargar = document.getElementById("buttonCargar");
const selectAllCheckbox = document.getElementById("selectAll");
const regionCheckboxes = document.querySelectorAll(".region");
const datos = {
    euskadi: true
}

async function cargarEuskadi(){
    try {
        // Realizar la solicitud POST
        const response = await axios.post("http://172.23.186.185:2202/carga", datos);
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

buttonCargar.addEventListener("click", cargarEuskadi);