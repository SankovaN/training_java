package training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import training.addressbook.model.ContactData;
import training.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
      if (app.contact().all().size() == 0) {
        app.contact().create(new ContactData().withName("testname").withLastname("lastname").withEmail("nadya_sankova@mail.ru").withAddress("test address").withPhone("81111111111"));
      }
    }

    @Test
    public void testContactModification() {
      Contacts before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("testname").withLastname("lastname").withEmail("nadya_sankova@mail.ru").withAddress("test address").withPhone("81111111111");
      app.contact().modify(contact);
      Contacts after = app.contact().all();
      assertEquals(after.size(), before.size());
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
