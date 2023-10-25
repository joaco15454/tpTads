package Amazing;
public class PaqueteOrdinario extends Paquete{
    private int costoDeEnvio;

    public PaqueteOrdinario(int idUnico, int volumen, double precio, String direccion, Boolean entregado,
            int costoDeEnvio) {
        super(idUnico, volumen, precio, direccion, entregado);
        this.costoDeEnvio = costoDeEnvio;
    }

    public int getCostoDeEnvio() {
        return costoDeEnvio;
    }

    public void setCostoDeEnvio(int costoDeEnvio) {
        this.costoDeEnvio = costoDeEnvio;
    }
    
    /* OPERACIONES */
    
}
