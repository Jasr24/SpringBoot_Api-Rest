package js.andres.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import js.andres.jwt.AuthRequest;
import js.andres.jwt.AuthResponse;
import js.andres.jwt.JwtTokenUtil;
import js.andres.modelos.UsuarioModel;
import js.andres.servicios.UsuarioService;

@RestController
@RequestMapping("/api/v1")
public class AccesoController {

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;



    /* Lo siguiente es el RequestBody
        {
            "correo" : "info@tamila.cl",
            "password" : "123456"
        }
     */
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthRequest request){

        try{

            //la siguiente linea es la que hace la magia de la autenticacion
            Authentication authentication = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getPassword()));
            System.out.println(authentication);

            //Creamos el token
            UsuarioModel usuario = this.usuarioService.buscarPorCorre(request.getCorreo());
            String accessToken = this.jwtUtil.generarToken(usuario);

            AuthResponse response = new AuthResponse(request.getCorreo(), accessToken);

            return ResponseEntity.ok(response);

        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

}
