package com.example.demo.Entidades;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUsuario;
	
	
	
	private ArrayList<Producto> listaFavoritos;
	
	public Usuario() {
		listaFavoritos = new ArrayList<>();
	}
	
	
	public void aniadirALaLista(Producto p) {
		listaFavoritos.add(p);
	}
	
	public ArrayList<Producto> sacarLaLista() {
		return listaFavoritos;
	}
}