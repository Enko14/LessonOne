package ru.stqa.pft.addressbook.tests.Contacts;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

public class CreateContact extends TestBase {


  @Test
  public void testCreateContact() {
    app.goTo().goToHomePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withName("Aleksey").withSurname("Meshchaninov").withNickname("enkooo").withCompany("InfoTeCS").withCity("Moscow").withEmail("enko@mail.ru").withGroup("test11");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));

  }
}
