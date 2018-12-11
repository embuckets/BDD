/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author emilio
 */
public class Encuesta {

    private int idEncuesta;
    private String titulo;
    private String descripcion;
    private LocalDateTime abre;
    private LocalDateTime cierra;
    private int idUnidad;
    private List<Opcion> opciones;
    private Opcion votado;

    public Opcion getVotado() {
        return votado;
    }

    public void setVotado(Opcion votado) {
        this.votado = votado;
    }

    public Encuesta(int idEncuesta, String titulo, String descripcion, LocalDateTime abre, LocalDateTime cierra, int idUnidad) {
        this.idEncuesta = idEncuesta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.abre = abre;
        this.cierra = cierra;
        this.idUnidad = idUnidad;
        this.opciones = new ArrayList<>();
    }

    public Encuesta() {
        this.opciones = new ArrayList<>();
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getAbre() {
        return abre;
    }

    public void setAbre(LocalDateTime abre) {
        this.abre = abre;
    }

    public LocalDateTime getCierra() {
        return cierra;
    }

    public void setCierra(LocalDateTime cierra) {
        this.cierra = cierra;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void addOpcion(Opcion opcion) {
        this.opciones.add(opcion);
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }
    
    public void crearOpciones(String[] opciones){
        for (String opcion : opciones){
            Opcion op = new Opcion(this.idEncuesta, idEncuesta, opcion);
            this.opciones.add(op);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.idEncuesta;
        hash = 41 * hash + Objects.hashCode(this.titulo);
        hash = 41 * hash + Objects.hashCode(this.descripcion);
        hash = 41 * hash + Objects.hashCode(this.abre);
        hash = 41 * hash + Objects.hashCode(this.cierra);
        hash = 41 * hash + this.idUnidad;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Encuesta other = (Encuesta) obj;
        if (this.idEncuesta != other.idEncuesta) {
            return false;
        }
        if (this.idUnidad != other.idUnidad) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.abre, other.abre)) {
            return false;
        }
        if (!Objects.equals(this.cierra, other.cierra)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Encuesta{" + "idEncuesta=" + idEncuesta + ", titulo=" + titulo + ", descripcion=" + descripcion + ", abre=" + abre + ", cierra=" + cierra + ", idUnidad=" + idUnidad + '}';
    }

}
