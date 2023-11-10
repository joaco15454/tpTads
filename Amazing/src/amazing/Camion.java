package amazing;

public class Camion extends Transporte {

    protected double valorAdicional;

    public Camion(String patente, int volumenMaximo, double valorQueCobra, double valorAdicional) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorAdicional = valorAdicional;
    }

    @Override
    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteEspecial) && !transporteLleno() && !obtenerPaquetesCargados().contains(p) && p.obtenerVolumen() > 2000;
    }

	@Override
	public void actualizarCostoEntrega() {
		   double costo = obtenerValorQueCobra() + (paquetesCargados.size() * valorAdicional);
	       this.valorQueCobra = costo;
	}
}
