package amazing;
public class PaqueteOrdinario extends Paquete{
    private int costoDeEnvio;

    public PaqueteOrdinario(String direccion, int volumen, int precio, 
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
    
}
