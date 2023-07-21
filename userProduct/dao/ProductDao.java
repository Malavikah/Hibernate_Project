package org.jsp.userProduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.userProduct.dto.Product;
import org.jsp.userProduct.dto.User;

public class ProductDao {
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public Product addProduct(Product p, int user_id) {
		User u=manager.find(User.class, user_id);
		if(u != null) {
			p.setUser(u);
			u.getProd().add(p);
			EntityTransaction transaction=manager.getTransaction();
			manager.persist(p);
			transaction.begin();
			transaction.commit();
			return p;
		}
		return null;
	}
	
//	public Product updateProduct(Product p) {
//			EntityTransaction transaction=manager.getTransaction();
//			manager.merge(p);
//			transaction.begin();
//			transaction.commit();
//			return p;
//		}
	
	public List<Product> viewProducts(int id){
		String qry="select u.prod from User u where u.id=?1";	
		Query q=manager.createQuery(qry);
		q.setParameter(1, id);
		return q.getResultList();
		}
	
	public Product viewProducts(long phone, String password){
			String qry="select u.prod from User u where u.phone=?1 and u.password=?2";	
			Query q=manager.createQuery(qry);
			q.setParameter(1, phone);
			q.setParameter(2, password);
			try {
			return (Product) q.getSingleResult();
			}catch(NoResultException e) {
				return null;
			}
		}
		
}
