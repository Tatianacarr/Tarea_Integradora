package Servicio;

import Modelo.Empleado;
import Modelo.Medico;
import Modelo.Administrativo;
import java.util.ArrayList;

public class EmpleadoServicio {
    private ArrayList<Empleado> listaEmpleados;

    public EmpleadoServicio() {
        this.listaEmpleados = new ArrayList<>();
    }

    public boolean registrarEmpleado(Empleado nuevoEmpleado) {
        if (buscarPorCedula(nuevoEmpleado.getCedula()) != null) {
            System.out.println("Error: Cédula duplicada. Registro bloqueado."); // Requerimiento
            return false;
        }
        listaEmpleados.add(nuevoEmpleado);
        return true;
    }

    public ArrayList<Empleado> obtenerTodos() {
        return listaEmpleados;
    }
    public Empleado buscarPorCedula(String cedula) {
        for (Empleado emp : listaEmpleados) {
            if (emp.getCedula().equals(cedula)) {
                return emp;
            }
        }
        return null;
    }

    public boolean reemplazarInformacion(String cedula, Empleado empleadoModificado) {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getCedula().equals(cedula)) {
                listaEmpleados.set(i, empleadoModificado);
                return true;
            }
        }
        return false;
    }
    public boolean eliminarRegistro(String cedula) {
        Empleado emp = buscarPorCedula(cedula);
        if (emp != null) {
            listaEmpleados.remove(emp);
            return true;
        }
        return false;
    }
    public void mostrarEstadisticas() {
        int totalMedicos = 0;
        int totalAdmins = 0;
        double pagoMedicos = 0;
        double pagoAdmins = 0;
        Empleado mayorIngreso = null;

        for (Empleado emp : listaEmpleados) {
            double pago = emp.calcularPago();

            if (emp instanceof Medico) {
                totalMedicos++;
                pagoMedicos += pago;
            } else if (emp instanceof Administrativo) {
                totalAdmins++;
                pagoAdmins += pago;
            }

            if (mayorIngreso == null || pago > mayorIngreso.calcularPago()) {
                mayorIngreso = emp;
            }
        }

        System.out.println("\n===== ESTADÍSTICAS DEL SISTEMA =====");
        System.out.println("Total Médicos: " + totalMedicos);
        System.out.println("Total Administrativos: " + totalAdmins);
        System.out.println("Total Empleados: " + listaEmpleados.size());
        System.out.println("Pago Total Médicos: $" + pagoMedicos);
        System.out.println("Pago Total Administrativos: $" + pagoAdmins);
        if (mayorIngreso != null) {
            System.out.println("Empleado con mayor ingreso: " + mayorIngreso.getNombre() + " ($" + mayorIngreso.calcularPago() + ")");
        } else {
            System.out.println("Empleado con mayor ingreso: Ninguno");
        }
    }
}