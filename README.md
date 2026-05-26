# Tarea_Integradora
Descripción del proyecto

El sistema “Clínica Salud Total” permite administrar el personal de una clínica privada mediante Programación Orientada a Objetos en Java.

El sistema gestiona:

Médicos
Personal Administrativo

Además implementa operaciones CRUD, validaciones, excepciones, conversiones y estadísticas del personal.

Funcionalidades implementadas
Código	Funcionalidad
RF01	Registrar médicos
RF02	Registrar administrativos
RF03	Mostrar registros
RF04	Buscar por cédula
RF05	Reemplazar información
RF06	Eliminar registros
RF07	Calcular pagos
RF08	Mostrar estadísticas
RF09	Salir
Menú principal
===== CLÍNICA SALUD TOTAL =====

1. Registrar médico
2. Registrar administrativo
3. Mostrar empleados
4. Buscar por cédula
5. Reemplazar información
6. Eliminar registro
7. Calcular pagos
8. Mostrar estadísticas
9. Salir
Programación Orientada a Objetos (POO)
Herencia

Se utilizó una clase padre llamada Empleado que contiene los atributos y métodos comunes.

Clases hijas:

Medico
Administrativo
Clase Padre
Empleado
Subclases
Medico extends Empleado
Administrativo extends Empleado

La herencia permite reutilizar código y evitar duplicación.

Encapsulamiento

Todos los atributos fueron declarados como privados (private) para proteger la información.

Ejemplo:

private String nombre;
private int edad;

El acceso se realiza mediante métodos get y set.

Ejemplo:

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}
Polimorfismo

Se aplicó polimorfismo usando:

ArrayList<Empleado>

Esto permite almacenar objetos Medico y Administrativo en una misma lista.

Ejemplo:

ArrayList<Empleado> empleados = new ArrayList<>();

Además, el método calcularPago() funciona de manera diferente en cada subclase.

Excepciones

Se implementó manejo de excepciones utilizando try-catch.

Excepciones aplicadas
Excepción	Uso
NumberFormatException	Conversión incorrecta
InputMismatchException	Datos inválidos
NullPointerException	Objetos nulos
IndexOutOfBoundsException	Índices inválidos
IllegalArgumentException	Argumentos incorrectos
Ejemplo
try {
    int edad = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("Error: ingrese números válidos.");
}
Conversiones

Se utilizaron conversiones obligatorias para transformar texto a números.

Conversión String → int
int edad = Integer.parseInt(textoEdad);
Conversión String → double
double valorHora = Double.parseDouble(textoValor);
Validaciones implementadas
Menú

No permite:

letras
números negativos
opciones fuera del rango
Edad

No permite:

letras
negativos
cero
valores mayores a 150
Cédula
No permite registros repetidos
Campos vacíos

No permite:

nombre vacío
correo vacío
especialidad vacía
departamento vacío
Correo

Debe contener:

@
.
Teléfono

Solo acepta números.

Valores numéricos

Deben ser mayores a cero:

pacientes atendidos
valor consulta
horas trabajadas
valor por hora
CRUD Implementado
CREATE

Registrar empleados.

READ

Mostrar empleados registrados.

UPDATE

Reemplazar información de empleados.

DELETE

Eliminar registros existentes.

Búsqueda

El sistema permite buscar empleados por cédula.

Si existe

Muestra toda la información.

Si no existe

Muestra:

Registro no encontrado.
Cálculo de pagos
Médico

Fórmula:

pago=numeroPacientesAtendidos×valorConsulta

Ejemplo
40 × 25 = 1000
Administrativo

Fórmula:

pago=horasTrabajadas×valorHora

Ejemplo
160 × 4.50 = 720
Estadísticas implementadas


Además, fortalece el manejo de listas, búsqueda de información y control de errores en sistemas reales.
