package training.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import training.addressbook.model.ContactData;
import training.addressbook.model.Contacts;
import training.addressbook.model.GroupData;
import training.addressbook.model.Groups;

import java.util.Iterator;

import static org.testng.Assert.assertEquals;

public class ContactDeleteFromGroup  extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withName("testname").withLastname("lastname").withEmail("111").withAddress("test address").withPhone("81111111111"));
        }
    }

    @Test
    public void testContactDeleteFromGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contact;
        GroupData group = null;
        boolean contactInGroup = false;

        Iterator<ContactData> iterator = contacts.iterator();
        while ((contact = iterator.next()) != null) {
            Iterator<GroupData> iterator1 = groups.iterator();
            while ((group = iterator1.next()) != null) {
                if (contact.getGroups().contains(group)) {
                    contactInGroup = true;
                    break;
                }
            }
            if (contactInGroup) {
                break;
            }
        }

        if(!contactInGroup) {
            contact = new ContactData().withName("testname").withLastname("lastname").withEmail("111").withAddress("test address").withPhone("81111111111");
            app.goTo().homePage();
            app.contact().addInGroup(contact, group);
        }

        app.goTo().homePage();
        app.contact().deleteFromGroup(contact, group);
        ContactData lastContact = contact;
        ContactData dbContact = app.db().contacts().stream().filter(c ->c.getId() == lastContact.getId()).findFirst().get();
        assertEquals(false, dbContact.getGroups().contains(group));
    }
}
