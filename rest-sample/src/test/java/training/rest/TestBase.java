package training.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.Set;


public class TestBase {
     protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    boolean isIssueOpen(int issueId) throws IOException {

        //String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        //String json = getExecutor().execute(Request.Get(app.getProperty("bugify.baseUrl") + ".json")).returnContent().asString();
        //String json = getExecutor().execute(Request.Get(app.getProperty("bugify.baseUrl"))).returnContent().asString();
        //работающий вариант
        //String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues" + "/" + issueId + ".json")).returnContent().asString();
        String json = getExecutor().execute(Request.Get(app.getProperty("bugify.baseUrl") + "/" + issueId + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issueData = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());

       /* for (Issue nextIssue : issueData) {
            if (nextIssue.getState() == 4) {
                return false;
            }
            return true;
        }
        return true;
    }*/
        if (issueData.iterator().next().getState() == 4) {
        return false;
        }
        return true;
    }

       /* MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("mantis.apiUrl")));
        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        int st = issueData.getStatus().getId().intValue();
            if (st == 80) {
            return false;
        } else {
            return true;
        }*/


    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
