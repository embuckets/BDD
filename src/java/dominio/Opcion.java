/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Objects;
import java.util.Random;

/**
 *
 * @author emilio
 */
public class Opcion {

    private int idOpcion;
    private int idEncuesta;
    private String opcion;
    private int votos;

    public Opcion(int idOpcion, int idEncuesta, String opcion) {
        this.idOpcion = idOpcion;
        this.idEncuesta = idEncuesta;
        this.opcion = opcion;
    }

    public Opcion() {
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idOpcion;
        hash = 97 * hash + this.idEncuesta;
        hash = 97 * hash + Objects.hashCode(this.opcion);
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
        final Opcion other = (Opcion) obj;
        if (this.idOpcion != other.idOpcion) {
            return false;
        }
        if (this.idEncuesta != other.idEncuesta) {
            return false;
        }
        if (!Objects.equals(this.opcion, other.opcion)) {
            return false;
        }
        return true;
    }
    
    public int generateID() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

}
