package android;

import org.openqa.selenium.By;

import java.util.logging.Level;

import utils.log.Log;

public class ChromeCustomTabPage extends CommonPage {

    //ugly xpaths...
    //Android 10
    private String username_xpath_10 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View[2]/" +
            "android.view.View/android.view.View[1]/android.widget.EditText";
    private String password_xpath_10 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View[2]" +
            "/android.view.View/android.view.View[2]/android.widget.EditText";
    private String acceptButton_xpath_10 = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
            "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View[2]" +
            "/android.view.View/android.view.View[2]/android.widget.Button";
    private String authorizeButton_xpath_10 = "hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
            "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View[2]/android.widget.Button";
    private String switchUsersButton_xpath_10 = "//android.view.View[@content-desc=\"Switch users to continue\"]/android.widget.Button\n";

    //Android older than 10
    private String username_xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[2]/" +
            "android.view.View/android.view.View[1]/android.widget.EditText";
    private String password_xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[2]" +
            "/android.view.View/android.view.View[2]/android.widget.EditText";
    private String acceptButton_xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
            "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[2]" +
            "/android.view.View/android.view.View[2]/android.widget.Button";
    private String authorizeButton_xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
            "/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]" +
            "/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[2]/android.widget.Button";
    private String switchUsersButton = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/" +
            "android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[3]/android.widget.Button";


    private long deviceVersion;

    public ChromeCustomTabPage(){
        deviceVersion = (long) driver.getCapabilities().getCapability("deviceApiLevel");
    }

    public void enterCredentials(String username, String password){
        Log.log(Level.FINE, "Starts: enter OAuth2 credentials");
        if (deviceVersion >= 29) {
            Log.log(Level.FINE, "Android 10");
            if (!driver.findElementsByXPath(switchUsersButton_xpath_10).isEmpty())
                driver.findElementByXPath(switchUsersButton_xpath_10).click();
            waitByXpath(5, username_xpath_10);
            driver.findElement(By.xpath(username_xpath_10)).sendKeys(username);
            driver.findElement(By.xpath(password_xpath_10)).sendKeys(password);
            driver.findElement(By.xpath(acceptButton_xpath_10)).click();
        } else {
            Log.log(Level.FINE, "Android older than 10");
            if (!driver.findElementsByXPath(switchUsersButton).isEmpty())
                driver.findElementByXPath(switchUsersButton).click();
            waitByXpath(5, username_xpath);
            driver.findElement(By.xpath(username_xpath)).sendKeys(username);
            driver.findElement(By.xpath(password_xpath)).sendKeys(password);
            driver.findElement(By.xpath(acceptButton_xpath)).click();
        }
    }

    public void authorize(){
        Log.log(Level.FINE, "Starts: Authorize OAuth2");
        takeScreenshot("authorize");
        if (deviceVersion >= 29) {
            Log.log(Level.FINE, "Android 10");
            waitByXpath(5, authorizeButton_xpath_10);
            driver.findElement(By.xpath(authorizeButton_xpath_10)).click();
        } else {
            Log.log(Level.FINE, "Android older than 10");
            waitByXpath(5, authorizeButton_xpath);
            driver.findElement(By.xpath(authorizeButton_xpath)).click();
        }
    }
}