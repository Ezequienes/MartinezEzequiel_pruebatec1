package org.example.persistencia;

import org.example.logica.Empleado;
import org.example.persistencia.exceptions.NonexistentEntityException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    EmpleadoJpaController empleJPA = new EmpleadoJpaController();

    public void crearEmpleado(Empleado pers) {
        empleJPA.create(pers);
    }

    public void borrarEmpleado(Long id) {
        try {
            empleJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Empleado> traerEmpleados () {
        return empleJPA.findEmpleadoEntities();
    }

    public void modificarEmpleado (Empleado pers) {

        try {
            empleJPA.edit(pers);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
