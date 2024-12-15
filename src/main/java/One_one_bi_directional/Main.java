package One_one_bi_directional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        //   add(new Employee("Вован", "Петров", "Security", 500), new Detail("karaganda", "2555598-98", "petrov@mail.ru"));
        System.out.println(getEmpByDetailsId(3));
        deleteDetailsById(2);
    }

    public static void add(Employee emp, Detail detail) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            emp.setEmpdetail(detail);
            detail.setEmployee(emp);
            session.beginTransaction();
            session.persist(emp);
            session.getTransaction().commit();
        } finally {
            sf.close();
        }
    }

    public static Employee getEmpByDetailsId(int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = sf.getCurrentSession();

            session.beginTransaction();
            Detail detail = session.get(Detail.class, id);
            return detail.getEmployee();


        } finally {
            session.getTransaction().commit();
            sf.close();
        }
    }

    public static void deleteDetailsById(int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = sf.getCurrentSession();

            session.beginTransaction();
            Detail detail = session.get(Detail.class, id);
            session.remove(detail);//вместе с записью о деталях будет удалена запись о сотруднике

        } finally {
            session.getTransaction().commit();
            sf.close();
        }
    }

    /*
    Если изначально в details была установлена каскадная связь по all, а нужно удалить только detail,то нужно сузить перечень каскадных операций и разорвать связь detail и employee
    detail.getEmployee.set(null) далее можно удалять detail 
    * */


}
