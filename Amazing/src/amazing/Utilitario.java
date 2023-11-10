package amazing;

public class Utilitario extends Transporte {

    private double valorExtra;

    public Utilitario(String patente, int volumenMaximo, double valorQueCobra, double valorExtra) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorExtra = valorExtra;
    }

    public double obtenerValorExtra() {
        return valorExtra;
    }

	@Override
	public void actualizarCostoEntrega() {
		double costo = paquetesCargados.size() > 3 ? (obtenerValorQueCobra() + obtenerValorExtra()) : obtenerValorQueCobra();
        this.valorQueCobra = costo;
	}
}
