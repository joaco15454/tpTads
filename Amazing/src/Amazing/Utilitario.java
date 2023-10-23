package Amazing;

public class Utilitario extends Transporte {
    private double valorExtra;

    public Utilitario(String patente, int volumenMaximo, double valorQueCobra, double valorExtra) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorExtra = valorExtra;
    }

    public double getValorExtra() {
        return valorExtra;
    }

    public void setValorExtra(double valorExtra) {
        this.valorExtra = valorExtra;
    }
}
