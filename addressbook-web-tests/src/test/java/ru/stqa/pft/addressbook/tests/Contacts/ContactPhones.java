package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhones extends TestBase {

  @Test
  public void testContactPhonesEmailsCity() {
    app.goTo().goToHomePage();
    ContactData contactData = app.contact().all().iterator().next();
    ContactData contact = app.contact().getContactFromEdit(contactData);
    assertThat(contactData.getAllphones(), equalTo(mergePhones(contact)));
    assertThat(contactData.getEmail(), equalTo(contact.getEmail()));
    assertThat(contactData.getCity(), equalTo(contact.getCity()));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomeTel(), contact.getMobileTel(), contact.getWorkTel()).stream().map(this::cleanedPhone).filter(o -> !o.equals("")).collect(Collectors.joining("\n"));
  }

  public String cleanedPhone(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
