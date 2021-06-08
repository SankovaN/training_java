package training.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import training.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void gotoHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactForm() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhone());
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void selectContactById(int id) {

        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void gotoAddNew() {

        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        gotoAddNew();
        fillContactForm(contact);
        submitContactForm();
        gotoHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModification();
        fillContactForm(contact);
        submitContactModification();
        gotoHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            element.findElements(By.tagName("td"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector("[name='entry']>.center>input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withName(firstname).withLastname(lastname));
        }
        return contacts;
    }

}
