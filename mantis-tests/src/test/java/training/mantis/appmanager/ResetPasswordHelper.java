package training.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends BaseHelper {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword(String id) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        selectUserId(id);
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }

    private void selectUserId(String id) {
        //wd.findElement(By.cssSelector("a[href='edit.php?id=" + id +"']")).click();
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id +"']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
    
}
