package clases;

import java.util.ArrayList;

public class Pedido {
    private static Pedido instance = null;
    private ArrayList<Producto> productos;

    private Pedido() {
        this.productos = new ArrayList<>();
    }

    public static Pedido getInstance() {
        if (instance == null) {
            instance = new Pedido();
        }
        return instance;
    }

    //ESTO SIRVE PARA AGREGAR PRODUCTOS AL PEDIDO
    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    //ESTO SIRVE PARA  OBTENER LOS PRODUCTOS DEL PEDIDO
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    //ESTO SIRVE PARA ELIMINAR PRODUCTOS DEL PEDIDO
    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }
}