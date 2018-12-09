package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnHomePage() {
    click(By.linkText("home page"));
  }

  public void submitCreation() {
    click(By.xpath("(//input[@name='submit'])"));
  }
  public void submitUpdate(){
    click(By.xpath("(//input[@name='update'])"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("home"), contactData.getCity());
    type(By.name("email"), contactData.getEmail());

  }

  public void clickEdit(){
    click(By.xpath("(//img[@alt='Edit'])"));
  }
  public void clickDelete(){
    click(By.xpath("//input[@value='Delete']"));
  }
  public void submitAllert(){
    submit().accept();
  }
  public void selectContact() {
    click(By.cssSelector("input:not(:checked)[type='checkbox']"));
  }
  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }
}
