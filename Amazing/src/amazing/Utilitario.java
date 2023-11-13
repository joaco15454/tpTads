package amazing;

public class Utilitario extends Transporte {

    protected double valorExtra;

    public Utilitario(String patente, int volumenMaximo, double valorQueCobra, double valorExtra) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorExtra = valorExtra;
    }

	@Override
	public void actualizarCostoEntrega() {
		double costo = paquetesCargados.size() > 3 ? (super.valorQueCobra + valorExtra) : super.valorQueCobra;
        this.valorQueCobra = costo; 
	}
}
