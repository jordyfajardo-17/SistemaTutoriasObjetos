package edu.uees.tutorias.domain;

import java.time.LocalDateTime;

public class Reserva {
    private final String id;
    private final Estudiante estudiante;
    private final Docente docente;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private EstadoReserva estado;

    public Reserva(String id, Estudiante estudiante, Docente docente,
                   LocalDateTime inicio, LocalDateTime fin) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id es obligatorio");
        }
        if (estudiante == null || docente == null) {
            throw new IllegalArgumentException("Estudiante y docente son obligatorios");
        }
        if (inicio == null || fin == null || !inicio.isBefore(fin)) {
            throw new IllegalArgumentException("El inicio debe ser anterior al fin");
        }

        this.id = id;
        this.estudiante = estudiante;
        this.docente = docente;
        this.inicio = inicio;
        this.fin = fin;
        this.estado = EstadoReserva.SOLICITADA;
    }

    public void confirmar() {
        if (estado != EstadoReserva.SOLICITADA) {
            throw new IllegalStateException("Solo se puede confirmar una reserva solicitada");
        }
        estado = EstadoReserva.CONFIRMADA;
    }

    public void cancelar() {
        if (estado == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva ya está cancelada");
        }
        estado = EstadoReserva.CANCELADA;
    }

    public void reprogramar(LocalDateTime nuevoInicio, LocalDateTime nuevoFin) {
        if (estado == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("No se puede reprogramar una reserva cancelada");
        }
        if (nuevoInicio == null || nuevoFin == null || !nuevoInicio.isBefore(nuevoFin)) {
            throw new IllegalArgumentException("El inicio debe ser anterior al fin");
        }
        this.inicio = nuevoInicio;
        this.fin = nuevoFin;
        this.estado = EstadoReserva.REPROGRAMADA;
    }

    public String getId() { return id; }
    public Estudiante getEstudiante() { return estudiante; }
    public Docente getDocente() { return docente; }
    public LocalDateTime getInicio() { return inicio; }
    public LocalDateTime getFin() { return fin; }
    public EstadoReserva getEstado() { return estado; }
}
