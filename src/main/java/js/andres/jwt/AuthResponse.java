package js.andres.jwt;

//ESTE MODELO ES PARA SETEAR PARA PARCEAR LA ESTRUCTURA  DE NUESTRO TOKEN, UN POCO LA RESPUESTA CUANDO EL USUARIO SE LOGUEE

public class AuthResponse {
    
    private String correo;
    private String accessToken;

    public AuthResponse() {

    }

    public AuthResponse(String correo, String accessToken) {
        this.correo = correo;
        this.accessToken = accessToken;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
