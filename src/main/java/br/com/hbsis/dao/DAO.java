package br.com.hbsis.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import br.com.hbsis.util.HibernateUtil;

public abstract class DAO<T> {
	private Session session;
	private Transaction transaction;
	private Class<T> entity;

	public DAO(Class<T> entity) {
		this.entity = entity;
	}

	public void saveOrUpdate(T t) {
		try {
			startOperation();
			session.saveOrUpdate(t);
			transaction.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			session.close();
		}
	}

	public void delete(T t) {
		try {
			startOperation();
			session.delete(t);
			transaction.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T find(Long id) {
		T obj = null;
		try {
			startOperation();
			obj = (T) session.load(entity.getSimpleName(), id);
			transaction.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			session.close();
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> objects = null;
		try {
			startOperation();
			Query<T> query = session.createQuery("from " + entity.getSimpleName());
			objects = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			session.close();
		}
		return objects;
	}

	private void handleException(HibernateException e) {
		System.err.println(e.getMessage());
	}

	private void startOperation() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}
}
