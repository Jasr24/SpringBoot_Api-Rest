package js.andres.seguridad;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import js.andres.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Seguridad {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //En el siguiente metodo se hace las configuraciones nesesarias para habilitar la api
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
        /*http.csrf().disable(); //deshabilitamos esto MUY IMPORTANTE ESTO
        http.authorizeHttpRequests().anyRequest().permitAll(); //Aqui habilitamos todas las peticiones que recibamos
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Aqui Deshabilitamos el filtro de csrf y le decimos que trabajaremos con una politica de secion estandar.. al deshabilitar esto podemos usar los form-data ESTO LO USAMOS PARA UNA CONFIGURACION SENCILLA*/
        
        http.csrf().disable(); //deshabilitamos esto MUY IMPORTANTE ESTO
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Aqui Deshabilitamos el filtro de csrf y le decimos que trabajaremos con una politica de secion estandar.. al deshabilitar esto podemos usar los form-data 
        http.exceptionHandling()
            .authenticationEntryPoint((request, response, ex) -> {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            });

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests()
            .antMatchers("/api/v1/login")
            .permitAll()
            .anyRequest()
            .authenticated();
        
        return http.build();
    }
}
