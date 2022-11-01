package js.andres.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import js.andres.utilidades.Contantes;

//ESTA CLASE ES PARA EL UPLOAD DE ARCHIVOS

@Configuration
public class Configuracion implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file: " + Contantes.RUTA_UPLOAD);
    }
}
