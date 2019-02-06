package ru.stqa.pft.tests;

import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;

public class RegistrationTests extends TestBase {
  // @BeforeMethod
  public void startMail() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);
    String user1 = String.format("user%s", now);
    String password = "password";
    app.registration().registerNewUser(user1, email, password);
    app.james().deleteUser(user1);
  }


  //@AfterMethod(alwaysRun = true)
  public void stopMail() {
    app.mail().stop();
  }
}
