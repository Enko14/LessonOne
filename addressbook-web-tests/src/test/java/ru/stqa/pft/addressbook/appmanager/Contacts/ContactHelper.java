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

  public void clickEdit() {
    click(By.xpath("(//img[@alt='Edit'])"));
  }

  public void clickDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitAllert() {
    submit().accept();
  }

  public void selectContact(int index) {
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

  public void createContact(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact, true);
    submitCreation();
    returnHomePage();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));
    for (WebElement element : elements) {
      String surname = element.findElement(By.xpath("//tr/td[2]")).getText();
      String name = element.findElement(By.xpath("//tr/td[3]")).getText();
      String city =element.findElement(By.xpath("//tr/td[6]")).getText();
      String id=element.findElement(By.tagName("input")).getAttribute("id");
      ContactData contactData = new ContactData(id,name, surname, null,null,city,null,null);
      contacts.add(contactData);
    }
    return contacts;
  }

}
