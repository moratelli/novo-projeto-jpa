package br.sc.senai.dao;

import br.sc.senai.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserDAOTest {

	private static EntityManagerFactory factory;
	private static EntityManager entityManager;

	public static void main(String[] args) {

		factory = Persistence.createEntityManagerFactory("npdb");
		entityManager = factory.createEntityManager();

		insert();
		//  update();
		//  delete();
		//  find();

		listUsers();

		entityManager.close();
		factory.close();
	}

	public static void insert() {
		entityManager.getTransaction().begin();

		User newUser = new User();

		newUser.setNome("Carlos Alberto");
		newUser.setSenha("222333");

		entityManager.persist(newUser);
		entityManager.getTransaction().commit();
	}

	public static void update() {
		entityManager.getTransaction().begin();

		User updatedUser = entityManager.find(User.class, 3);

		updatedUser.setNome("Antonio Augusto");
		updatedUser.setSenha("aabbcc");

		entityManager.merge(updatedUser);
		entityManager.getTransaction().commit();
	}

	private static void delete() {
		entityManager.getTransaction().begin();

		User user = entityManager.find(User.class, 4);

		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	private static void find() {
		entityManager.getTransaction().begin();

		User user = entityManager.find(User.class, 1);

		System.out.println("Nome: " + user.getNome());
		System.out.println("Senha " + user.getSenha());

		entityManager.getTransaction().commit();
	}

	public static void listUsers() {
		Query query = entityManager.createNamedQuery("User.listAll");
		List<User> users = query.getResultList();

		for (User user : users) {
			System.out.println("------");
			System.out.println(user.getNome());
			System.out.println(user.getSenha());
		}
	}

}
