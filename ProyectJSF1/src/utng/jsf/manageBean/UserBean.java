package utng.jsf.manageBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import utng.jsf.entities.SecurityRole;
import utng.jsf.entities.User;
import utng.jsf.repositories.UserRepository;
import utng.jsf.repositories.SecurityRoleRepository;

@ManagedBean
public class UserBean {
	@ManagedProperty(value= "#{entityManager}")
	private EntityManager entityManager;
	private User user = new User();
	private List<User> users;
	private Long securityRoleID;
	
	public void save() {
		SecurityRoleRepository securityRoleRepository = new SecurityRoleRepository(entityManager);
		SecurityRole securityRole = securityRoleRepository.search(securityRoleID);
		this.user.setSecurityRole(securityRole);
		UserRepository userRepository = new UserRepository(this.entityManager);
		
		if(this.user.getId()!=null) {
			userRepository.update(this.user);
		}else {
			userRepository.save(this.user);
		}
		this.user= new User();
		this.users= null;
		this.setSecurityRoleID(null); 
	}
	
	public void remove(User user) {
		UserRepository repository= new UserRepository(this.entityManager);
		repository.remove(user);
		this.users= null;
	}
	
	public void search(User user) {
		UserRepository repository= new UserRepository(this.entityManager);
		user= repository.search(user.getId());
		this.user = user;
		setSecurityRoleID(this.user.getSecurityRole().getId());
	}
	
	public List<User> getUsers(){
		if(this.users==null) {
			UserRepository repository = new UserRepository(this.entityManager);
			this.users = repository.getUsers();
		}
		return this.users;
	}
	
	public Long getCount() {
		UserRepository repository= new UserRepository(this.entityManager);
		return repository.getCountUsers();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user= user;
	}
	
	public Long getSecurityRoleID() {
		return securityRoleID;
	}
	
	public void setSecurityRoleID(Long securityRoleID) {
		this.securityRoleID= securityRoleID;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager= entityManager;
	}
	
	@SuppressWarnings("unused")
	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("EntityManager");
	}
	
}
