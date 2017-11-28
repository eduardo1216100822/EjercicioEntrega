package utng.jsf.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import utng.jsf.entities.User; 

public class UserRepository {
	private EntityManager entityManager;
	
	
	public UserRepository(EntityManager entityManager) {
		this.entityManager= entityManager;
	}
	
	public void save(User user) {
		this.entityManager.persist(user);
		this.entityManager.flush();
	}
	
	public void update(User user) {
		this.entityManager.merge(user);
		this.entityManager.flush();
	}
	
	public void remove(User user) {
		this.entityManager.remove(user);
	}
	
	public User search(Long ID) {
		return this.entityManager.find(User.class, ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUsers(){
		return this.entityManager.createNamedQuery("User.findAll").getResultList();
	}
	
	public Long getCountUsers() {
		return (Long) this.entityManager.createNamedQuery("User.count").getSingleResult();
	}

}
