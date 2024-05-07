package org.examen_2024.entities;

import java.time.LocalDate;
import java.util.Objects;

public class TicketSoporte implements Comparable<TicketSoporte>{

    private Long id;
    private LocalDate fechaCreacion;
    private LocalDate fehcaFinalizacion; //nullable

    public enum Estado {ABIERTO, ENPROCESO, RESUELTO};
    private Estado estado;
    private Integer prioridad; //1-3
    private Usuario usuarioSolicitante;
    private Tecnico tenicoAsignado;
    private String comentarios;

    public TicketSoporte(Long id, LocalDate fechaCreacion, LocalDate fehcaFinalizacion, Estado estado, Integer prioridad,
                         Usuario usuarioSolicitante, Tecnico tenicoAsignado, String comentarios) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fehcaFinalizacion = fehcaFinalizacion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioSolicitante = usuarioSolicitante;
        this.tenicoAsignado = tenicoAsignado;
        this.comentarios = comentarios;
    }

    public TicketSoporte(LocalDate fechaCreacion, LocalDate fehcaFinalizacion, Estado estado, Integer prioridad,
                         Usuario usuarioSolicitante, Tecnico tenicoAsignado, String comentarios) {
        this.fechaCreacion = fechaCreacion;
        this.fehcaFinalizacion = fehcaFinalizacion;
        this.estado = estado;
        this.prioridad = prioridad;
        this.usuarioSolicitante = usuarioSolicitante;
        this.tenicoAsignado = tenicoAsignado;
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFehcaFinalizacion() {
        return fehcaFinalizacion;
    }

    public void setFehcaFinalizacion(LocalDate fehcaFinalizacion) {
        this.fehcaFinalizacion = fehcaFinalizacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public Usuario getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public Tecnico getTenicoAsignado() {
        return tenicoAsignado;
    }

    public void setTenicoAsignado(Tecnico tenicoAsignado) {
        this.tenicoAsignado = tenicoAsignado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TicketSoporte{");
        sb.append("id=").append(id);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", fehcaFinalizacion=").append(fehcaFinalizacion);
        sb.append(", estado=").append(estado);
        sb.append(", prioridad=").append(prioridad);
        sb.append(", usuarioSolicitante=").append(usuarioSolicitante);
        sb.append(", tenicoAsignado=").append(tenicoAsignado);
        sb.append(", comentarios='").append(comentarios).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketSoporte that = (TicketSoporte) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(TicketSoporte o) {
        return this.getFechaCreacion().compareTo(o.getFechaCreacion());
    }
}
