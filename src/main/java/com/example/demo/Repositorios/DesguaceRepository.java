package com.example.demo.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entidades.Desguace;




public interface DesguaceRepository extends JpaRepository<Desguace, String> {
	
	Desguace findByUsuario(String usuario);
	Desguace findByDireccion(String direccion);
	Desguace findByEmail(String email);
	Desguace findByPassword(String password);
	Desguace findByValPassword(String valPassword);
	
	
	
}