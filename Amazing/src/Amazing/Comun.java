 package Amazing;

 public class Comun extends Transporte{
    
    private int limitePaquetes;
    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes) {
        super(patente, volumenMaximo, valorQueCobra);
        this.limitePaquetes = limitePaquetes;
    }
    public void setLimitePaquetes(int limitePaquetes) {
        this.limitePaquetes = limitePaquetes;
    }
    public int getLimitePaquetes() {
        return limitePaquetes;
    }

    
 }