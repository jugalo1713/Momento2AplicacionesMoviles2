package com.example.fotografos.model;

public class Fotografos {

    private String camara;
    private String tipoFotografia;
    private String nacionalidad;
    private String nombre;
    private String fbId;

    public Fotografos(String camara, String tipoFotografia, String nacionalidad, String nombre) {
        this.camara = camara;
        this.tipoFotografia = tipoFotografia;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
    }


    public Fotografos(String camara, String tipoFotografia, String nacionalidad, String nombre, String fbId) {
        this.camara = camara;
        this.tipoFotografia = tipoFotografia;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.fbId = fbId;
    }

    public Fotografos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCamara() {
        return camara;
    }

    public void setCamara(String camara) {
        this.camara = camara;
    }

    public String getTipoFotografia() {
        return tipoFotografia;
    }

    public void setTipoFotografia(String tipoFotografia) {
        this.tipoFotografia = tipoFotografia;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }


}
