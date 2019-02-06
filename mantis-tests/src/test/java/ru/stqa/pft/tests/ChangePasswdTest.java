package ru.stqa.pft.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.model.User;

import javax.mail.MessagingException;
import java.io.IOException;

public class ChangePasswdTest extends TestBase {
  String email;
  String user;
  int userID;
  String password = "password";


  @BeforeTest
  public void getListUsers() throws IOException, MessagingException {
    //Проверяем, что у нас помимо администратора есть еще пользователи. Если нет - создаем.Если есть - берем первого попавшегося
    if (app.db().users().size() <= 1) {
      long now = System.currentTimeMillis();
      app.registration().registerNewUser(String.format("user%s", now), String.format("user%s@localhost", now), password);
    }
    User userData = app.db().users().stream().filter(o -> !o.getUsername().equals("administrator")).findFirst().get();
    email = userData.getEmail();
    user = userData.getUsername();
    userID = userData.getId();
    System.out.println(userData.toString());
  }

  @Test
  public void loginAsRootAndResetPasswd() throws IOException, InterruptedException, MessagingException {
    app.james().drainEmail(user, password);
    app.registration().login("administrator", "root");
    app.getDriver().findElement(By.cssSelector("a[class='manage-menu-link']")).click();
    app.getDriver().findElement(By.cssSelector("a[href='/mantisbt/manage_user_page.php']")).click();
    app.getDriver().findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", userID))).click();
    app.getDriver().findElement(By.cssSelector("input[value='Reset Password']")).click();
    app.registration().waitMail(user, email, password);
  }
}
