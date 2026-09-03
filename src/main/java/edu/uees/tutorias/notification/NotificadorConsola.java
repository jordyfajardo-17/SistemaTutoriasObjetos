package edu.uees.tutorias.notification;

import edu.uees.tutorias.domain.Reserva;

public class NotificadorConsola implements Notificador {
    @Override
    public void notificarReservaCreada(Reserva reserva) {
        System.out.println("Reserva creada: " + reserva.getId());
    }

    @Override
    public void notificarReservaConfirmada(Reserva reserva) {
        System.out.println("Reserva confirmada: " + reserva.getId());
    }

    @Override
    public void notificarReservaCancelada(Reserva reserva) {
        System.out.println("Reserva cancelada: " + reserva.getId());
    }
}
