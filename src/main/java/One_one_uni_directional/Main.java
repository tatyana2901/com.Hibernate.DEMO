package One_one_uni_directional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        // add(new Employee("Маргарита", "Черненко", "HR", 84500),new Detail("Moscow", "897-888", "margosha@mail.ru"));
        // add(new Employee("Петр", "Иващенко", "Снабжение", 33200),new Detail("Kazan", "0129888", "petruxa@mail.ru"));
        //System.out.println(getDetailsById(1));
        deleteById(1); //УДАЛЯТСЯ ЗАПИСИ ИЗ СВЯЗАННОЙ ТАБЛИЦЫ DETAILS ПО УДАЛЯЕМОМУ СОТРУДНИКУ

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
            session.beginTransaction();
            session.persist(emp);
            session.getTransaction().commit();
        } finally {
            sf.close();
        }
    }

    public static Detail getDetailsById(int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            Employee e = session.get(Employee.class, id);
            Detail d = e.getEmpdetail();
            session.getTransaction().commit();
            return d;
        } finally {
            sf.close();
        }
    }

    public static void deleteById(int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        try {
            Session session = sf.getCurrentSession();
            session.beginTransaction();
            Employee e = session.get(Employee.class, id);
            session.remove(e);
            session.getTransaction().commit();

        } finally {
            sf.close();
        }


    }

}
