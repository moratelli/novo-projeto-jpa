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

		//  insert();
		//  update();
		//  delete();
		//  find();

		User user = new User();
		user.setEmail("pedro@moratelli.dev");
		user.setSenha("123123");

		boolean found = login(user);

		if (found) {
			System.out.println("Login was successful!");
		} else {
			System.out.println("Wrong user/password!");
		}

		//  listUsers();

		entityManager.close();
		factory.close();
	}

	public static void insert() {
		entityManager.getTransaction().begin();

		User newUser = new User();

		newUser.setNome("Pedro Moratelli");
		newUser.setEmail("pedro@moratelli.dev");
		newUser.setSenha("123123");

		entityManager.persist(newUser);
		entityManager.getTransaction().commit();
	}

	public static void update() {
		entityManager.getTransaction().begin();

		User updatedUser = entityManager.find(User.class, 2);

		updatedUser.setNome("Antonio Augusto");
		updatedUser.setEmail("antonio@senai.br");
		updatedUser.setSenha("aabbcc");

		entityManager.merge(updatedUser);
		entityManager.getTransaction().commit();
	}

	private static void delete() {
		entityManager.getTransaction().begin();

		User user = entityManager.find(User.class, 1);

		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	private static void find() {
		entityManager.getTransaction().begin();

		User user = entityManager.find(User.class, 1);

		System.out.println("Nome: " + user.getNome());
		System.out.println("Email " + user.getEmail());

		entityManager.getTransaction().commit();
	}

	public static void listUsers() {
		Query query = entityManager.createNamedQuery("User.listAll");
		List<User> users = query.getResultList();

		for (User user : users) {
			System.out.println("------");
			System.out.println(user.getNome());
			System.out.println(user.getEmail());
			System.out.println(user.getSenha());
		}
	}

	public static boolean login(User user) {
		boolean found = true;

		try {
			Query query = entityManager.createNamedQuery("User.login");

			query.setParameter("email", user.getEmail());
			query.setParameter("senha", user.getSenha());

			User userLogin = (User) query.getSingleResult();

			System.out.println(userLogin.getNome() + " has logged in!");
		} catch (Exception e) {
			e.printStackTrace();
			found = false;
		}

		return found;
	}

}
