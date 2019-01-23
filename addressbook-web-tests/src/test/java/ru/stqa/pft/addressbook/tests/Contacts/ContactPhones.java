package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhones extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().goToHomePage();
    ContactData contactData = app.contact().all().iterator().next();
    ContactData contact = app.contact().getContactFromEdit(contactData);
    assertThat(cleanedPhone(contactData.getHomeTel()), equalTo(cleanedPhone(contact.getHomeTel())));
    assertThat(cleanedPhone(contactData.getMobileTel()), equalTo(cleanedPhone(cleanedPhone(contact.getMobileTel()))));
    assertThat(cleanedPhone(contactData.getWorkTel()), equalTo(cleanedPhone(contact.getWorkTel())));
  }

  public String cleanedPhone(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
