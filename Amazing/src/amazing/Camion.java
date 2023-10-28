package amazing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Camion extends Transporte {

    private double valorAdicional;

    public Camion(String patente, int volumenMaximo, double valorQueCobra, double valorAdicional) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorAdicional = valorAdicional;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    @Override
    public List<String> cargarPedido(Pedido pedido) {
        List<String> listaPaquetesCargados = new ArrayList<>();
        if (pedido.isEstaCerrado() && !transporteLleno()) {
            HashMap<Integer, Paquete> carrito = pedido.getCarrito();
            for (Paquete paquete : carrito.values()) {
                if (paquete instanceof PaqueteEspecial && paquete.getVolumen() > 2000) {
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

    public void costoViaje() {
        double costo = getValorQueCobra() + (paquetesCargados.size() * getValorAdicional());
        setValorQueCobra(costo);
    }

}