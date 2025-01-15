

const selectAllCheckbox = document.getElementById("selectAll");
const regionCheckboxes = document.querySelectorAll(".region");


selectAllCheckbox.addEventListener("change", function() {
   if(selectAllCheckbox.checked){
       regionCheckboxes.forEach(function(checkbox){
           checkbox.checked = false;
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