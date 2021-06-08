package training.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import training.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("testname", "lastname", "nadya_sankova@mail.ru", "test address", "81111111111");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
