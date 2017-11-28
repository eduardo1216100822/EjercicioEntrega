package utng.jsf.manageBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;

import utng.jsf.entities.SecurityRole;
import utng.jsf.repositories.SecurityRoleRepository;

@ManagedBean
public class SecurityRoleBean {
	
	@ManagedProperty(value="#{entityManager}")
	private EntityManager entityManager;
	
	private List<SecurityRole> securityRoles=null;
	
	public List<SecurityRole> getSecurityRoles(){
		if(this.securityRoles == null) {
			SecurityRoleRepository repository = new SecurityRoleRepository(entityManager);
			this.securityRoles= repository.getSecurityRoles();
		}
		return this.securityRoles;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager= entityManager;
	}

}
