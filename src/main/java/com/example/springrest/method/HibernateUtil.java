package com.example.springrest.method;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@SuppressWarnings("deprecation")
public class HibernateUtil {

	private static HibernateUtil instance=new HibernateUtil();
    private SessionFactory sessionFactory;
    
    public static HibernateUtil getInstance(){
            return instance;
    }
    
    private HibernateUtil(){
        try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml"); 
			        
			sessionFactory = configuration.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
    }
    
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        
        return session;
    }
}
