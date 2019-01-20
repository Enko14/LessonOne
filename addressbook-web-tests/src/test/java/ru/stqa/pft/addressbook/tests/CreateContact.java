package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class CreateContact extends TestBase {


  @Test
  public void testCreateContact() {
    app.getContactHelper().goToAddNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Aleksey", "Meshchaninov", "enkooo", "InfoTeCS", "Moscow", "enko@mail.ru","test11"),true);
    app.getContactHelper().submitCreation();
    app.getContactHelper().returnHomePage();
  }


}
