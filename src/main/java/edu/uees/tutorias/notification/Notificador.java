package edu.uees.tutorias.notification;

import edu.uees.tutorias.domain.Reserva;

public interface Notificador {
    void notificarReservaCreada(Reserva reserva);
    void notificarReservaConfirmada(Reserva reserva);
    void notificarReservaCancelada(Reserva reserva);
}
