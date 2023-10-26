package Amazing.Amazing;

import java.util.HashMap;
import java.util.Iterator;



public class Pedido {
    private int dni;
    private int nroPedido;
    private String direccion;
    private String nombreDeCliente;
    private HashMap<Integer,Paquete> carrito;

    /* CONSTRUCTOR  */
    public Pedido(int nroPedido, String direccion, String nombreDeCliente, HashMap<Integer, Paquete> carrito, int dni) {
        this.nroPedido = nroPedido;
        this.direccion = direccion;
        this.nombreDeCliente = nombreDeCliente;
        this.carrito = carrito;
        this.dni = dni;
    }
    /* FIN CONSTRUCTOR */
    /* GETTERS AND SETTERS */
     public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    public int getNroPedido() {
        return nroPedido;
    }
    public void setNroPedido(int nroPedido) {
        if (nroPedido < 0) {
            throw new RuntimeException("Error, el numero tiene que ser mayor a 0");
        }
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
        if (hayNumeroEnString(nombreDeCliente)) {
                        throw new RuntimeException("Error, el nombre tiene que ser valido");

        }
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
    protected  void agregarProductoCarrito(int id, int volumen, double precio) {
        /*COMPLETAR */
         if (paqueteEnCarrito(id)) {
            throw new RuntimeException("Error, ya existe un paquete con esa id");
         }
        Paquete p = new Paquete(id,volumen,precio,getDireccion(),false);
        carrito.put(id, p);
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

    /* METODOS AUXILIARES  */
    public boolean hayNumeroEnString(String s) // valdicion para que sea un string de letras y no variado, por ejemplo jo4aquin
    {     

        for( int i = 0; i < s.length(); i++ )
            if( Character.isDigit( s.charAt( i ) ) )
                return false;
        return true;
    }

   
}
