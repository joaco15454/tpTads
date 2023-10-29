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
    	if(costoDeEnvio < 0) {
    		throw new RuntimeException("Error, el costo de envio no puede ser negativo.");
    		}
        this.costoDeEnvio = costoDeEnvio;
    }
    
    /* OPERACIONES */
    @Override
    public double costoFinal () {
    	return getPrecio() + costoDeEnvio;
    }
}
