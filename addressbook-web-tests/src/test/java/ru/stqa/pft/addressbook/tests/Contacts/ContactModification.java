package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase {
  @BeforeMethod
  public void before() {
    app.goTo().goToHomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Aleksey", "Meshchaninov", "enkooo", "InfoTeCS", "Moscow", "enko@mail.ru", "test11"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Dmitriy", "Ivanov", "oookne", "InfoTeCS", "Moscow", "okne@mail.ru", null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(before.size(), after.size());
    before.remove(index);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after), new HashSet<Object>(before));
  }


}
