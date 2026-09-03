package edu.uees.tutorias.service;

import edu.uees.tutorias.domain.Disponibilidad;
import edu.uees.tutorias.domain.Docente;
import edu.uees.tutorias.domain.Estudiante;
import edu.uees.tutorias.domain.Reserva;
import edu.uees.tutorias.notification.Notificador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioReservas {
    private final Notificador notificador;
    private final List<Disponibilidad> disponibilidades = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();

    public ServicioReservas(Notificador notificador) {
        if (notificador == null) {
            throw new IllegalArgumentException("El notificador es obligatorio");
        }
        this.notificador = notificador;
    }

    public void agregarDisponibilidad(Disponibilidad disponibilidad) {
        disponibilidades.add(disponibilidad);
    }

    public Reserva solicitarReserva(String id, Estudiante estudiante, Docente docente,
                                    LocalDateTime inicio, LocalDateTime fin) {
        boolean disponible = disponibilidades.stream()
                .anyMatch(d -> d.getDocente().equals(docente) && d.contiene(inicio, fin));

        if (!disponible) {
            throw new IllegalStateException("El docente no tiene disponibilidad en ese horario");
        }

        boolean ocupado = reservas.stream().anyMatch(r ->
                r.getDocente().equals(docente)
                        && r.getEstado() != edu.uees.tutorias.domain.EstadoReserva.CANCELADA
                        && inicio.isBefore(r.getFin())
                        && fin.isAfter(r.getInicio()));

        if (ocupado) {
            throw new IllegalStateException("El horario ya está reservado");
        }

        Reserva reserva = new Reserva(id, estudiante, docente, inicio, fin);
        reservas.add(reserva);
        notificador.notificarReservaCreada(reserva);
        return reserva;
    }

    public void confirmarReserva(Reserva reserva) {
        reserva.confirmar();
        notificador.notificarReservaConfirmada(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        reserva.cancelar();
        notificador.notificarReservaCancelada(reserva);
    }

    public List<Reserva> obtenerReservas() {
        return List.copyOf(reservas);
    }
}
