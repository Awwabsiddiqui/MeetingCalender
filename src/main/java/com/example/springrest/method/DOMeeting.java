package com.example.springrest.method;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springrest.entity.Ent;
import com.example.springrest.entity.Repo;

@Component
public class DOMeeting {

	@Autowired
	private Repo Repository;

	public void doRegister(String name) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Ent Ent = new Ent();
			Ent.setName(name);

			Criteria criteria = session.createCriteria(Ent.class);
			List results = criteria.add(Restrictions.eq("name", name)).list();
			System.out.println("List ::: " + results);
			if (results.isEmpty()) {
				session.saveOrUpdate(Ent);
			} else {
				System.out.println("already present !!!");
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public String doRegisterEntity(Ent Ent) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		String status = "Created Successfully";
		try {
			transaction = session.beginTransaction();

//			Criteria criteria = session.createCriteria(Ent.class);
//			List results = criteria.add(Restrictions.eq("name", Ent.getName())).list();
//			System.out.println("List ::: "+results);

			boolean doesUserExist = Repository.existsByName(Ent.getName());
			System.out.println("doesUserExist : " + doesUserExist);
//			if(!doesUserExist   /*results.isEmpty()*/) {
			session.saveOrUpdate(Ent);
//			}else {
//				System.out.println("already present !!!");
//				status="Already Present";
//			}

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
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
		List<Ent> list = criteria.add(Restrictions.eq("name", name)).list();

		for (Ent ent : list) {
			session.remove(ent);
		}
		tx.commit();
		session.close();
		return 1;
	}

	public List findEntByName(String name) {
		if (name.equals(""))
			return null;
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Ent.class);
		List results = criteria.add(Restrictions.eq("name", name)).list();
		session.close();

		return results;
	}

	public String bookMeeting(Ent ent) {
		String status = "";
		Ent meetingAlreadyWith = Repository.findTopByNameAndMeetingDateLessThanEqualAndMeetingEndDateGreaterThanEqual(ent.getName(), ent.getMeetingDate(), ent.getMeetingDate()).orElse(new Ent());
		if (meetingAlreadyWith != null && meetingAlreadyWith.getMeetingWith() != null && !meetingAlreadyWith.getMeetingWith().equals("")) {
			status = "Meeting already booked with : " + meetingAlreadyWith.getMeetingWith();
		} else {
			Repository.save(ent);
			
			Ent meetingWithEnt = new Ent();
			meetingWithEnt.setName(ent.getMeetingWith());
			meetingWithEnt.setMeetingWith(ent.getName());
			meetingWithEnt.setMeetingDate(ent.getMeetingDate());
			meetingWithEnt.setMeetingEndDate(ent.getMeetingEndDate());
			
			Repository.save(meetingWithEnt);
			
			status = "Meeting Booked";
		}
		return status;
	}

}
