package amazing;
public class PaqueteOrdinario extends Paquete{
    private int costoDeEnvio;

    public PaqueteOrdinario(String direccion, int volumen, double precio, 
            int costoDeEnvio) {
        super( direccion, volumen, precio);
        this.costoDeEnvio = costoDeEnvio;
    }

    public int getCostoDeEnvio() {
        return this.costoDeEnvio;
    }

    public void setCostoDeEnvio(int costoDeEnvio) {
        this.costoDeEnvio = costoDeEnvio;
    }
    
    /* OPERACIONES */
    @Override
    public double costoFinal () {
    	return getPrecio() + costoDeEnvio;
    }
}
