package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class DeleteContactTest extends TestBase {

  @Test
  public void testDeleteContact() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Aleksey", "Meshchaninov", "enkooo", "InfoTeCS", "Moscow", "enko@mail.ru", "test11"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().clickDelete();
    app.getContactHelper().submitAllert();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }

}
