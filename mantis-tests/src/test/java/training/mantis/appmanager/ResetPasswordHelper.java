package training.mantis.appmanager;

import org.openqa.selenium.By;
import training.mantis.model.UserData;

import java.util.List;

public class ResetPasswordHelper extends BaseHelper {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public UserData resetPassword() {
        List<UserData> result = (List<UserData>) app.db().users();
        for (UserData user : result) {
            if (user.getAccess_level() == 25) {                            //добавить проверку условия
                click(By.linkText(user.getUsername()));
            }
            click(By.cssSelector("input[value='Сбросить пароль']"));
            return user;
        }
        return null;
    }


    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }
    
}
