package app;

import Modelo.Empleado;
import Modelo.Medico;
import Modelo.Administrativo;
import Servicio.EmpleadoServicio;
import Util.Validador;

import java.util.Scanner;

public class Main {
    private static EmpleadoServicio servicio = new EmpleadoServicio();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\n===== CLÍNICA SALUD TOTAL =====");
            System.out.println("1. Registrar médico");
            System.out.println("2. Registrar administrativo");
            System.out.println("3. Mostrar empleados");
            System.out.println("4. Buscar por cédula");
            System.out.println("5. Reemplazar información");
            System.out.println("6. Eliminar registro");
            System.out.println("7. Calcular pagos");
            System.out.println("8. Mostrar estadísticas");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                // Conversión obligatoria e interceptación de InputMismatch/NumberFormatException en menú
                String entradaOp = sc.nextLine();
                opcion = Integer.parseInt(entradaOp);

                if (opcion < 1 || opcion > 9) {
                    System.out.println("Error: opción inválida. Fuera de rango.");
                    continue;
                }

                switch (opcion) {
                    case 1: registrar(true, null); break;
                    case 2: registrar(false, null); break;
                    case 3: mostrarTodos(); break;
                    case 4: buscar(); break;
                    case 5: modificar(); break;
                    case 6: eliminar(); break;
                    case 7: calcularPagosTotales(); break;
                    case 8: servicio.mostrarEstadisticas(); break;
                    case 9: System.out.println("Saliendo del sistema..."); break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: opción inválida. Ingrese solo números enteros.");
                opcion = 0; // Resetea para no romper el ciclo continuo
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        } while (opcion != 9);
    }

    // Método reutilizable para altas y modificaciones controlando validaciones y conversiones String->int/double
    private static void registrar(boolean esMedico, String cedulaFija) {
        try {
            String cedula = cedulaFija;
            if (cedula == null) {
                System.out.print("Ingrese Cédula: ");
                cedula = sc.nextLine();
            }
            Validador.validarTextoVacio(cedula, "Cédula");

            System.out.print("Ingrese Nombre: ");
            String nombre = sc.nextLine();
            Validador.validarTextoVacio(nombre, "Nombre");

            System.out.print("Ingrese Edad: ");
            int edad = Integer.parseInt(sc.nextLine()); // Conversión obligatoria
            Validador.validarEdad(edad);

            System.out.print("Ingrese Teléfono: ");
            String telefono = sc.nextLine();
            Validador.validarTelefono(telefono);

            System.out.print("Ingrese Correo: ");
            String correo = sc.nextLine();
            Validador.validarCorreo(correo);

            if (esMedico) {
                System.out.print("Ingrese Especialidad: ");
                String especialidad = sc.nextLine();
                Validador.validarTextoVacio(especialidad, "Especialidad");

                System.out.print("Ingrese Número de Pacientes Atendidos: ");
                int pacientes = Integer.parseInt(sc.nextLine()); // Conversión obligatoria
                Validador.validarMayorQueCero(pacientes, "Pacientes atendidos");

                System.out.print("Ingrese Valor por Consulta: ");
                double valorC = Double.parseDouble(sc.nextLine()); // Conversión obligatoria
                Validador.validarMayorQueCero(valorC, "Valor consulta");

                Medico med = new Medico(cedula, nombre, edad, telefono, correo, especialidad, pacientes, valorC);
                if (cedulaFija == null) {
                    servicio.registrarEmpleado(med);
                } else {
                    servicio.reemplazarInformacion(cedulaFija, med);
                }
            } else {
                System.out.print("Ingrese Departamento: ");
                String departamento = sc.nextLine();
                Validador.validarTextoVacio(departamento, "Departamento");

                System.out.print("Ingrese Horas Trabajadas: ");
                int horas = Integer.parseInt(sc.nextLine()); // Conversión obligatoria
                Validador.validarMayorQueCero(horas, "Horas trabajadas");

                System.out.print("Ingrese Valor por Hora: ");
                double valorH = Double.parseDouble(sc.nextLine()); // Conversión obligatoria
                Validador.validarMayorQueCero(valorH, "Valor por hora");

                Administrativo adm = new Administrativo(cedula, nombre, edad, telefono, correo, departamento, horas, valorH);
                if (cedulaFija == null) {
                    servicio.registrarEmpleado(adm);
                } else {
                    servicio.reemplazarInformacion(cedulaFija, adm);
                }
            }
            System.out.println("Operación realizada con éxito.");

        } catch (NumberFormatException e) {
            System.out.println("Error de Formato: Se esperaba un valor numérico válido (Evite letras en edad/montos).");
        } catch (IllegalArgumentException e) {
            System.out.println("Error de Validación: " + e.getMessage());
        }
    }

    private static void mostrarTodos() {
        if (servicio.obtenerTodos().isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado emp : servicio.obtenerTodos()) {
            System.out.println("\n-----------------------------");
            emp.mostrarInformacion();
        }
    }

    private static void buscar() {
        System.out.print("Ingrese la cédula a buscar: ");
        String cedula = sc.nextLine();
        Empleado emp = servicio.buscarPorCedula(cedula);
        if (emp != null) {
            System.out.println("\n--- Registro Encontrado ---");
            emp.mostrarInformacion();
        } else {
            System.out.println("Registro no encontrado.");
        }
    }

    private static void modificar() {
        System.out.print("Ingrese la cédula del registro a reemplazar: ");
        String cedula = sc.nextLine();
        Empleado emp = servicio.buscarPorCedula(cedula);
        if (emp != null) {
            boolean esMedico = (emp instanceof Medico);
            System.out.println("Actualizando datos para el registro tipo: " + (esMedico ? "Médico" : "Administrativo"));
            registrar(esMedico, cedula);
        } else {
            System.out.println("Registro no encontrado.");
        }
    }

    private static void eliminar() {
        System.out.print("Ingrese la cédula del registro a eliminar: ");
        String cedula = sc.nextLine();
        if (servicio.eliminarRegistro(cedula)) {
            System.out.println("Registro eliminado exitosamente.");
        } else {
            System.out.println("Registro no encontrado.");
        }
    }

    private static void calcularPagosTotales() {
        if (servicio.obtenerTodos().isEmpty()) {
            System.out.println("No hay empleados para liquidar nómina.");
            return;
        }
        System.out.println("\n===== RESUMEN DE PAGOS =====");
        for (Empleado emp : servicio.obtenerTodos()) {
            System.out.println(emp.getNombre() + " (" + emp.getCedula() + ") - Pago: $" + emp.calcularPago());
        }
    }
}