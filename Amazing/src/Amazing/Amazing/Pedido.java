package Amazing;

import java.util.HashMap;
import java.util.Iterator;



public class Pedido {
    private int nroPedido;
    private String direccion;
    private String nombreDeCliente;
    private HashMap<Integer,Paquete> carrito;

    /* CONSTRUCTOR  */
    public Pedido(int nroPedido, String direccion, String nombreDeCliente, HashMap<Integer, Paquete> carrito) {
        this.nroPedido = nroPedido;
        this.direccion = direccion;
        this.nombreDeCliente = nombreDeCliente;
        this.carrito = carrito;
    }
    /* FIN CONSTRUCTOR */
    /* GETTERS AND SETTERS */
    public int getNroPedido() {
        return nroPedido;
    }
    public void setNroPedido(int nroPedido) {
        this.nroPedido = nroPedido;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNombreDeCliente() {
        return nombreDeCliente;
    }
    public void setNombreDeCliente(String nombreDeCliente) {
        this.nombreDeCliente = nombreDeCliente;
    }
    public HashMap<Integer, Paquete> getCarrito() {
        return carrito;
    }
    public void setCarrito(HashMap<Integer, Paquete> carrito) {
        this.carrito = carrito;
    }
    /* FIN GETTERS AND SETTERS */
    /* OPERACIONES */
    protected  void agregarProductoCarrito(int id, int volumen, int precio) {
        /*COMPLETAR */
    }
    protected  void eliminarProductoCarrito (int id) {
        
        if (!paqueteEnCarrito(id)) {
            throw new RuntimeException("Error, el paquete que desea borrar no esta en el carrito");
        }
        carrito.remove(id);
    }
    public double calcularValorAPagar() {
        /*COMPLETAR */
        double valor = 0.0;
        Iterator <Integer> it = carrito.keySet().iterator();
        while (it.hasNext()) {
            int id = it.next();
            valor+= calcularValorPaquete(id);
        }
        return valor;
    }
    public boolean paqueteEnCarrito (int id) {
        return obtenerPaquete(id) == null ? false: true;
        
    }
    protected  boolean paqueteEntregado(int id) {
        return obtenerPaquete(id).getEntregado();
    }
    protected  double calcularValorPaquete(int id) {
        return obtenerPaquete(id).getPrecio();
    }
    public Paquete obtenerPaquete(int id) {
        return carrito.get(id);
    }
    /* FIN OPERACIONES */
}
