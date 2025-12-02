package com.turnero.services;

import com.turnero.entities.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoService {
    private List<Turno> turnos = new ArrayList<>();

    public Turno crearTurno(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    public Turno obtenerTurno(Long id) {
        if (id == null) {
            return null;
        }

        return turnos.stream()
                .filter(t -> id.equals(t.getIdentificador()))
                .findFirst()
                .orElse(null);
    }

    public List<Turno> obtenerTodos() {
        return new ArrayList<>(turnos);
    }

    public Turno actualizarTurno(Long id, Turno turnoActualizado) {
        Turno turno = obtenerTurno(id);

        if (turno == null) {
            return null;
        }

        turno.setEstado(turnoActualizado.getEstado());
        turno.setDescripcion(turnoActualizado.getDescripcion());
        turno.setFecha(turnoActualizado.getFecha());
        turno.setCiudadano(turnoActualizado.getCiudadano());

        return turno;
    }

    public boolean eliminarTurno(Long id) {
        if (id == null){
            return false;
        }

        return turnos.removeIf(t -> id.equals(t.getIdentificador()));
    }
}
