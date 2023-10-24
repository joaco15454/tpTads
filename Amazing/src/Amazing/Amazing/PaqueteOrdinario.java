package Amazing;

public class PaqueteOrdinario extends Paquete{
    
    public PaqueteOrdinario(int idUnico, int volumen, double costoEnvio, String direccion, Boolean entregado) {
        super(idUnico, volumen, costoEnvio, direccion, entregado);
        //TODO Auto-generated constructor stub
    }

    private double costoEnvio;

    public double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    
}