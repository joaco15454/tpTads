package amazing;
public class PaqueteOrdinario extends Paquete{
    private int costoDeEnvio;

    public PaqueteOrdinario(String direccion, int volumen, double precio, int costoDeEnvio) {
        super( volumen, precio);
        this.costoDeEnvio = costoDeEnvio;
    }
    
    /* OPERACIONES */
    @Override
    public double costoFinal () {
    	return super.precio + costoDeEnvio;
    }
}
