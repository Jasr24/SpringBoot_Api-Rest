package js.andres.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import js.andres.modelos.ProductosModel;

public interface IProductoRepository extends JpaRepository<ProductosModel,Integer>{
    
}
