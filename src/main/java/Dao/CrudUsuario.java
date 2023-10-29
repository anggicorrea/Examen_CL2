package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import Interfaces.InterUsuarioable;
import model.TblUsuariocl2;

public class CrudUsuario implements InterUsuarioable {

	@Override
	public void RegistrarUsuario(TblUsuariocl2 tblusu) {
		EntityManagerFactory cnx=Persistence.createEntityManagerFactory("CL2_RODRIGUEZ");
		EntityManager em= cnx.createEntityManager();
		em.getTransaction().begin();
		em.persist(tblusu);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public String ValidarUsuario(TblUsuariocl2 usu) {
		EntityManagerFactory cnx = Persistence.createEntityManagerFactory("CL2_RODRIGUEZ");
		EntityManager em = cnx.createEntityManager();
		em.getTransaction().begin();
		
		Query cons = em.createQuery("select u from TblUsuariocl2 u where u.usuariocl2=:x and u.passwordcl2=:y",TblUsuariocl2.class);
		cons .setParameter("x", usu.getUsuariocl2());		
		cons.setParameter("y",usu.getPasswordcl2());
		
		String mensaje = ""; 
		TblUsuariocl2  u;
		try{ 
			u=(TblUsuariocl2) cons.getSingleResult();
			mensaje = "Bienvenido : " + usu.getUsuariocl2();
		} catch (Exception ex){
			u = null;
			mensaje = "No encontrado, por favor registrese";
		}
					
		return mensaje;
	}

}
