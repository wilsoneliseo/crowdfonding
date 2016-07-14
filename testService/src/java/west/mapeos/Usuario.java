/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package west.mapeos;

import west.TipoUsuario;

public class Usuario {
    private int idusuario; 
    private String nickname; 
    private String contrasena;
    private String nombre;
    private String direccion;
    private String telefono;
    private String cuentabancaria;
    private TipoUsuario tipo;    

    public Usuario(int idusuario, String nickname, String contrasena, String nombre, String direccion, String telefono, String cuentabancaria, TipoUsuario tipo) {
        this.idusuario = idusuario;
        this.nickname = nickname;
        this.contrasena = contrasena;        
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuentabancaria = cuentabancaria;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuentabancaria() {
        return cuentabancaria;
    }

    public void setCuentabancaria(String cuentabancaria) {
        this.cuentabancaria = cuentabancaria;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
