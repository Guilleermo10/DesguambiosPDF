package com.example.demo.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idComentario;

	
	private String contenido;
	
	public Comentario () {}
	
	
	public Comentario (String contenido) {
		this.contenido = contenido;
	}

public long getIdComentario() {
	return idComentario;
}
	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}



	@Override
	public String toString() {
		return " Comentario [idComentario = " + idComentario + ", contenido = " + contenido + "]";
	}
	
	
	
	
}
