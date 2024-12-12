package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // Crear empleado
        Empleado empleado1 = new Empleado(1L, "Pepe", "Melonez", "Oficinista", 1200, "30-04-2013");
        ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();
        controladoraPersistencia.crearEmpleado(empleado1);

        //Borrar un empleado de la BD
        controladoraPersistencia.borrarEmpleado(1L);

        //Modificar un salario de empleado, por ejemplo
        empleado1.setSalario(310);
        controladoraPersistencia.modificarEmpleado(empleado1);

        //Obtener los empleados desde la base de datos
        List<Empleado> empleados=controladoraPersistencia.traerEmpleados();
        for (Empleado empleadoBD: empleados){
            System.out.println(empleadoBD);
        }

        System.out.println(empleado1);
    }
}
