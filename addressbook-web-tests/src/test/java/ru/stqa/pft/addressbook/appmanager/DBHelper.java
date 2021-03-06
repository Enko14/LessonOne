package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DBHelper {
  private final SessionFactory sessionFactory;

  public DBHelper() {
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from GroupData").list();
    for (GroupData groupData : (List<GroupData>) result) {
      System.out.println(groupData);
    }
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from ContactData where deprecated ='0000-00-00'").list();
    for (ContactData contactData : (List<ContactData>) result) {
      System.out.println(contactData);
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }
}
