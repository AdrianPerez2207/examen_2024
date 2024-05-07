package org.examen_2024.entities;

import java.time.LocalDate;

public class Usuario extends Persona{

    private LocalDate fechaAlta;

    public Usuario(Long id, String nombre, String apellidos, String email, String movil,
                   LocalDate fechaAlta) {
        super(id, nombre, apellidos, email, movil);
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Usuario{");
        sb.append(", id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellidos='").append(apellidos).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", movil=").append(movil);
        sb.append("fechaAlta=").append(fechaAlta);
        sb.append('}');
        return sb.toString();
    }
}
