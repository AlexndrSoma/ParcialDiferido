/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author uca
 */
public class Filtro {

    private int id;
    private String numInscripcion;
    private String nombre;
    private String propietario;
    private String edad;
    private String raza;
    private boolean estado;

    public Filtro() {

    }

    public Filtro(int id, String numInscripcion, String nombre, String propietario, String edad, String raza, boolean estado) {
        this.id = id;
        this.numInscripcion = numInscripcion;
        this.nombre = nombre;
        this.propietario = propietario;
        this.edad=edad;
        this.raza = raza;
        this.estado = estado;

    }

    public Filtro(String numInscripcion, String nombre, String propietario,String edad, String raza, boolean estado) {
        this.numInscripcion = numInscripcion;
        this.nombre = nombre;
        this.propietario = propietario;
        this.edad=edad;
        this.raza = raza;
        this.estado = estado;
    }

    public Filtro(String nombre, String propietario, String edad, String raza, boolean estado) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.edad=edad;
        this.raza = raza;
        this.estado = estado;
    }
    /*
    public Filtro(String propietario, String raza, boolean estado) {
        this.propietario = propietario;
        this.raza = raza;
        this.estado = estado;
    }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumInscripcion() {
        return numInscripcion;
    }

    public void setNumInscripcion(String numInscripcion) {
        this.numInscripcion = numInscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Filtro{" + "id=" + id + ", numInscripcion=" + numInscripcion + ", nombre=" + nombre + ", propietario=" + propietario + ", edad=" + edad + ", raza=" + raza + ", estado=" + estado + '}';
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    
}
