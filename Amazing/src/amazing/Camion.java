package amazing;

public class Camion extends Transporte {

    private double valorAdicional;

    public Camion(String patente, int volumenMaximo, double valorQueCobra, double valorAdicional) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorAdicional = valorAdicional;
    }

    public double obtenerValorAdicional() {
        return valorAdicional;
    }

    @Override
    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteEspecial) && !transporteLleno() && !obtenerPaquetesCargados().contains(p) && p.obtenerVolumen() > 2000;
    }

	@Override
	public void actualizarCostoEntrega() {
		   double costo = obtenerValorQueCobra() + (paquetesCargados.size() * obtenerValorAdicional());
	       this.valorQueCobra = costo;
	}
}
