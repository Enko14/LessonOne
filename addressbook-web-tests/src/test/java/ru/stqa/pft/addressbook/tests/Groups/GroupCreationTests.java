package ru.stqa.pft.addressbook.tests.Groups;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
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

public class GroupCreationTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validGroupsFromXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/big_smile.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      List<GroupData> group = (List<GroupData>) xStream.fromXML(xml);
      return group.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("data.provider.groups.path"))))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> group = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType());
      return group.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJSON")
  public void testGroupCreation(GroupData group) {

    //GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.goTo().GroupPage();
    Groups before = app.db().groups();
    app.group().create(group);
    assertThat(app.group().getGroupCount(), equalTo(before.size() + 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyGroupListInUI();

  }

  @Test
  public void testGroupBadCreation() {
    app.goTo().GroupPage();
    Groups before = app.db().groups();
    GroupData group = new GroupData().withName("test22'");
    app.group().create(group);
    assertThat(app.group().getGroupCount(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before));
    verifyGroupListInUI();
  }

}
