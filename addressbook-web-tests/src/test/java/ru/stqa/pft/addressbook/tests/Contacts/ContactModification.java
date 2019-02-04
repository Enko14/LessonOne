package ru.stqa.pft.addressbook.tests.Contacts;

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
    if (app.db().contacts().size() == 0) {
      app.goTo().goToHomePage();
      app.contact().create(new ContactData().withName("Aleksey").withSurname("Meshchaninov").withNickname("enkooo").withCompany("InfoTeCS").withCity("Moscow").withAddress2("Moscow").withEmail("enko@mail.ru").withEmail2("enko@mail.ru").withEmail3("enko@mail.ru"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifyData = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyData.getId()).withName("Dmitriy").withSurname("Ivanov").withNickname("oookne").withCompany("InfoTeCS").withCity("Moscow").withAddress2("Moscow").withEmail("okne@mail.ru").withEmail2("enko@mail.ru").withEmail3("enko@mail.ru");
    app.goTo().goToHomePage();
    app.contact().modify(contact);
    assertThat(app.contact().getContactCount(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifyData).withAdded(contact)));
  }


}
