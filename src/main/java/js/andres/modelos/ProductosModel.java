package js.andres.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class ProductosModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String slug;
    private String descripcion;
    private Integer precio;
    private String foto;

    @OneToOne
    @JoinColumn(name = "categoria_id")  //esta linea para que haga la conexion a la otra tabla.. esta conextada a categoria_id
    private CategoriaModel categoriaId;

    public ProductosModel() {

    }
    
    public ProductosModel(String nombre, String slug, String descripcion, Integer precio, String foto,
            CategoriaModel categoriaId) {
        this.nombre = nombre;
        this.slug = slug;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.categoriaId = categoriaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public CategoriaModel getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(CategoriaModel categoriaId) {
        this.categoriaId = categoriaId;
    }
}
