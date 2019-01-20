package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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

  public void selectContact() {
    click(By.cssSelector("input:not(:checked)[type='checkbox']"));
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
    fillContactForm(contact,true);
    submitCreation();
    returnHomePage();
  }

}
