package com.example.springrest.method;

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
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.example.springrest.entity.Ent;
import com.example.springrest.entity.RepoEnt;
import com.example.springrest.entity.RepoSubEnt;
import com.example.springrest.entity.SubEnt;

@Component
public class DOMeeting {

	@Autowired
	private RepoEnt Repository;
	
	@Autowired
	private RepoSubEnt RepositorySubent;

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

			session.saveOrUpdate(Ent);

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
//		query.setString("meeting", b.getSubEnts().get(0).getMeeting());
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
		Ent Ent = (Ent) criteria.add(Restrictions.eq("name", name)).uniqueResult();
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

	public String bookMeeting(Ent ent , Ent meetingWithEnt) {
		String status = "";
		try {
			Date today = new Date();
			boolean validateMeetingTime = ent.subEnts.get(0).getMeetingDate().after(today) && ent.subEnts.get(0).getMeetingEndDate().after(today) && ent.subEnts.get(0).getMeetingDate().before(ent.subEnts.get(0).getMeetingEndDate());
			if(validateMeetingTime) {
				Ent meetingAlreadyWith = Repository.findTopByNameAndSubEntsMeetingDateLessThanEqualAndSubEntsMeetingEndDateGreaterThanEqual(ent.getName(), ent.subEnts.get(0).getMeetingDate(), ent.subEnts.get(0).getMeetingDate()).orElse(new Ent());
				
				if (meetingAlreadyWith != null && meetingAlreadyWith.subEnts!=null && meetingAlreadyWith.subEnts.get(0)!=null && meetingAlreadyWith.subEnts.get(0).getMeetingWith() != null && !meetingAlreadyWith.subEnts.get(0).getMeetingWith().equals("")) {
					status = "Meeting already booked with : " + meetingAlreadyWith.subEnts.get(0).getMeetingWith();
				} else {
					
					
//					SubEnt mainMeeting = ent.getSubEnts().get(0);
//					mainMeeting.setEnt(ent);
//					
//					SubEnt mainMeetingWithEnt = meetingWithEnt.getSubEnts().get(0);
//					mainMeetingWithEnt.setEnt(meetingWithEnt);
					
					
					status = "Meeting Booked";
					Repository.save(ent);
					Repository.save(meetingWithEnt);

					status = "Meeting Booked";
				}
			}else {
				status = "Invalid Time Entered";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
	public String bookMeetingSubEnt(SubEnt ent , SubEnt meetingWithEnt) {
		String status = "";
		try {
			Date today = new Date();
			boolean validateMeetingTime = ent.getMeetingDate().after(today) && ent.getMeetingEndDate().after(today) && ent.getMeetingDate().before(ent.getMeetingEndDate());
			if(validateMeetingTime) {
				Ent meetingAlreadyWith = Repository.findTopByNameAndSubEntsMeetingDateLessThanEqualAndSubEntsMeetingEndDateGreaterThanEqual(ent.getEnt().getName(), ent.getMeetingDate(), ent.getMeetingDate()).orElse(new Ent());
				
				if (meetingAlreadyWith != null && meetingAlreadyWith.subEnts!=null && meetingAlreadyWith.subEnts.get(0)!=null && meetingAlreadyWith.subEnts.get(0).getMeetingWith() != null && !meetingAlreadyWith.subEnts.get(0).getMeetingWith().equals("")) {
					status = "Meeting already booked with : " + meetingAlreadyWith.subEnts.get(0).getMeetingWith();
				} else {
					System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
					RepositorySubent.save(ent);
					RepositorySubent.save(meetingWithEnt);
					status = "Meeting Booked";
				}
			}else {
				status = "Invalid Time Entered";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status=e.getLocalizedMessage();
		}

		return status;
	}
}
