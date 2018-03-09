package com.example.demo.Entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Component
@SessionScope
@Entity
public class Desguace {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String usuario;
	
	private String passwordHash;
	
	
	
	
	private String email;
	private String direccion;
	private String password;
	private String valPassword;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Producto> productos;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Comentario> comentarios;
	
	public Desguace() {
		
	}
	
	public Desguace(String usuario, String email, String direccion, String password
			, String valPassword, String... roles) {
		this.usuario = usuario;
		this.email = email;
		this.direccion = direccion;
		this.password = password;
		this.valPassword = valPassword;
		//this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValPassword() {
		return valPassword;
	}
	public void setValPassword(String valPassword) {
		this.valPassword = valPassword;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Desguace [usuario=" + usuario + ", email=" + email + ", direccion=" + direccion + ", password="
				+ password + ", valPassword=" + valPassword + "]";
	}

	


}
