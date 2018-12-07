/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function Encuesta(id, titulo, descripcion) {
    this.id = id;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.abre = null;
    this.cierra = null;
    this.setAbre = function (year, month, day, hour, minute, second) {
        this.abre = new Date(year, month, day, hour, minute, second);
    };
    this.setAbre = function (date) {
        this.abre = new Date(date);
    };
    this.setCierra = function (year, month, day, hour, minute, second) {
        this.cierra = new Date(year, month, day, hour, minute, second);
    };
    this.setCierra = function (date) {
        this.cierra = new Date(date);
    };
}

function buildEncuesta(jsonObj) {
    var id_encuesta = jsonObj.id_encuesta;
    var titulo = jsonObj.titulo;
    var descripcion = jsonObj.descripcion;
    var encuesta = new Encuesta(id_encuesta, titulo, descripcion);
    var abre = convertToLocalizedDateTime(jsonObj.abre.year, jsonObj.abre.monthValue, jsonObj.abre.dayOfMonth, jsonObj.abre.hour, jsonObj.abre.minute, jsonObj.abre.second);
    encuesta.setAbre(abre);
    var cierra = convertToLocalizedDateTime(jsonObj.cierra.year, jsonObj.cierra.monthValue, jsonObj.cierra.dayOfMonth, jsonObj.cierra.hour, jsonObj.cierra.minute, jsonObj.cierra.second);
    encuesta.setCierra(cierra);
    return encuesta;
}

function convertToLocalizedDateTime(year, month, day, hour, minute, second) {
    var validMonth = month.length == 1 ? '0' + month : month;
    var validDay = day.length == 1 ? '0' + day : day;
    return year + '-' + validMonth + '-' + validDay + 'T' + hour + ':' + minute + ':' + second + '-06:00';
}

function display(jsonText) {
    var container = document.getElementById("container");
    while (container.lastChild) {
        container.removeChild(container.lastChild);
    }
    var jsonArray = JSON.parse(jsonText);
    for (i in jsonArray) {
        var encuesta = buildEncuesta(jsonArray[i]);
        var card = buildCard(encuesta);
        container.appendChild(card);
    }

}

function buildCard(encuesta) {
    var cardDiv = document.createElement("div");
    cardDiv.className = "card grid-item";
    var cardTitle = document.createElement("h3");
    cardTitle.className = "card-title";
    cardTitle.innerHTML = encuesta.titulo;
    var cardText = document.createElement("p");
    cardText.className = "card-text";
    cardText.innerHTML = encuesta.descripcion;

    var cardAbre = document.createElement("p");
    cardAbre.className = "text-muted";
    cardAbre.innerHTML = "Abre: " + encuesta.abre.toLocaleString();

    var cardCierra = document.createElement("p");
    cardCierra.className = "text-muted";
    cardCierra.innerHTML = "Cierra: " + encuesta.cierra.toLocaleString();

    var cardButton = document.createElement("input");
    cardButton.type = "submit";
    cardButton.value = "Resultados";
    cardButton.className = "card-button";
    if (encuesta.cierra > new Date()) {
        cardButton.disabled = true;
    }

    var form = document.createElement("form");
    form.method = "POST";
    form.action = "encuesta"
    var hidden = document.createElement("input");
    hidden.type = "hidden";
    hidden.name = "encuestaId";
    hidden.value = encuesta.id;

    form.appendChild(cardTitle);
    form.appendChild(cardText);
    form.appendChild(cardAbre);
    form.appendChild(cardCierra);
    form.appendChild(cardButton);
    form.appendChild(hidden);

    cardDiv.appendChild(form);
    return cardDiv;
}

function requestEncuestas() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            display(this.responseText);
        }
    };
    var abre = document.getElementById("abre-date").value;
    var cierra = document.getElementById("cierra-date").value;
    xhttp.open("GET", "encuesta?abre=" + abre + "&cierra=" + cierra, true);
    xhttp.send();
}

