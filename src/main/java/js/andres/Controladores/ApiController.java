package js.andres.Controladores;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import js.andres.modelos.EjemploModel;

@RestController //Para las apí se usa RestController
@RequestMapping("api/v1")
public class ApiController {

    @GetMapping("/metodo")
    public String metodo_get(){

        return "Método GET";
    }

    //Metodo get con parametro
    @GetMapping("/metodo/{id}")
    public String metodo_get_parametro(@PathVariable("id") String id){

        return "metodo Get con Parámetros = " + id;
    }

    @PostMapping("/metodo")
    public String metodo_post(){

        return "Método POST";
    }

    //metodo post con parametros de una Clase, entidad o como quieras llamarlo... (NOTA ES LO QUE LLEGA DE PARTE DEL CLIENTE.. YA SEA POSTMAN EN EL BODY O UN FORMULARIO EN UN NAVEGADOR OOO)
    @PostMapping("/metodo-json")
    public String metodo_post_json(@RequestBody EjemploModel ejemploModel){

        return "nombre = " + ejemploModel.getNombre() + " | correo = " + ejemploModel.getCorreo();
    }
    
    @PutMapping("/metodo")
    public String metodo_put(){

        return "Método PUT";
    }

    @DeleteMapping("/metodo")
    public String metodo_delete(){

        return "Método DELETE";
    }
}
