package training.addressbook.tests;

import org.testng.annotations.Test;
import training.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    //app.logout();
  }


}
