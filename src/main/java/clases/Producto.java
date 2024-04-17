package clases;

public class Producto {
    private Integer id;
    private String tipo;
    private String nombre;
    private Consultor consultor;


    public Producto(Integer id, String tipo, String nombre) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return null;
    }

    public String getConsultorNombre() {
    return this.consultor.getNombre();
}

    public void setConsultor(Consultor consultor) {
        this.consultor = consultor;
    }

    public Consultor getConsultor() {
        return this.consultor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}