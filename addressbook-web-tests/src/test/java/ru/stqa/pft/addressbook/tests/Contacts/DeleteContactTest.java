package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactTest extends TestBase {
  @BeforeMethod
  public void before() {
    if (app.db().contacts().size() == 0) {
      app.goTo().goToHomePage();
      app.contact().create(new ContactData().withName("Aleksey").withSurname("Meshchaninov").withNickname("enkooo").withCompany("InfoTeCS").withCity("Moscow").withEmail("enko@mail.ru").withEmail2("enko@mail.ru").withEmail3("enko@mail.ru").withGroup("test11"));
    }
  }

  @Test
  public void testDeleteContact() {
    Contacts before = app.db().contacts();
    ContactData deletedData = before.iterator().next();
    app.contact().delete(deletedData);
    app.goTo().goToHomePage();
    assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedData)));
  }
}
