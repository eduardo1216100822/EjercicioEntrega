package utng.jsf.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import utng.jsf.entities.SecurityRole;

public class SecurityRoleRepository {
	
	private EntityManager entityManager;
	
	public SecurityRoleRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public SecurityRole search(Long ID) {
		return entityManager.find(SecurityRole.class, ID);
	}
	
	@SuppressWarnings("unchecked")
	public List<SecurityRole> getSecurityRoles(){
		return this.entityManager.createNamedQuery("SecurityRole.findAll").getResultList();
	}
}
