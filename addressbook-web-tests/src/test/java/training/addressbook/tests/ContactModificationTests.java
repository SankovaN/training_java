package training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import training.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
      if (! app.getContactHelper().isThereAContact()) {
          app.getContactHelper().createContact(new ContactData("testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111"));
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().initContactModification();
      ContactData contact = new ContactData(before.get(before.size() -1).getId(),"testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111");
      app.getContactHelper().fillContactForm(contact);
      app.getContactHelper().submitContactModification();
      app.getContactHelper().gotoHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size());

      before.remove(before.size() - 1);
      before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) ->Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
      Assert.assertEquals(before, after);
    }
}
