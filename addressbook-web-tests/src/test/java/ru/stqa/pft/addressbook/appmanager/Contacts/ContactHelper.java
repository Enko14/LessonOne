package ru.stqa.pft.addressbook.appmanager.Contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnHomePage() {
    click(By.linkText("home page"));
  }

  public void submitCreation() {
    click(By.xpath("(//input[@name='submit'])"));
  }

  public void submitUpdate() {
    click(By.xpath("(//input[@value='Update'])"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getCity());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
   // attach(By.name("photo"), contactData.getPictire());
    if (creation) {
      if (contactData.getGroup() != null) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      }
    } else {
      {
        Assert.assertFalse(isElementPresent(By.name("new_group")));
      }
    }
  }

  public void clickEdit(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "'")).click();
  }

  public void clickDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitAllert() {
    submit().accept();
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
    // return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
  }

  public ContactData getContactFromEdit(ContactData contactData) {
    clickEdit(contactData.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    return new ContactData().withName(name).withSurname(lastname).withCompany(company)
            .withCity(address).withNickname(nickname).withEmail(email).withHomeTel(home).withWorkTel(work).withMobileTel(mobile).withEmail2(email2).withEmail3(email3);

  }

  private Contacts contactsCache = null;

  public void create(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact, true);
    submitCreation();
    contactsCache = null;
    returnHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    clickEdit(contact.getId());
    fillContactForm(contact, false);
    submitUpdate();
    contactsCache = null;
    returnHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    clickDelete();
    submitAllert();
    contactsCache = null;
  }

  public Contacts all() {
    if (contactsCache != null) {
      return new Contacts(contactsCache);
    }
    contactsCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
    for (WebElement element : elements) {
      String surname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String adress = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String allphones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactsCache.add(new ContactData().withId(id).withName(name).withSurname(surname)
              .withCity(adress).withAllphones(allphones).withAllEmails(allEmails));
    }
    return new Contacts(contactsCache);
  }

}