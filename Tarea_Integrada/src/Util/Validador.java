package Util;

import java.util.regex.Pattern;

public class Validador {

    public static void validarEdad(int edad) throws IllegalArgumentException {
        if (edad <= 0 || edad >= 150) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0 y menor a 150.");
        }
    }
    public static void validarTextoVacio(String texto, String campo) throws IllegalArgumentException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + campo + "' no puede estar vacío.");
        }
    }

    public static void validarCorreo(String correo) throws IllegalArgumentException {
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("El correo debe contener un '@'.");
        }
    }

    public static void validarTelefono(String telefono) throws IllegalArgumentException {
        if (telefono == null || !Pattern.matches("\\d+", telefono)) {
            throw new IllegalArgumentException("El teléfono debe contener solo números.");
        }
    }
    public static void validarMayorQueCero(double valor, String campo) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor de '" + campo + "' debe ser mayor a cero.");
        }
    }
}