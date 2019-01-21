package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModification extends TestBase {
  @BeforeMethod
  public void before() {
    app.goTo().goToHomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withName("Aleksey").withSurname("Meshchaninov").withNickname("enkooo").withCompany("InfoTeCS").withCity("Moscow").withEmail("enko@mail.ru").withGroup("test11"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifyData =before.iterator().next();
    ContactData contact = new ContactData().withId(modifyData.getId()).withName("Dmitriy").withSurname("Ivanov").withNickname("oookne").withCompany("InfoTeCS").withCity("Moscow").withEmail("okne@mail.ru");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(before.size(), after.size());
    assertThat(after, equalTo(before.without(modifyData).withAdded(contact)));
  }


}
