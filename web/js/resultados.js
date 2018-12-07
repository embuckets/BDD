/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Encuesta(id, titulo, descripcion, idUnidad) {
    this.id = id;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.abre = null;
    this.cierra = null;
    this.idUnidad = idUnidad;
    this.votado = null;
    this.opciones = [];
    this.setAbre = function (year, month, day, hour, minute, second) {
        this.abre = new Date(year, month, day, hour, minute, second);
    };
    this.setCierra = function (year, month, day, hour, minute, second) {
        this.cierra = new Date(year, month, day, hour, minute, second);
    };
    this.setOpciones = function (opciones) {
        var index;
        for (index in opciones) {
            this.opciones[index] = new Opcion(opciones[index].idOpcion, opciones[index].idEncuesta, opciones[index].opcion, opciones[index].votos);
        }
    };
}
function Opcion(idOpcion, idEncuesta, opcion, votos) {
    this.idOpcion = idOpcion;
    this.idEncuesta = idEncuesta;
    this.opcion = opcion;
    this.votos = votos;
}

function buildEncuesta(jsonObj) {
    var idEncuesta = jsonObj.idEncuesta;
    var titulo = jsonObj.titulo;
    var descripcion = jsonObj.descripcion;
    var idUnidad = jsonObj.idUnidad;
    var encuesta = new Encuesta(idEncuesta, titulo, descripcion, idUnidad);
    encuesta.setAbre(jsonObj.abre.year, jsonObj.abre.monthValue, jsonObj.abre.dayOfMonth, jsonObj.abre.hour, jsonObj.abre.minute, jsonObj.abre.second);
    encuesta.setCierra(jsonObj.cierra.year, jsonObj.cierra.monthValue, jsonObj.cierra.dayOfMonth, jsonObj.cierra.hour, jsonObj.cierra.minute, jsonObj.cierra.second);
    if (jsonObj.votado) {
        encuesta.votado = new Opcion(jsonObj.votado.idOpcion, jsonObj.votado.idEncuesta, jsonObj.votado.opcion);
    }
    encuesta.setOpciones(jsonObj.opciones);
    return encuesta;
}

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


