package android;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import utils.log.Log;

public class CommonPage {

    protected static AndroidDriver driver = AppiumManager.getManager().getDriver();
    protected Actions actions;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public CommonPage()  {
        actions = new Actions(driver);
    }

    public static void waitByXpath(int timeToWait, String resourceXpath){
        WebDriverWait wait = new WebDriverWait(driver, timeToWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(resourceXpath)));
    }

    public static void waitById(int timeToWait, String resourceId){
        WebDriverWait wait = new WebDriverWait(driver, timeToWait);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id(resourceId)));
    }

    public static void waitByIdInvisible(int timeToWait, String resourceId){
        WebDriverWait wait = new WebDriverWait(driver, timeToWait);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(MobileBy.id(resourceId)));
    }

    public static void waitByTextVisible(int timeToWait, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWait);
        MobileElement mobileElement = (MobileElement)
                driver.findElementByAndroidUIAutomator
                        ("new UiSelector().text(\""+ text +"\");");
        wait.until(ExpectedConditions.textToBePresentInElement(mobileElement, text));
    }

    public static void waitTillStatus(int timeToWait, String resourceId, boolean status){
        WebDriverWait wait = new WebDriverWait(driver, timeToWait);
        wait.until(ExpectedConditions.elementSelectionStateToBe(MobileBy.id(resourceId), status));
    }

    public static void swipe (double startx, double starty, double endx, double endy) {
        Dimension size = driver.manage().window().getSize();
        int startY=(int)(size.height * starty);
        int endY=(int)(size.height * endy);
        int startX=(int)(size.width * startx);
        int endX=(int)(size.height * endx);
        TouchAction ts = new TouchAction(driver);
        ts.longPress(PointOption.point(startX, startY))
                .moveTo(PointOption.point(startX, endY)).release().perform();
    }

    public static void takeScreenshot (String name) {
        try {
            String sd = sdf.format(new Timestamp(System.currentTimeMillis()).getTime());
            File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShotFile, new File("screenshots/"+name+"_"+sd+".png"));
            Log.log(Level.FINE,"Take screenshot " + name + " at: " + sd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}