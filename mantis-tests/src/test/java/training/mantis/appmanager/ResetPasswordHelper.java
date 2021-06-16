package training.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import training.mantis.model.UserData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResetPasswordHelper extends BaseHelper {
    private CloseableHttpClient httpclient;

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();

    }

    public void start(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public UserData resetPassword() {

        //List<UserData> result = app.db().users();
        List<UserData> result = app.db().users();
        for (UserData user : result) {
            if (user.getAccesslevel() == 25) {
                click(By.linkText(user.getUsername()));
                wd.navigate().refresh();
                click(By.cssSelector("input[value='Сбросить пароль']"));
            return user;
        }
    }
    return null;

}



    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }



    /*//public HttpSession(WebDriver wd) {
        this.wd = wd;
    }*/

    public boolean login(String username, String password) throws IOException {
        wd.get(app.getProperty("web.BaseUrl") + "/manage_user_page.php");
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("return", "index.php"));
        params.add(new BasicNameValuePair("username", app.getProperty("web.adminLogin")));
        params.add(new BasicNameValuePair("password", app.getProperty("web.adminPassword")));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }
}
