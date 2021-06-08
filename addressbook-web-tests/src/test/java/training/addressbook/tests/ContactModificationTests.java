package training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import training.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
      if (app.contact().all().size() == 0) {
        app.contact().create(new ContactData().withName("testname").withLastname("lastname").withEmail("nadya_sankova@mail.ru").withAddress("test address").withPhone("81111111111"));
      }
    }

    @Test
    public void testContactModification() {
      Set<ContactData> before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("testname").withLastname("lastname").withEmail("nadya_sankova@mail.ru").withAddress("test address").withPhone("81111111111");
      app.contact().modify(contact);
      Set<ContactData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size());

      before.remove(modifiedContact);
      before.add(contact);
      Assert.assertEquals(before, after);
    }
}
