package com.Hibernate.DEMO.com.Hibernate.DEMO_CRUD_operations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // add(new Employee("Петрова", "Валентина", "IT", 4500));
        //  System.out.println(getById(2));
        //  System.out.println(getAllEmp());
        // add(new Employee("Катя","Иванова","Finance", 890));
        // getAllByFilter("from Employee where surname = 'Anisimova'");
      //  getAllByFilter("from Employee where surname = 'Anisimova' AND salary>1000");
      //  updateById(2,100000);
      //  updateWithCondition("update Employee set salary = 2000 where salary>2000");

        //deleteById(3);

        deleteByCondition("delete Employee where department='Finance' AND name='Катя'");


    }

    public static void add(Employee employee) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
        } finally {
            sf.close();
        }
    }

    public static Employee getById(int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            Employee emp1 = session.get(Employee.class, id);
            session.getTransaction().commit();
            return emp1;
        } finally {
            sf.close();
        }
    }

    public static List<Employee> getAllEmp() {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            List<Employee> list = session.createQuery("from Employee").getResultList();
            list.forEach(System.out::println);
            session.getTransaction().commit();
            return list;
        } finally {
            sf.close();
        }
    }

    public static List<Employee> getAllByFilter(String condition) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            List<Employee> list = session.createQuery(condition).getResultList();
            list.forEach(System.out::println);
            session.getTransaction().commit();
            return list;
        } finally {
            sf.close();
        }
    }

    public static void updateById (int id,int salary) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class,id);
            emp.setSalary(salary);
            session.getTransaction().commit();

        } finally {
            sf.close();
        }
    }
    public static void updateWithCondition (String condition) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
           session.createQuery(condition).executeUpdate();
            session.getTransaction().commit();

        } finally {
            sf.close();
        }
    }

    public static void deleteById (int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class,id);
            session.remove(emp);
            session.getTransaction().commit();

        } finally {
            sf.close();
        }
    }

    public static void deleteByCondition (String condition) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();

            session.createQuery(condition).executeUpdate();
            session.getTransaction().commit();

        } finally {
            sf.close();
        }
    }



}
