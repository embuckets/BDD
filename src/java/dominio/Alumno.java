/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Objects;

/**
 *
 * @author emilio
 */
public class Alumno {
    private String matricula;
    private String nombre;
    private String paterno;
    private String materno;
    private int idDivision;
    private int idUnidad;

    public Alumno(String matricula, String nombre, String paterno, String materno, int idDivision, int idUnidad) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.idDivision = idDivision;
        this.idUnidad = idUnidad;
    }

    public Alumno() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public int getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(int idDivision) {
        this.idDivision = idDivision;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.matricula);
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.paterno);
        hash = 23 * hash + Objects.hashCode(this.materno);
        hash = 23 * hash + this.idDivision;
        hash = 23 * hash + this.idUnidad;
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
        final Alumno other = (Alumno) obj;
        if (this.idDivision != other.idDivision) {
            return false;
        }
        if (this.idUnidad != other.idUnidad) {
            return false;
        }
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.paterno, other.paterno)) {
            return false;
        }
        if (!Objects.equals(this.materno, other.materno)) {
            return false;
        }
        return true;
    }
    
    
}
