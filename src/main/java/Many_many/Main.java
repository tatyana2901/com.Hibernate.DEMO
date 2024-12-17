package Many_many;

import One_many_bi_directional.Department;
import One_many_bi_directional.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {
       // addNewChildToSection(new Child("Ваня", 10), new Child("Петя", 8), new Child("Илья", 9), new Section("Футболл"));
       // addNewChildToSection(new Child("Коля",10),new Section("Волейбол"),new Section("Математика"),new Section("Танцы"));
        System.out.println(getSectionOfChild(5));
    }

    public static void addNewChildToSection(Child child, Child child1, Child child2, Section section) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = sf.getCurrentSession();
            section.addChildToSection(child);
            section.addChildToSection(child1);
            section.addChildToSection(child2);

            session.beginTransaction();

            session.persist(section);
            session.getTransaction().commit();
        } finally {
            session.close();
            sf.close();
        }
    }

    public static void addNewChildToSection(Child child, Section section, Section section1, Section section2) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = sf.getCurrentSession();
            child.addSectionToChild(section);
            child.addSectionToChild(section1);
            child.addSectionToChild(section2);

            session.beginTransaction();

            session.persist(child);
            session.getTransaction().commit();
        } finally {
            session.close();
            sf.close();
        }
    }

    public static List<Section> getSectionOfChild(int id) {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();
        Session session = null;
        List<Section> s =null;
        try {
            session = sf.getCurrentSession();


            session.beginTransaction();
            Child child = session.get(Child.class,id);
            child.getSections();
            session.getTransaction().commit();





        } finally {
            session.close();
            sf.close();
            return s;
        }
    }
}
