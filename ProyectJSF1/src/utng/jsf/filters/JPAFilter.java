package utng.jsf.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames= {"Faces Servlet"})
public class JPAFilter implements Filter{
	
	private EntityManagerFactory emf;
/*	
	@Override
	public void destroy() {
		this.emf.close();
	}
*/	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		emf=Persistence.createEntityManagerFactory("testdb");
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse reponse, FilterChain fc)
			throws IOException, ServletException {
		EntityManager entityManager= emf.createEntityManager();
		request.setAttribute("entityManager", entityManager);
		
		entityManager.getTransaction().begin();
		fc.doFilter(request, reponse);
		try {
			entityManager.getTransaction().commit();
		}catch(Exception e){
			entityManager.getTransaction().rollback();
			throw new ServletException(e.getMessage());
		}finally {
			entityManager.close();
		}
		
	}
	
	@Override
	public void destroy() {
		emf.close();
	}
}