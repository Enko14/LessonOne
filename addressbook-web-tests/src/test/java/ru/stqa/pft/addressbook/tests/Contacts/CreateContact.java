package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class CreateContact extends TestBase {


  @Test
  public void testCreateContact() {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Aleksey", "Meshchaninov", "enkooo", "InfoTeCS", "Moscow", "enko@mail.ru", "test11"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    after.remove(after.size()-1);
    Assert.assertEquals(after,before);
  }
}
