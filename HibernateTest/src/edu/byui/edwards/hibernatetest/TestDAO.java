package edu.byui.edwards.hibernatetest;

/* This program is a part of a simple hibernate example used for CIT-360
It is written by Troy Tuckett.  BYUI.EDU
*/
// package edu.byui.tuckett.hibernatetest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.*;

/** TestDAO implemented using a singleton pattern
*  Used to get customer data from my MYSQL database*/
public class TestDAO {

 SessionFactory factory = null;
 Session session = null;

 private static TestDAO single_instance = null;

 private TestDAO()
 {
     factory = HibernateUtils.getSessionFactory();
 }

 /** This is what makes this class a singleton.  You use this
  *  class to get an instance of the class. */
 public static TestDAO getInstance()
 {
     if (single_instance == null) {
         single_instance = new TestDAO();
     }

     return single_instance;
 }

 /** Used to get more than one customer from database
  *  Uses the OpenSession construct rather than the
  *  getCurrentSession method so that I control the
  *  session.  Need to close the session myself in finally.*/
 public List<Customer> getCustomers() {

     try {
         session = factory.openSession();
         session.getTransaction().begin();
         String sql = "from edu.byui.edwards.hibernatetest.Customer";
         List<Customer> resultList = (List<Customer>)session.createQuery(sql).getResultList();
		List<Customer> cs = resultList;
         session.getTransaction().commit();
         return cs;

     } catch (Exception e) {
         e.printStackTrace();
         // Rollback in case of an error occurred.
         session.getTransaction().rollback();
         return null;
     } finally {
         session.close();
     }

 }

 /** Used to get a single customer from database */
 public Customer getCustomer(int id) {

     try {
         session = factory.openSession();
         session.getTransaction().begin();
         String sql = "from edu.byui.edwards.hibernatetest.Customer where id=" + Integer.toString(id);
         Customer c = (Customer)session.createQuery(sql).getSingleResult();
         session.getTransaction().commit();
         return c;

     } catch (Exception e) {
         e.printStackTrace();
         // Rollback in case of an error occurred.
         session.getTransaction().rollback();
         return null;
     } finally {
         session.close();
     }
 }

}

