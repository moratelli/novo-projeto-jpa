package br.sc.senai.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NamedQueries({
		@NamedQuery(name="User.listAll", query="select u from User u"),
		@NamedQuery(name="User.login", query="select u from User u where u.email = :email and u.senha = :senha")
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 40)
	private String nome;

	@Column(length = 12)
	private String senha;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
