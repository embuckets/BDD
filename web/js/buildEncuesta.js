function buildEncuesta(jsonObj){
    var idEncuesta = jsonObj.idEncuesta;
    var titulo = jsonObj.titulo;
    var descripcion = jsonObj.descripcion;
    var idUnidad = jsonObj.idUnidad;
    var encuesta = new Encuesta(idEncuesta, titulo, descripcion, idUnidad);
    encuesta.setAbre(jsonObj.abre.year, jsonObj.abre.monthValue,jsonObj.abre.dayOfMonth,jsonObj.abre.hour,jsonObj.abre.minute,jsonObj.abre.second);
    encuesta.setCierra(jsonObj.cierra.year, jsonObj.cierra.monthValue,jsonObj.cierra.dayOfMonth,jsonObj.cierra.hour,jsonObj.cierra.minute,jsonObj.cierra.second);


    return encuesta;
}