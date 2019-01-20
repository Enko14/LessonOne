package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Aleksey", "Meshchaninov", "enkooo", "InfoTeCS", "Moscow", "enko@mail.ru","test11"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().clickEdit();
    app.getContactHelper().fillContactForm(new ContactData("Dmitriy", "Ivanov", "oookne", "InfoTeCS", "Moscow", "okne@mail.ru",null),false);
    app.getContactHelper().submitUpdate();
    app.getContactHelper().returnHomePage();
  }

}
