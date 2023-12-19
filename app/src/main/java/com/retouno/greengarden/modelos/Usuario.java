package com.retouno.greengarden.modelos;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String nickname;
    private String password;

    public Usuario(String nombre, String apellido, String correo, String nickname, String password){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nickname = nickname;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
