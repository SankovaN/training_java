package training.rest;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
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
import sun.plugin.util.PluginSysUtil;
import training.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import com.google.gson.Gson;


public class TestBase {

    
    boolean isIssueOpen(int issueId) throws IOException {

        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issueData = new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());

        for (Issue nextIssue : issueData) {
            if (nextIssue.getState() == 4) {
                return false;
            }
            return true;
        }
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
