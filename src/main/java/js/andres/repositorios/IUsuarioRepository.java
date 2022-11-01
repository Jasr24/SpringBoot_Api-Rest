package js.andres.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import js.andres.modelos.UsuarioModel;

public interface IUsuarioRepository extends JpaRepository<UsuarioModel,Integer>{
    
    public UsuarioModel findByCorreo(String correo);
    
}
