package training.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import training.mantis.model.MailMessage;
import training.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword () throws IOException, MessagingException {
        /*long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost", now);
        app.james().createUser(user, password);
        app.registration().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 1000);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
        String confirmationLink =  findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));*/

        long now = System.currentTimeMillis();
        //String user = String.format("user%s", now);
        String password1 = "newpassword";
        //String email = String.format("user%s@localhost", now);
        //app.james().createUser(user, password);
        app.reset().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        //app.james().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        UserData value = app.reset().resetPassword();
        String email = value.getEmail();
        String user = value.getUsername();
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        //List<MailMessage> mailMessages = app.james().waitForMail(user, password1, 60000);
        String confirmationLink =  findConfirmationLink(mailMessages, email);
        //app.registration().finish(confirmationLink, password1);
        app.reset().finish(confirmationLink, password1);
        assertTrue(app.newSession().login(user, password1));
    }

    private String  findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
