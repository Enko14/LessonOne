package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

  @Test
  public void testDeleteContact() {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().clickDelete();
    app.getContactHelper().submitAllert();
    app.getNavigationHelper().goToHomePage();
  }

}
