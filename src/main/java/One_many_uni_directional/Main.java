package One_many_uni_directional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        addNewDepNewEmp(new Department("Logistic", 500, 800), new Employee("Петр", "Федоров", 700));
        getEmpsByDepId(2);

    }


    public static void addNewDepNewEmp(Department department, Employee employee) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            department.addEmpToDep(employee);

            session.beginTransaction();
            session.persist(department);
            session.getTransaction().commit();
        } finally {
            System.out.println(department.getEmps());
            sf.close();
        }
    }

    public static void getEmpsByDepId(int id) { ///при return list ошибка lazy initialisation
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
            list = d.getEmps();
            // return list;
            System.out.println(list);

        } finally {
            session.getTransaction().commit();
            sf.close();

        }
    }


/*    public static Department getDepbyEmpId(int id) { ///при return list ошибка lazy initialisation
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
    }*/
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

        //если удалям только работника, то департамент останется
        //если удаляется департамент, то удалится и работник из этого департамента
    }


}