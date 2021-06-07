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
}
