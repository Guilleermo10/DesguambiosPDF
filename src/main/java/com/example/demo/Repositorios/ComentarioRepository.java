package com.example.demo.Repositorios;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entidades.Comentario;


public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	Comentario findByIdComentario(long idComentario);
	List<Comentario> findByContenido(String contenido);
	

	@Transactional
	@Modifying
	@Query(
			value = "UPDATE `desguambios`.`comentario` SET `contenido`=:contenido  WHERE `id_Comentario`=:idComentario", nativeQuery = true)
	void updateComentario(@Param("contenido")String contenido, @Param("idComentario") long idComentario);

	
	
	
}

