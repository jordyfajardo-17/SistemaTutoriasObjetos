package edu.uees.tutorias.domain;

/**
 * Representa a una persona que participa en el sistema de tutorías.
 */
public abstract class Usuario {
    private final String id;
    private final String nombre;

    protected Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
