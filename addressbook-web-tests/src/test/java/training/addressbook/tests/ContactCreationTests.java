package training.addressbook.tests;

import org.testng.annotations.*;
import training.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111"));
    //app.logout();
  }

}
