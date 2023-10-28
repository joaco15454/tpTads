package amazing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Comun extends Transporte {

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

    @Override
    public List<String> cargarPedido(Pedido pedido) {
        List<String> listaPaquetesCargados = new ArrayList<>();
        if (pedido.isEstaCerrado() && !transporteLleno()) {
            HashMap<Integer, Paquete> carrito = pedido.getCarrito();
            for (Paquete paquete : carrito.values()) {
                if (paquete instanceof PaqueteOrdinario && paquete.getVolumen() < 2000) {
                    cargarPaquete(paquete);
                    StringBuilder sb = new StringBuilder();
                    sb.append("+ [");
                    sb.append(pedido.getNroPedido());
                    sb.append(" - ");
                    sb.append(paquete.getIdUnico());
                    sb.append(" ] ");
                    sb.append(pedido.getDireccion());
                    String paqueteCargado = sb.toString();
                    listaPaquetesCargados.add(paqueteCargado);
                    carrito.remove(paquete.getIdUnico());
                }
            }
        }
        return listaPaquetesCargados;
    }

    @Override
    public boolean transporteLleno() {
        return ((paquetesCargados.size() == getLimitePaquetes()) || (getVolumenActual() == getVolumenMaximo()));
    }
}