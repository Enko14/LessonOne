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
    assertThat(contactData.getHomeTel(), equalTo(contact.getHomeTel()));
    assertThat(contactData.getMobileTel(), equalTo(contact.getMobileTel()));
    assertThat(contactData.getWorkTel(), equalTo(contact.getWorkTel()));
  }
}
