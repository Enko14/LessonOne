package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().clickEdit();
    app.getContactHelper().fillContactForm(new ContactData("Dmitriy", "Ivanov", "oookne", "InfoTeCS", "Moscow", "okne@mail.ru"));
    app.getContactHelper().submitUpdate();
    app.getContactHelper().returnHomePage();
  }

}
