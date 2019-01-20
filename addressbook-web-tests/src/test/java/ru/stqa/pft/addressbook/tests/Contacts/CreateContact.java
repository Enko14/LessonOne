package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class CreateContact extends TestBase {


  @Test
  public void testCreateContact() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact=new ContactData("Aleksey", "Meshchaninov", "enkooo", "InfoTeCS", "Moscow", "enko@mail.ru", "test11");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);
    contact.setId(after.stream().max(Comparator.comparingInt(ContactData::getId)).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));

  }
}
