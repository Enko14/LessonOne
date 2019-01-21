package ru.stqa.pft.addressbook.tests.Groups;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().GroupPage();
    List<GroupData> before=app.group().list();
    GroupData group=new GroupData().withName("test11");
    app.group().create(group);
    List<GroupData> after=app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(group.withId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId()));
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
