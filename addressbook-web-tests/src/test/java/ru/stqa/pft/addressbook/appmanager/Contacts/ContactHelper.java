package ru.stqa.pft.addressbook.appmanager.Contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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
    type(By.name("home"), contactData.getCity());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void clickEdit(int index) {
    wd.findElements(By.xpath(("//img[@alt='Edit']"))).get(index).click();
  }

  public void clickDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitAllert() {
    submit().accept();
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    ;
  }

  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
    // return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
  }

  public void create(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact, true);
    submitCreation();
    returnHomePage();
  }

  public void modify(int index, ContactData contact) {
    select(index);
    clickEdit(index);
    fillContactForm(contact, false);
    submitUpdate();
    returnHomePage();
  }

  public void delete(int index) {
    select(index);
    clickDelete();
    submitAllert();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
    for (WebElement element : elements) {
      String surname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String city = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withName(name).withSurname(surname).withCity(city));
    }
    return contacts;
  }

}