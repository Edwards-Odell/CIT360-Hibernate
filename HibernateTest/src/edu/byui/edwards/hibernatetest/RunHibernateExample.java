package edu.byui.edwards.hibernatetest;

/* This program is a part of a simple hibernate example used for CIT-360
It is written by Troy Tuckett.  BYUI.EDU
*/
// package edu.byui.tuckett.hibernatetest;

import java.util.*;

public class RunHibernateExample {

 public static void main(String[] args) {

     TestDAO t = TestDAO.getInstance();

     List<Customer> c = t.getCustomers();
     for (Customer i : c) {
         System.out.println(i);
     }

     System.out.println(t.getCustomer(1));
 }
}
/*
Trying to figure out why this thing wont commit.
*/
