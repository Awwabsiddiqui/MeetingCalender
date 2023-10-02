package com.example.springrest.method;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.example.springrest.entity.Ent;

public class DOMeeting {
	public void doRegister(String name) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Ent Ent = new Ent();
			Ent.setName(name);
			session.save(Ent);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<Ent> getEnts() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from meetingcalender");
		List<Ent> Ents = query.list();
		session.close();
		return Ents;
	}

	public int updateEnt(Ent b) {
		if (b.getId() <= 0)
			return 0;
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "update meetingcalender set name = :name, meeting=:meeting where id = :id";
		Query query = session.createQuery(hql);
		query.setLong("id", b.getId());
		query.setString("title", b.getName());
		query.setString("meeting", b.getMeeting());
		int rowCount = query.executeUpdate();

		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

	public int updateEnt(long id) {
		if (id <= 0)
			return 0;
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Ent Ent = session.load(Ent.class, id);
		Ent.setName("Johnson");
		session.update(Ent);
		tx.commit();
		session.close();
		return 1;
	}

	public int updateEnt(String name) {
		if (name.equals(""))
			return 0;
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Ent.class);
		Ent Ent = (com.example.springrest.entity.Ent) criteria.add(Restrictions.eq("name", name)).uniqueResult();
		if (Ent != null) {
			session.update(Ent);
		}
		tx.commit();
		session.close();

		if (Ent != null)
			return 1;
		return 0;
	}

	public int deleteEnt(long id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "delete from meetingcalender where id = :id";
		Query query = session.createQuery(hql);
		query.setLong("id", id);
		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}

	public int deleteEnt(String name) {
		if (name.equals(""))
			return 0;
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Ent.class);
		Ent Ent = (com.example.springrest.entity.Ent) criteria.add(Restrictions.eq("name", name)).uniqueResult();
		session.remove(Ent);
		tx.commit();
		session.close();
		return 1;
	}
}
