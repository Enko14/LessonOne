package ru.stqa.pft.addressbook.tests.Groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModification extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test11"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifyGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifyGroup.getId()).withName("test11").withHeader("test22").withFooter("test33");
    app.goTo().GroupPage();
    app.group().modify(group);
    assertThat(app.group().getGroupCount(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifyGroup).withAdded(group)));
    verifyGroupListInUI();
  }


}
