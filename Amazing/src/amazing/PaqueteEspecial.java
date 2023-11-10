package amazing;
public class PaqueteEspecial extends Paquete{
    private double porcentajeAdicional;
    private int valorAdicional;
    public PaqueteEspecial(String direccion, int volumen, double precio,
            double porcentajeAdicional, int valorAdicional) {
        super( volumen, precio);
        this.porcentajeAdicional = porcentajeAdicional;
        this.valorAdicional = valorAdicional;
    }
    public double obtenerPorcentajeAdicional() {
        return porcentajeAdicional/100;
    }


    
    protected int adicionalAPagar(){
        int valorAdicionalAPagar = 0;
        if(super.volumen>= 3000 && super.volumen < 5000) {
            valorAdicionalAPagar=(valorAdicional);
        }
        else if (super.volumen > 5000) {
            valorAdicionalAPagar=(valorAdicional);
        }
        return valorAdicionalAPagar;
    }
    @Override
    public double costoFinal(){
        double precioBaseConPorcentaje = super.precio + (super.precio * (obtenerPorcentajeAdicional()));
        precioBaseConPorcentaje += adicionalAPagar();
        return precioBaseConPorcentaje;
    }
}