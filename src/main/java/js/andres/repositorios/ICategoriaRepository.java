package js.andres.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import js.andres.modelos.CategoriaModel;

public interface ICategoriaRepository extends JpaRepository<CategoriaModel, Integer>{
    

}
