package edu.uees.tutorias.domain;

import java.time.LocalDateTime;

public class Disponibilidad {
    private final Docente docente;
    private final LocalDateTime inicio;
    private final LocalDateTime fin;

    public Disponibilidad(Docente docente, LocalDateTime inicio, LocalDateTime fin) {
        if (docente == null) {
            throw new IllegalArgumentException("El docente es obligatorio");
        }
        if (inicio == null || fin == null || !inicio.isBefore(fin)) {
            throw new IllegalArgumentException("El inicio debe ser anterior al fin");
        }
        this.docente = docente;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Docente getDocente() {
        return docente;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public boolean contiene(LocalDateTime inicioReserva, LocalDateTime finReserva) {
        return !inicioReserva.isBefore(inicio) && !finReserva.isAfter(fin);
    }
}
