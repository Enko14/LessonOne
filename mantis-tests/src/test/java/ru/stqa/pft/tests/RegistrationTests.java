package ru.stqa.pft.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

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
    app.james().createUser(user1, password);
    app.registration().start(user1, email);
    // List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    List<MailMessage> mailMessages = app.james().waitForMail(user1, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user1, password));
    app.james().deleteUser(user1);
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  //@AfterMethod(alwaysRun = true)
  public void stopMail() {
    app.mail().stop();
  }
}
