

package com.example.demo.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entidades.Producto;



public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	Producto findByIdProducto (Long idProducto);
	//Producto findByLitProducto (String litProducto);
	List<Producto> findByLitProductoIgnoreCase(String litProducto);
	//Producto findByDir_empresa (String dir_empresa);
	List<Producto> findByUsuario (String usuario);
	//Producto findById_marca (String id_marca);
	List<Producto> findByLitProductoContainingIgnoreCaseAndIdMarcaContainingIgnoreCase(String litProducto, String idMarca);
	List<Producto> findByIdMarcaIgnoreCase(String idMarca);
	
	
	
	
	@Transactional
	@Modifying
	@Query(
			value = "UPDATE `desguambios`.`producto` SET `dir_empresa`=:direccionpropietario, `id_marca`=:id_marca , `lit_producto`=:litProducto, `usuario`=:nombredesguacepropietario,  WHERE `id_producto`=:idProducto", nativeQuery = true)
	void updateProducto(@Param("litProducto")String litProducto, @Param("direccionpropietario")String direccionpropietario, @Param("nombredesguacepropietario") String nombredesguacepropietario,@Param("id_marca")String id_marca,@Param("idProducto") long idProducto);

	
	
	
}