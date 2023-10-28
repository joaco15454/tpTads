package amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Amazing {
	private String Cuit;
	private double facturacionTotalPedidosCerrados = 0;
	private HashMap<Integer, Pedido> pedidos = new HashMap<>();
	private HashMap<String, Transporte> transportes = new HashMap<>();

	public Amazing(String cuit) {
		Cuit = cuit;
	}

	public String getCuit() {
		return Cuit;
	}

	public double getFacturacionTotalPedidosCerrados() {
		return facturacionTotalPedidosCerrados;
	}

	public void setFacturacionTotalPedidosCerrados(double facturacionTotalPedidosCerrados) {
		this.facturacionTotalPedidosCerrados += facturacionTotalPedidosCerrados;
	}

	public HashMap<Integer, Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(HashMap<Integer, Pedido> pedidos) {

		this.pedidos = pedidos;
	}

	public HashMap<String, Transporte> getTransportes() {
		return transportes;
	}

	public void setTransportes(HashMap<String, Transporte> transportes) {
		this.transportes = transportes;
	}

	public void setCuit(String cuit) {
		if (cuit.length() != 11) {
			throw new RuntimeException("Error, longitud del cuit erronea");
		}
		Cuit = cuit;
	}
	// ** PARTE OPERACIONES INTERFAZ */

	/**
	 * Registra un nuevo transporte tipo Automovil en el sistema con los siguientes
	 * datos correspondiente a todo transporte:
	 * - patente,
	 * - volumen maximo de carga
	 * - valor del viaje (que cobrará a la empresa)
	 * 
	 * Además por ser Automovil se proporciona el dato:
	 * - cantidad maxima de paquetes que transporta
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una excepcion.
	 */
	/* Metodos auxiliares transportes */
	private boolean existeTransporte(String patente) {
		return transportes.get(patente) != null;
	}

	/* Fin metodos auxiliares transportes */
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		if (existeTransporte(patente)) {
			throw new RuntimeException("Error, ya hay un transporte registrado con la patente " + patente);
		}
		Transporte t = new Comun(patente, volMax, valorViaje, maxPaq);
		transportes.put(patente, t);
	}

	/**
	 * Registra un nuevo transporte tipo Utilitario en el sistema con los
	 * datos correspondientes a toda clase de transporte y ademas:
	 * 
	 * - un valor extra que cobra a la empresa si superan los 10 paquetes.
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una excepcion.
	 */
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		if (existeTransporte(patente)) {
			throw new RuntimeException("Error, ya hay un transporte registrado con la patente " + patente);
		}
		Transporte t = new Utilitario(patente, volMax, valorViaje, valorExtra);
		transportes.put(patente, t);
	}

	/**
	 * Registra un nuevo transporte tipo Camion en el sistema con los
	 * datos correspondiente a todo transporte y ademas:
	 * 
	 * - un valor adicional que se multiplica por la cantidad de paquetes que le
	 * carguen.
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una excepcion.
	 */
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if (existeTransporte(patente)) {
			throw new RuntimeException("Error, ya hay un transporte registrado con la patente " + patente);
		}
		Transporte t = new Camion(patente, volMax, valorViaje, adicXPaq);
		transportes.put(patente, t);
	}

	/**
	 * Se registra un nuevo pedido en el sistema proporcionando los siguientes
	 * datos:
	 * - el nombre del cliente que lo solicita
	 * - su dirección
	 * - su dni
	 * 
	 * El sistema le asigna un numero de pedido unico y crea un carrito de ventas
	 * vacio.
	 * Devuelve el numero de pedido asignado.
	 * 
	 */
	public int registrarPedido(String cliente, String direccion, int dni) {
		Pedido p = new Pedido(dni, direccion, cliente, dni, false);
		pedidos.put(p.getNroPedido(), p);
		System.out.println(p.getNroPedido());
		return p.getNroPedido();
	}

	/**
	 * Se registra la compra de un producto, el cual se agregara al carrito del
	 * pedido dado
	 * como un paquete de tipo ordinario.
	 * 
	 * Se ingresan los datos necesario para agregarlo:
	 * - pedido al que corresponde agregarlo
	 * - volumen del paquete a agregar
	 * - precio del producto que contiene el paquete.
	 * 
	 * Ademas por ser un paquete de tipo ordinario:
	 * - costo del envio
	 * 
	 * Si ese pedido no esta registrado en el sistema o ya está finalizado
	 * se debe generar una excepcion.
	 * 
	 * devuelve el codigo de paquete unico.
	 * 
	 */
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		Pedido p = pedidos.get(codPedido);
		if (p == null || p.isEstaCerrado()) {
			throw new RuntimeException("pedido no encontrado o cerrado");
		}
		return p.agregarProductoCarrito(volumen, precio, costoEnvio);
	}

	/**
	 * Se registra la compra de un producto que se agregara al carrito del pedido
	 * dado
	 * como paquete de tipo especial.
	 * 
	 * Se ingresan los datos necesario para agregarlo:
	 * - pedido al que corresponde agregarlo
	 * - volumen del paquete a agregar
	 * - precio del producto que contiene el paquete.
	 * 
	 * Ademas por ser un paquete de tipo especial:
	 * - porcentaje adicional (que se calcula y suma a su precio)
	 * - adicional (se suma si el paquete tiene volumen>3000)
	 * 
	 * Si ese pedido no esta registrado en el sistema o ya está finalizado
	 * se debe generar una excepcion.
	 * 
	 * devuelve el codigo de paquete unico.
	 * 
	 */
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		Pedido p = pedidos.get(codPedido);
		if (p == null || p.isEstaCerrado()) {
			throw new RuntimeException("pedido no encontrado o cerrado");
		}
		return p.agregarProductoCarrito(volumen, precio, porcentaje, adicional);
	}

	/**
	 * quita un paquete del pedido dado su codigo unico de paquete.
	 * 
	 * Devuelve true si pudo quitar el paquete.
	 * si no lo encontró o el pedido ya esta finalizado, devuelve false.
	 * 
	 * Demostrar la complejidad en terminos de O grande en el informe.
	 */
	public boolean quitarPaquete(int codPaquete) {
		Iterator<Integer> it = pedidos.keySet().iterator();

		while (it.hasNext()) {
			Integer clave = it.next();
			Pedido p = pedidos.get(clave);

			for (Integer id : p.getCarrito().keySet()) {
				Paquete pedido = p.getCarrito().get(id);
				if (pedido.getIdUnico() == codPaquete) {
					return true;
				}
				// Hacer algo con el pedido
			}

		}
		return false;
	}

	public boolean quitarPaquete2(int codPaquete) {
		for (Pedido p : pedidos.values()) {
			if (paqueteEnPedido(p, codPaquete)) {
				p.getCarrito().remove(codPaquete);
				return true;
			}
		}
		return false;
	}

	private boolean paqueteEnPedido(Pedido p, int codPaquete) {
		HashMap<Integer, Paquete> carrito = p.getCarrito();
		Paquete paquete = carrito.get(codPaquete);
		if (paquete == null) {
			return false;
		}
		return true;
	}

	/**
	 * Se registra la finalizacion de un pedido registrado en la empresa,
	 * dado su codigo.
	 * Devuelve el total a pagar por el pedido.
	 * 
	 * Si ese codigo no esta en el sistema o ya fue finalizado se debe
	 * generar una excepcion.
	 *
	 */
	public double cerrarPedido(int codPedido) {
		Pedido p = buscarPedido(codPedido);
		p.setEstaCerrado(true);
		setFacturacionTotalPedidosCerrados(p.calcularValorAPagar());
		return p.calcularValorAPagar(); // Devuelve el total a pagar pero es un void, por las dudas dejo esto aca para
										// correccion futura

	}

	/**
	 * Se solicita la carga de un transporte registrado en la empresa, dada su
	 * patente.
	 * 
	 * Devuelve un String con forma de listado donde cada renglón representa un
	 * paquete cargado.
	 * Cada renglón debe respetar el siguiente formato:
	 * " + [ NroPedido - codPaquete ] direccion"
	 * por ejemplo:
	 * " + [ 1002 - 101 ] Gutierrez 1147"
	 *
	 * Los paquetes que se cargan deben pertenecer a pedidos finaizados.
	 * Si no se encontró ningún paquete para cargar, se debe devolver un string
	 * vacio.
	 * 
	 * Si esa patente no esta en el sistema se debe generar una excepcion.
	 * 
	 */
	public String cargarTransporte(String patente) {
		if (!existeTransporte(patente)) {
			throw new RuntimeException("Error, no existe un transporte registrado con la patente " + patente);
		}
		StringBuilder sb = new StringBuilder();
		Transporte t = transportes.get(patente);
		for (Pedido p : pedidos.values()) {
			if (p.isEstaCerrado()) {
				List<String> listaPaquetesCargados = cargarPedido(t, p);
				// sb.append(listaPaquetesCargados);
				sb.append(String.join("\n", listaPaquetesCargados)).append("\n");
			}
		}
		actualizarCostoEntrega(t);
		String listadoPaquetesCargados = sb.toString();
		return listadoPaquetesCargados;
	}

	/* metodo auxiliar cargarTransporte */
	public void actualizarCostoEntrega(Transporte t) {
		if (t instanceof Camion) {
			((Camion) t).calcularCostoViaje();
		} else if (t instanceof Utilitario) {
			((Utilitario) t).calcularCostoViaje();
		}
	}

	private List<String> cargarPedido(Transporte t, Pedido pedido) {
		t.transporteEstaLleno();
		List<String> listaPaquetesCargados = new ArrayList<>();
		HashMap<Integer, Paquete> carrito = pedido.getCarrito();
		for (Paquete paquete : carrito.values()) {
			if (t.seCumplenCondiciones(paquete)) {
				t.cargarPaquete(paquete);
				listaPaquetesCargados.add(formatoEntrega(pedido, paquete));
			}
		}
		// System.out.println(listaPaquetesCargados);
		return listaPaquetesCargados;
	}

	public String formatoEntrega(Pedido pedido, Paquete paquete) {
		// String pa = "+ [ " + pedido.getNroPedido() + " - " + paquete.getIdUnico() + "
		// ] " + pedido.getDireccion() + "\n";
		StringBuilder sb = new StringBuilder();
		sb.append("+ [ ");
		sb.append(pedido.getNroPedido());
		sb.append(" - ");
		sb.append(paquete.getIdUnico());
		sb.append(" ] ");
		sb.append(pedido.getDireccion());
		sb.append("\n");
		String datosEntrega = sb.toString();
		return datosEntrega;
	}
	/* Fin metodo auxiliar cargarTransporte */

	/**
	 * Se registra el costo del viaje de un transporte dado su patente
	 * Este costo es el que cobra el transporte (a la empresa) por entregar
	 * la carga una vez que fue cargado con los paquetes.
	 * 
	 * Una vez cargado, aunque no se haya podido completar, si no hay más paquetes
	 * para agregarle, el transporte reparte los paquetes cargados.
	 * 
	 * Se devuelve el valor del viaje segun lo indicado en cada clase de transporte.
	 * Cada clase de transporte tiene su forma de calcular el costo del viaje.
	 * 
	 * Si esa patente no esta en el sistema se debe generar una excepcion.
	 * Si el transporte no esta cargado genera un excepcion.
	 * 
	 * Se debe resolver en O(1)
	 */
	public double costoEntrega(String patente) {
		if (!existeTransporte(patente)) {
			throw new RuntimeException("Error, no hay un transport registrado con la patente " + patente);
		}
		Transporte t = transportes.get(patente);
		return t.costoEntrega();
	}

	/**
	 * Devuelve la suma del precio facturado de todos los pedidos cerrados.
	 * 
	 * Se debe realizar esta operacion en O(1).
	 */
	public double facturacionTotalPedidosCerrados() {

		return getFacturacionTotalPedidosCerrados();
	}

	/**
	 * Devuelve los pedidos cerrados y que no fueron entregados en su totalidad.
	 * O sea, que tienen paquetes sin entregar.
	 * 
	 * Devuelve un diccionario cuya clave es el codigo del pedido y el valor es el
	 * nombre del
	 * cliente que lo pidio.
	 * 
	 */
	public Map<Integer, String> pedidosNoEntregados() {
		Map<Integer, String> pedidosNoEntregados = new HashMap<>();
		for (Pedido p : pedidos.values()) {
			if (p.isEstaCerrado()) {
				for (Paquete c : p.getCarrito().values()) {
					if (!c.fueEntregado() && !pedidosNoEntregados.containsKey(p.getNroPedido())) {
						pedidosNoEntregados.put(p.getNroPedido(), p.getNombreDeCliente());
					}
				}
			}
		}
		return pedidosNoEntregados;
	}

	/**
	 * Se consideran transportes identicos a 2 transportes cargados con:
	 * - distinta patente,
	 * - misma clase y
	 * - la misma carga.
	 * Se considera misma carga al tener la misma cantidad de paquetes con las
	 * mismas caracteristicas:
	 * - mismo volumen,
	 * - misma clase y
	 * - mismo precio.
	 * VER EJEMPLO EN ENUNCIADO
	 */
	public boolean hayTransportesIdenticos() {
		for (Transporte t1 : transportes.values()) {
			for (Transporte t2 : transportes.values()) {
				if (distintaPatente(t1, t2) && mismaClaseTransporte(t1, t2) && cargaIdentica(t1, t2)) {
					return true;
				}
			}
		}
		return false;
	}

	/* Metodos auxiliares transportes identicos */
	private boolean cargaIdentica(Transporte t1, Transporte t2) {
		boolean mismos = true;
		List<Paquete> primeraCarga = t1.getPaquetesCargados();
		List<Paquete> segundaCarga = t2.getPaquetesCargados();
		if (primeraCarga.size() != segundaCarga.size()) {
			return false;
		}
		for (Paquete p1 : primeraCarga) {
			boolean mismasCaracteristicas = false;
			for (Paquete p2 : segundaCarga) {
				if (mismoPrecioVolumen(p1, p2) && mismaClasePaquete(p1, p2)) {
					mismasCaracteristicas = true;
					break;
				}
			}
			mismos = mismos && mismasCaracteristicas;
		}
		return mismos;
	}

	private boolean mismoPrecioVolumen(Paquete p1, Paquete p2) {
		boolean mismoPrecio = (p1.getPrecio() == p2.getPrecio());
		boolean mismoVolumen = (p1.getVolumen() == p2.getVolumen());
		return mismoPrecio && mismoVolumen;
	}

	private boolean distintaPatente(Transporte t1, Transporte t2) {
		return t1.getPatente() != t2.getPatente();
	}

	private boolean mismaClasePaquete(Paquete p1, Paquete p2) {
		return p1.getClass() == p2.getClass();
	}

	private boolean mismaClaseTransporte(Transporte t1, Transporte t2) {
		return t1.getClass() == t2.getClass();
	}
	/* Fin metodos auxiliares transportes identicos */

	/* METODOS AUXILIARES */
	public Pedido buscarPedido(int codPedido) {
		Pedido p = pedidos.get((codPedido));

		return p;
	}

}
