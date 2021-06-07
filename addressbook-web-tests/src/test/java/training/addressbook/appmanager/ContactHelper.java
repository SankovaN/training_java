package training.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import training.addressbook.model.ContactData;

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
    }

    public void selectContact() {
       click(By.name("selected[]"));
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

    public void createContact(ContactData contact) {
        gotoAddNew();
        fillContactForm(contact);
        submitContactForm();
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
