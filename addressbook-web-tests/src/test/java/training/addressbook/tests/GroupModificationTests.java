package training.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import training.addressbook.model.GroupData;
import training.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
     app.goTo().groupPage();
     if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
     }
    }

    @Test
    public void testGroupModification() {
     Groups before = app.group().all();
     GroupData modifiedGroup = before.iterator().next();
     GroupData group = new GroupData()
             .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
     app.group().modify(group);
     assertThat(app.group().count(), Matchers.equalTo(before.size()));
     Groups after = app.group().all();
     assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
