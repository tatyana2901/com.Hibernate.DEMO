package One_many_bi_directional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
     /*   addNewDepNewEmp(new Department("Finance",10,50),
                new Employee("Иван","Петроненко", 45));*/
        getEmpsByDepId(2);
        //  deleteEmpById(1);

    }


    public static void addNewDepNewEmp(Department department, Employee employee) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();

            session.beginTransaction();
            department.addEmpToDep(employee);
            session.persist(department);
            session.getTransaction().commit();
        } finally {
            System.out.println(department.getEmps());
            sf.close();
        }
    }

    public static List<Employee> getEmpsByDepId(int id) { ///при return list ошибка lazy initialisation
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session session = null;
        List<Employee> list = null;
        try {
            session = sf.getCurrentSession();

            session.beginTransaction();
            Department d = session.get(Department.class, id);
            System.out.println("Печатается департамент" + d);
            list = d.getEmps();
            System.out.println("Печатается список" + list);
            return list;
            //System.out.println(list);

        } finally {
            session.getTransaction().commit();

            sf.close();

        }
    }


    public static Department getDepbyEmpId(int id) { ///при return list ошибка lazy initialisation
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = sf.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            Department d = emp.getDepartment();
            return d;

        } finally {
            session.getTransaction().commit();
            sf.close();
        }
    }

    public static void deleteEmpById(int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = sf.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            session.remove(emp);

        } finally {
            session.getTransaction().commit();
            sf.close();
        }
    }


}