package js.andres.Controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import js.andres.modelos.EjemploModel;
import js.andres.utilidades.Utilidades;

@RestController
@RequestMapping("/api/v1")
public class ResponseEntityController {
    
    @GetMapping("/response-entity")
    public ResponseEntity<String> metodo_get(){
        return ResponseEntity.ok("Método GET con Response Entity");  //ok retorna el estado 200
    }

    //Get con parametro
    @GetMapping("/response-entity/{id}")
    public ResponseEntity<String> metodo_get_parametro(@PathVariable("id") String id){
        return ResponseEntity.ok("Método GET con Response Entity y parametro = " + id);  //ok retorna el estado 200
    }

    //Ejemplo de metod get con un responseEntity personalizado (NOTA: este ejemplo lo podemos hacer con cualquie anotacion...get.post.put.delete etc)
    @GetMapping("/response-entity-personalizado")
    public ResponseEntity<Object> metodoGetPersonalizado(){
        return Utilidades.generateResponse(HttpStatus.OK, "ResponseEntity personalizado");
    }

    @PostMapping("/response-entity")
    public ResponseEntity<String> metodo_post(){
        return ResponseEntity.ok("Método POST con Response Entity");  //ok retorna el estado 200
    }

    //Post con cuerpo
    @PostMapping("/response-entity-json")
    public ResponseEntity<String> metodo_post_json(@RequestBody EjemploModel ejemploModel){
        return ResponseEntity.ok("Método POST con Response Entity y un Json :" + "nombre = " + ejemploModel.getNombre() + " | correo = " + ejemploModel.getCorreo());  //ok retorna el estado 200
    }

    @PutMapping("/response-entity")
    public ResponseEntity<String> metodo_put(){
        return ResponseEntity.ok("Método PUT con Response Entity");  //ok retorna el estado 200
    }

    @DeleteMapping("/response-entity")
    public ResponseEntity<String> metodo_delete(){
        return ResponseEntity.ok("Método DELETE con Response Entity");  //ok retorna el estado 200
    }
}
