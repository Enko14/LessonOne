package ru.stqa.pft.addressbook.tests.Groups;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new GroupData("test11", null, null));
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
    after.remove(after.size()-1);
    Assert.assertEquals(before,after);
  }

}
