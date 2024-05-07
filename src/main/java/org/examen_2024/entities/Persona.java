package org.examen_2024.entities;

public class Persona {


    protected Long id;
    protected String nombre;
    protected String apellidos;
    protected String email;
    protected String movil;

    public Persona(Long id, String nombre, String apellidos, String email, String movil) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.movil = movil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Persona{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellidos='").append(apellidos).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", movil=").append(movil);
        sb.append('}');
        return sb.toString();
    }
}
