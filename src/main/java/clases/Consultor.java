package clases;

import java.util.ArrayList;

public class Consultor {
    private String id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String direccion;
    private String email;
    private String password;

    private static ArrayList<Consultor> consultores = new ArrayList<>();


    public Consultor(String id, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String direccion, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        consultores.add(this);

    }

    public static Consultor getConsultorPorNombre(String nombre) {
    for (Consultor consultor : consultores) {
        if (consultor.getNombre().equals(nombre)) {
            return consultor;
        }
    }
    return null;
}

    public static ArrayList<Consultor> getConsultores() {
        return consultores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void setConsultores(ArrayList<Consultor> consultores) {
        Consultor.consultores = consultores;
    }

    public static void agregarConsultoresPrueba() {
    new Consultor("1", "Juan", "Perez", "Lopez", "123456789", "Direccion 1", "juan@mail.com", "password1");
    new Consultor("2", "Maria", "Gomez", "Rodriguez", "987654321", "Direccion 2", "maria@mail.com", "password2");
    new Consultor("3", "Carlos", "Martinez", "Gonzalez", "456789123", "Direccion 3", "carlos@mail.com", "password3");
    new Consultor("4", "Ana", "Fernandez", "Ramirez", "789123456", "Direccion 4", "ana@mail.com", "password4");
    new Consultor("5", "Pedro", "Castillo", "Morales", "321654987", "Direccion 5", "pedro@mail.com", "password5");
}

    @Override
    // metodo para imprimir los datos del consultor en terminal
    public String toString() {
        return "Consultor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public static void actualizarConsultor(Consultor consultorActualizado) {
    for (int i = 0; i < consultores.size(); i++) {
        if (consultores.get(i).getId().equals(consultorActualizado.getId())) {
            consultores.set(i, consultorActualizado);
            break;
        }
    }
}


        public static void eliminarConsultor(Consultor consultor) {
            consultores.remove(consultor);
        }



}
