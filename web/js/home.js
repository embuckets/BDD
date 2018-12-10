
function display(jsonText) {
    var container = document.getElementById("container");
    while (container.lastChild) {
        container.removeChild(container.lastChild);
    }

    var jsonArray = JSON.parse(jsonText);
    var encuestas = [];
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
    cardButton.className = "card-button";
    if (encuesta.cierra > new Date()) {
        cardButton.value = "Votar";

    } else {
        cardButton.value = "Resultados";

    }
    if (encuesta.abre > new Date()) {
        cardButton.disabled = "true";
    }

    var form = document.createElement("form");
    form.method = "POST";
    form.action = "encuesta";
    var hidden = document.createElement("input");
    hidden.type = "hidden";
    hidden.name = "encuestaId";
    hidden.value = encuesta.id;

    form.appendChild(cardTitle);
    form.appendChild(cardText);
    form.appendChild(cardAbre);
    form.appendChild(cardCierra);
    if (encuesta.votado) {
        var cardVotado = document.createElement("p");
        cardVotado.className = "text-bold";
        cardVotado.innerHTML = "Votaste: " + encuesta.votado.opcion;
        form.appendChild(cardVotado);
    }
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
