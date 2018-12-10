
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
    this.setAbre = function (date) {
        this.abre = new Date(date);
    };
    this.setCierra = function (year, month, day, hour, minute, second) {
        this.cierra = new Date(year, month, day, hour, minute, second);
    };
    this.setCierra = function (date) {
        this.cierra = new Date(date);
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
    var abre = convertToLocalizedDateTime(jsonObj.abre.year, jsonObj.abre.monthValue, jsonObj.abre.dayOfMonth, jsonObj.abre.hour, jsonObj.abre.minute, jsonObj.abre.second);
    encuesta.setAbre(abre);
    var cierra = convertToLocalizedDateTime(jsonObj.cierra.year, jsonObj.cierra.monthValue, jsonObj.cierra.dayOfMonth, jsonObj.cierra.hour, jsonObj.cierra.minute, jsonObj.cierra.second);
    encuesta.setCierra(cierra);
    if (jsonObj.votado) {
        encuesta.votado = new Opcion(jsonObj.votado.idOpcion, jsonObj.votado.idEncuesta, jsonObj.votado.opcion, null);
    }
    if (jsonObj.opciones) {
        encuesta.setOpciones(jsonObj.opciones);
    }
    return encuesta;
}

function convertToLocalizedDateTime(year, month, day, hour, minute, second) {
    var validMonth = month.length == 1 ? '0' + month : month;
    var validDay = day.length == 1 ? '0' + day : day;
    return year + '-' + validMonth + '-' + validDay + 'T' + hour + ':' + minute + ':' + second + '-06:00';
}
