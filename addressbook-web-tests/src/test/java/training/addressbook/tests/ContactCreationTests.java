package training.addressbook.tests;

import org.testng.annotations.Test;
import training.addressbook.model.ContactData;
import training.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/test.jpg");
        ContactData contact = new ContactData().withName("testname").withLastname("lastname")
                .withEmail("nadya_sankova@mail.ru").withAddress("test address")
                .withPhone("81111111111").withPhoto(photo);
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
