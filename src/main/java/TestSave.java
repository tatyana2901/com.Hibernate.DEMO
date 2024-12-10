import com.Hibernate.DEMO.com.Hibernate.DEMO.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestSave {

    public static void main(String[] args) {
       // add(new Employee("Петрова", "Валентина", "IT", 4500));
        System.out.println(getById(2));


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
            Employee emp1 = session.get(Employee.class,id);
            session.getTransaction().commit();
            return emp1;
        } finally {
            sf.close();
        }
    }

}
