function newOption() {
    if (document.getElementById("myInput").value === '') {
        alert("Las opciones no pueden ser vacias");
        return;
    }

    var optionsContainer = document.getElementById("myOptions");

    var newOption = document.createElement("input");
    var deleteBtn = document.createElement("span");

    var optionDiv = document.createElement("div");
    optionDiv.appendChild(newOption);
    optionDiv.appendChild(deleteBtn);
    optionsContainer.appendChild(optionDiv);

    newOption.value = document.getElementById("myInput").value;
    newOption.name = "options[]";
    newOption.readOnly = true;
    newOption.className = "form-crear-input-opc disabled";

    deleteBtn.innerHTML = "\u00D7";
    deleteBtn.className = "form-crear-del";
    deleteBtn.onclick = function () {
        var children = optionsContainer.children;
        optionsContainer.removeChild(optionDiv);
    }
    document.getElementById("myInput").value = '';
}

function validar() {
    var optionsContainer = document.getElementById("myOptions");
    if (optionsContainer.children.length < 2) {
        alert("Se necesistan dos opciones como mínimo");
        return false;
    } 
    var abre = new Date(document.getElementById("abre-date").value + " " + document.getElementById("abre-time").value);
    var cierra = new Date(document.getElementById("cierra-date").value + " " + document.getElementById("cierra-time").value);
    if (abre > cierra){
        alert("La fecha de cierre tiene que ser despues de la fecha que abre");
        return false;
    }
    return true;
}