package Modelo;

public class Administrativo extends Empleado {
    private String departamento;
    private int horasTrabajadas;
    private double valorHora;

    public Administrativo(String cedula, String nombre, int edad, String telefono, String correo,
                          String departamento, int horasTrabajadas, double valorHora) {
        super(cedula, nombre, edad, telefono, correo);
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularPago() {
        return horasTrabajadas * valorHora;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Departamento: " + departamento);
        System.out.println("Horas Trabajadas: " + horasTrabajadas);
        System.out.println("Valor Hora: $" + valorHora);
        System.out.println("Pago Total: $" + calcularPago());
    }
    public String getDepartamento() {
        return departamento; }
    public void setDepartamento(String departamento) {
        this.departamento = departamento; }

    public int getHorasTrabajadas() {
        return horasTrabajadas; }
    public void setHorasTrabajadas(int h) {
        this.horasTrabajadas = h; }

    public double getValorHora() {
        return valorHora; }
    public void setValorHora(double v) {
        this.valorHora = v; }
}