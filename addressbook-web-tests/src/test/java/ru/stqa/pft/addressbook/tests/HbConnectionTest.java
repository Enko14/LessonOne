package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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
    List result = session.createQuery("from ContactData where deprecated ='0000-00-00'").list();

    session.getTransaction().commit();
    session.close();
    for (ContactData contactData : (List<ContactData>) result) {
      System.out.println(contactData);
      System.out.println(contactData.getGroups());
    }
  }
}
