package training.addressbook;

import org.testng.annotations.*;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    gotoAddNew();
    fillContactForm(new ContactData("testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111"));
    submitContactForm();
    gotoHomePage();
    logout();
  }

}
