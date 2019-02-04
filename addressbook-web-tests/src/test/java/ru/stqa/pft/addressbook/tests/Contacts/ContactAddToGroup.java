package ru.stqa.pft.addressbook.tests.Contacts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroup extends TestBase {
  GroupData addedGroup;
  int contactID;

  @BeforeMethod()
  public void before() {
    if (app.db().contacts().size() == 0) {
      app.goTo().goToHomePage();
      app.contact().create(new ContactData().withName("Aleksey").withSurname("Meshchaninov").withNickname("enkooo").withCompany("InfoTeCS").withCity("Moscow").withAddress2("Moscow").withEmail("enko@mail.ru").withEmail2("enko@mail.ru").withEmail3("enko@mail.ru"));
    }
  }

  @BeforeClass
  public void createGroupBeforeTest() {
    app.goTo().GroupPage();
    addedGroup = createGroup();
  }

  @Test
  public void contactAddToGroupTest() {
    app.goTo().goToHomePage();
    contactID = app.db().contacts().iterator().next().getId();
    Groups before = getGroupsByID(contactID);
    app.contact().groupAddToContact(contactID, addedGroup);
    Groups after = getGroupsByID(contactID);
    assertThat(after, equalTo(before.withAdded(addedGroup)));
  }

  @Test(dependsOnMethods = "contactAddToGroupTest")
  public void contactDeleteGroupTest() {
    app.goTo().goToHomePage();
    Groups before = getGroupsByID(contactID);
    app.contact().contactDeleteFromgroup(contactID, addedGroup);
    Groups after = getGroupsByID(contactID);
    assertThat(after, equalTo(before.without(addedGroup)));
  }

  @AfterClass
  public void deleteAddedGroup() {
    app.goTo().GroupPage();
    app.group().delete(addedGroup);
  }

  private GroupData createGroup() {
    app.group().create(new GroupData().withName("test11").withHeader("test22").withFooter("test33"));
    Groups groups = app.db().groups();
    return groups.stream().max(Comparator.comparingInt(GroupData::getId)).get();
  }

  private Groups getGroupsByID(int id) {
    return app.db().contacts().stream().filter(o -> o.getId() == id).findFirst().get().getGroups();
  }
}
