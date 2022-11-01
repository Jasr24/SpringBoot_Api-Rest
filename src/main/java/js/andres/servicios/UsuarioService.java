package js.andres.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import js.andres.modelos.UsuarioModel;
import js.andres.repositorios.IUsuarioRepository;

@Service
@Primary
public class UsuarioService {
    
    @Autowired
    private IUsuarioRepository repositorio;

    public List<UsuarioModel> listar(){
        return this.repositorio.findAll(Sort.by("id").descending());
    }

    public void guardar (UsuarioModel usuario){
        this.repositorio.save(usuario);
    }

    public UsuarioModel buscarPorCorre(String correo){
        return repositorio.findByCorreo(correo);
    }

    public void eliminar(Integer id){
        this.repositorio.deleteById(id);
    }
}
