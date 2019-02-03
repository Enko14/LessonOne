package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;
import java.util.Properties;

public class DBConnectionTest {

  public static Connection connection;
  private static String user = "root";
  private static String pass = "";

  @Test
  public void testDBConnection() {
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF-8", new Properties() {{
        setProperty("user", user);
        setProperty("password", pass);
      }});
      Statement st = connection.createStatement();
      ResultSet rs = st.executeQuery("select group_id,group_name,group_footer,group_header from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      connection.close();
      System.out.println(groups);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }
}
