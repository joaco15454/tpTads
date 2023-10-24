package Amazing;

public class PaqueteEspecial extends Paquete{

    private double costo;
    public PaqueteEspecial(int idUnico, int volumen, double costo, String direccion, Boolean entregado) {
        super(idUnico, volumen, costo, direccion, entregado);
        //TODO Auto-generated constructor stub
    }

    public void calcularNuevoPrecio(){
        
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}