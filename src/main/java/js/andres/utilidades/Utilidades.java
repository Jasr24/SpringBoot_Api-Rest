package js.andres.utilidades;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class Utilidades {
    
    // ESTOS METODOS LO PODREMOS USAR EN CUALQUIER APLICACION YA QUE SON BIEN GENERICO
    
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

    public static String guardarArchivo(MultipartFile multiPart, String ruta){

        if(Utilidades.validaImagen(multiPart.getContentType()) == false){
            return "no";
        } else {
            
            long time = System.currentTimeMillis();
            String nombre = time+Utilidades.getExtension(multiPart.getContentType());

            try {
                File imageFile = new File(ruta+nombre);
                multiPart.transferTo(imageFile);;
                return nombre;
            } catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }
    }

    public static boolean validaImagen (String mime){
        boolean retorno = false;
        switch(mime){
            case "image/jpeg":
                retorno = true;
                break;
            case "image/jpg":
                retorno = true;
                break;
            case "image/png":
                retorno = true;
                break;
            default:
                retorno = false;
                break;
        }
        return retorno;
    }
 
    public static String getExtension (String mime){
        String retorno = "";
        switch (mime){
            case "image/jpeg":
                retorno = ".jpeg";
                break;
            case "image/jpg":
                retorno =".jpg";
                break;
            case "image/png":
                retorno =".png";
                break;
        }
        return retorno;
    }

    public static String getSlug(String input){
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = EDGESDHASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }


    //Metodo para personalizar el ResponseEntity
    //ResponseEntity<Object> de tipo objeto para que reciba lo que sea.... HttpStatus para retornar el estado que nesesitemos
    public static ResponseEntity<Object> generateResponse(HttpStatus status, String mensaje){

        Map<String, Object> map = new HashMap<>();

        try{
            map.put("fecha", new Date());
            map.put("status", status.value());
            map.put("mensaje", mensaje);
            //Y podemos retornar lo que queramos
            return new ResponseEntity<>(map,status);
        }catch (Exception e){
            map.clear();
            map.put("fecha", new Date());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("mensaje", e.getMessage());
            //Y podemos retornar lo que queramos
            return new ResponseEntity<>(map,status);
        }
    }
}
