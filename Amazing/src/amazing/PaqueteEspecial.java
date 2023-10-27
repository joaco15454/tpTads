package amazing;
public class PaqueteEspecial extends Paquete{
    private int porcentajeAdicional;
    private int valorAdicional;
    public PaqueteEspecial(String direccion, int volumen, double precio,
            int porcentajeAdicional, int valorAdicional) {
        super(direccion, volumen, precio);
        this.porcentajeAdicional = porcentajeAdicional;
        this.valorAdicional = valorAdicional;
    }
    public int getPorcentajeAdicional() {
        return porcentajeAdicional;
    }
    public void setPorcentajeAdicional(int porcentajeAdicional) {
        this.porcentajeAdicional = porcentajeAdicional;
    }
    public int getValorAdicional() {
        return valorAdicional;
    }
    public void setValorAdicional(int valorAdicional) {
        this.valorAdicional = valorAdicional;
    }
    /* OPERACIONES */
    public void seSuperaElVolumen(int volumen){
        if(volumen > 3000) {
            setValorAdicional(400);
        }
        else if (volumen > 5000) {
            setValorAdicional(800);
        }    
    }
}
