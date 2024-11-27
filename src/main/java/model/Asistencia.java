package model;

import java.time.LocalDateTime;

public class Asistencia {
    private int idAsistencia;
    private int idUsuario;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSalida;

    // Constructor vacío
    public Asistencia() {}

    // Getters y Setters
    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalDateTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalDateTime getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(LocalDateTime horarioSalida) {
        this.horarioSalida = horarioSalida;
    }
}
