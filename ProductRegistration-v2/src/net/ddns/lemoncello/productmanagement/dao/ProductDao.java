package net.ddns.lemoncello.productmanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.ddns.lemoncello.productmanagement.model.Product;
import net.ddns.lemoncello.productmanagement.utl.HibernateUtil;

public class ProductDao {

	/**
	 * Save Product
	 * 
	 * @param product
	 */
	public void saveProduct(Product product) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Update product
	 * 
	 * @param product
	 */
	public void updateProduct(Product product) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Delete product
	 * 
	 * @param barcode
	 */
	public void deleteProduct(int barcode) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Product product = session.get(Product.class, barcode);
			if (product != null) {
				session.delete(product);
				System.out.println("product is deleted");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param barcode
	 * @return
	 */
	public Product getProduct(int barcode) {
		Transaction transaction = null;
		Product product = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			product = session.get(Product.class, barcode);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return product;
	}
	
	/**
	 * Get all products
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct() {
		Transaction transaction = null;
		List<Product> listOfProduct = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			listOfProduct = session.createQuery("from Product").getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfProduct;
		
	}

}
