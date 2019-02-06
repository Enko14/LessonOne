package ru.stqa.pft.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.model.User;

import java.util.List;

public class HbConnectionTest {
  private SessionFactory sessionFactory;

  @BeforeClass(alwaysRun = true)
  protected void setUp() {
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  @Test
  public void testHbConnection() {

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from User").list();

    session.getTransaction().commit();
    session.close();
    for (User contactData : (List<User>) result) {
      System.out.println(contactData.getUsername());
    }
  }
}
