package js.andres.modelos;

public class EjemploModel {
    
    private String nombre;
    private String correo;
    private Integer precio;
    private String descripcion;
    
    public EjemploModel() {
    }

    public EjemploModel(String nombre, String correo, Integer precio, String descripcion) {
        this.nombre = nombre;
        this.correo = correo;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Integer getPrecio() {
        return precio;
    }
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
