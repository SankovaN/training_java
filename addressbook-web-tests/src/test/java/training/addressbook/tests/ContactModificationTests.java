package training.addressbook.tests;

import org.testng.annotations.Test;
import training.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
      app.getContactHelper().selectContact();
      app.getContactHelper().initContactModification();
      app.getContactHelper().fillContactForm(new ContactData("testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111"));
      app.getContactHelper().submitContactModification();
      app.getContactHelper().gotoHomePage();
    }
}
