package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import Interfaces.InterProductoable;
import model.TblProductocl2;

public class CrudProducto implements InterProductoable {

	@Override
	public void RegistrarProducto(TblProductocl2 tblprod) {
		EntityManagerFactory cnx = Persistence.createEntityManagerFactory("CL2_RODRIGUEZ");
		EntityManager em= cnx.createEntityManager();
		em.getTransaction().begin();
		em.persist(tblprod);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<TblProductocl2> ListaProducto() {
		EntityManagerFactory cnx =Persistence.createEntityManagerFactory("CL2_RODRIGUEZ");
		EntityManager em = cnx.createEntityManager();
		em.getTransaction().begin();
		List<TblProductocl2> listado = em.createQuery("select prod from TblProductocl2 prod",TblProductocl2.class).getResultList();
		return listado;
	}

}
