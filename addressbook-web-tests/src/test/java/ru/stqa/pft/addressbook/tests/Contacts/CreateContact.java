package ru.stqa.pft.addressbook.tests.Contacts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateContact extends TestBase {


  @DataProvider
  public Iterator<Object[]> validDataFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("data.provider.contact.path"))))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contactData = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contactData.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validDataFromJSON")
  public void testCreateContact(ContactData contactData) {
    app.goTo().goToHomePage();
    Contacts before = app.contact().all();
    File picture = new File("src/test/resources/big_smile.png");
    //ContactData contact = new ContactData().withName("Aleksey").withSurname("Meshchaninov").withNickname("enkooo").withCompany("InfoTeCS").withCity("Moscow").withEmail("enko@mail.ru").withPictire(picture);
    app.contact().create(contactData);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));

  }
}
