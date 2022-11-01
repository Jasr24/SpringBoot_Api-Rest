package js.andres.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import js.andres.modelos.UsuarioModel;
import js.andres.utilidades.Contantes;

@Component
public class JwtTokenUtil {
    
    private static final long EXPIRE_DURATION = 24*60*60*1000; //24 horas.. 1 dia. .. esto esta en Currentime osea en milisegundos
    

    //Este metodo valida el token que se le pasa(Aver si le sirve o no, si expiro o no)
    public boolean validateAccessToken(String token){
        try{
            Jwts.parser().setSigningKey(Contantes.FIRMA).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex){
            System.out.println("JWT expirado " + ex.getMessage());
        }catch (IllegalArgumentException ex){
            System.out.println("Token es null, está vacío o contiene espacion " + ex.getMessage());
        }catch (MalformedJwtException ex){
            System.out.println("JWT es invalido " + ex.getMessage());
        }catch (UnsupportedJwtException ex){
            System.out.println("JWT no soportado " + ex.getMessage());
        }catch (SignatureException ex){
            System.out.println("Validacion de firma erróne");
        }

        return false;
    }


    //EL SIGUIENTE METODO GENERA EL TOKEN
    public String generarToken(UsuarioModel usuario){
        return Jwts.builder()
                .setSubject(String.format("%s,%s", usuario.getId(),usuario.getCorreo())) //en el setSubject se puede poner manzana o lo que sea.. pero lo que isimos es un estandar como lo dice la documentacion.
                .setIssuer("jasr24") //un valor asocioado al procolo
                .setIssuedAt(new Date()) //le pasamos la fecha actual
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION)) //le pasamos la espiracion del token
                .signWith(SignatureAlgorithm.HS512, Contantes.FIRMA) //hay varios algoritmos que puedes pasar consultelo en jwt.io
                .compact();                
    }       



    //LOS DOS METODOS SIGUIENTES ES PARA FORMATEAR EL TOKEN
    
    //Este  metodo es para midificar uno de los campos especificos que da problemas en al configuracion de los token
    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    //Esto es para formatear el token. 
    private Claims parseClaims( String token){
        return Jwts.parser()
                .setSigningKey(Contantes.FIRMA)
                .parseClaimsJws(token)
                .getBody();
    }
}
