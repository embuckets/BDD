function display(jsonText) {

    var jsonObj = JSON.parse(jsonText);
    var encuesta = buildEncuesta(jsonObj);
    document.getElementById("titulo").innerHTML = encuesta.titulo;
    document.getElementById("desc").innerHTML = encuesta.descripcion;
    document.getElementById("abre").innerHTML = encuesta.abre.toLocaleString();
    document.getElementById("cierra").innerHTML = encuesta.cierra.toLocaleString();

    var tbody = document.getElementById("resultados");
    var index;
    for (index in encuesta.opciones) {
        var row = tbody.insertRow();
        row.insertCell(0).innerHTML = encuesta.opciones[index].opcion;
        row.insertCell(1).innerHTML = encuesta.opciones[index].votos;
    }
}

function requestResultados() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            display(this.responseText);
            // document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "resultado", true);
    xhttp.send();
}


