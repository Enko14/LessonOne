package ru.stqa.pft.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.model.User;
import ru.stqa.pft.model.Users;

import java.util.List;

public class DBHelper {
  private final SessionFactory sessionFactory;

  public DBHelper() {
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }


  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from User").list();
    for (User user : (List<User>) result) {
      System.out.println(user);
    }
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }
}
