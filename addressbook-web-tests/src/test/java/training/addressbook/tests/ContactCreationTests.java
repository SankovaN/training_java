package training.addressbook.tests;

import org.testng.annotations.*;
import training.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNew();
    app.getContactHelper().fillContactForm(new ContactData("testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111"));
    app.getContactHelper().submitContactForm();
    app.getContactHelper().gotoHomePage();
    app.logout();
  }

}
