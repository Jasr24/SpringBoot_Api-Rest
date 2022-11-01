package js.andres.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import js.andres.modelos.CategoriaModel;
import js.andres.modelos.ProductosModel;
import js.andres.servicios.CategoriaService;
import js.andres.servicios.ProductoService;
import js.andres.utilidades.Contantes;
//import js.andres.servicios.UsuarioService;
import js.andres.utilidades.Utilidades;

@RestController
@RequestMapping("/api/v1")
public class BdController {
    
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    /*@Autowired
    private UsuarioService usuarioService;*/
    //Respponse Entity para lo transsacional --- para crear - editar o eliminar
    //List para lista


    ////////////////////// Categorias //////////////////////

    @GetMapping("/categorias")
    public List<CategoriaModel> categorias(){
        return this.categoriaService.listar();
    }

    @GetMapping("categorias/{id}")
    public CategoriaModel buscarPorCategoria(@PathVariable("id") Integer id){
        return this.categoriaService.buscarPorId(id);
    }

    @PostMapping("/categorias")
    public ResponseEntity<Object> categoria_post_crear_categoria(@RequestBody CategoriaModel request){ //lo pasamos de tipo objet para pasar nuestro responseEntity personalizado
        
        request.setSlug(Utilidades.getSlug(request.getNombre()));  //Pa que esto funcione debemos enviar el slug vacion en el RequestBody
        this.categoriaService.guardar(request);
        return Utilidades.generateResponse(HttpStatus.CREATED, "Se creo el registro exitosamente"); //CREATED estado 201
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Object> categorias_put_editar(@PathVariable("id") Integer id, @RequestBody CategoriaModel request){

        CategoriaModel categoria = this.categoriaService.buscarPorId(id);

        categoria.setNombre(request.getNombre());
        categoria.setSlug(Utilidades.getSlug(request.getNombre()));

        this.categoriaService.guardar(categoria);

        return Utilidades.generateResponse(HttpStatus.OK,"Se ha editado la categoria exitosamente");
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Object> categoria_delete_eliminar(@PathVariable("id") Integer id){
        
        //SE RECOMIENDA PRIMERO CREAR UN .. PARA PREGUNTAR SI LA CATEGORIA EXISTE O NO EN LA TABLA ANTES DE BORRARLA.

        try {
            this.categoriaService.eliminar(id);
            return Utilidades.generateResponse(HttpStatus.OK, "Se ha eliminado la categoria exitosamente");
        } catch (Exception e){

            //Si se hace sin crear el metodo que mencione arriba. si intenta eliminar algo que no existe retornara esto que sigue
            return Utilidades.generateResponse(HttpStatus.BAD_REQUEST, "Fallo la ejecucion, por favor vuelva a intentarlo mas tarde");
        }
    }
    


    ////////////////////// Productos //////////////////////


    @GetMapping("/productos")
    public List<ProductosModel> productos(){
        return this.productoService.listar();
    }

    /*@GetMapping("/productos/{id}")
    public ProductosModel buscarPorProducto(@PathVariable("id") Integer id){
        return this.productoService.buscarPorId(id);
    }*/

            //Se envia a traves de un form data --- formulario.. no de un json.. ya que le estamos enviando una imagen desde el pc fuera del proyecto
            //NOTA GENERAL:  podemos tener muchos @RequestParam y hacer con ellos lo que deseamos
    @PostMapping("/productos")
    public ResponseEntity<Object> productos_post_crear(ProductosModel producto, @RequestParam("file") MultipartFile file){ //Aquino le pusimos la anotacion requestBody ya que no viene como un json.. si no como un formulario

        HttpStatus status = HttpStatus.OK;
        String mensaje = "";

        if(!file.isEmpty()){
            String nombreImagen = Utilidades.guardarArchivo(file, Contantes.RUTA_UPLOAD);
            if(nombreImagen == "no"){
                status = HttpStatus.BAD_REQUEST; //estado 400
                mensaje = "La imagen enviada no es valida debe de ser JPG|PNG|JPEG";
            }else{
                if(nombreImagen !=null){
                    producto.setFoto(nombreImagen);
                    producto.setSlug(Utilidades.getSlug(producto.getNombre()));

                    this.productoService.guardar(producto);
                    status = HttpStatus.CREATED; //estdo 201
                    mensaje = "Se creo el registro exitoxamente";
                }
            }
        } else {
            status = HttpStatus.BAD_REQUEST; //estado 400
            mensaje = "La imagen enviada no es valida debe de ser JPG|PNG|JPEG";
        }
        return Utilidades.generateResponse(status, mensaje);
    }
}
