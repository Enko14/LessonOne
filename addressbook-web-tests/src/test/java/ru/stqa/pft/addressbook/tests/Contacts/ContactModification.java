package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Aleksey", "Meshchaninov", "enkooo", "InfoTeCS", "Moscow", "enko@mail.ru", "test11"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().clickEdit();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Dmitriy", "Ivanov", "oookne", "InfoTeCS", "Moscow", "okne@mail.ru", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitUpdate();
    app.getContactHelper().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size());
    before.remove(before.size()-1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
  }

}
