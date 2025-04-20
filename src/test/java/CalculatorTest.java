import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {
    public static AndroidDriver driver;
    public static DesiredCapabilities dcs;

    @BeforeClass
    public void setUp(){
        dcs = new DesiredCapabilities();
        dcs.setCapability("deviceName", "emulator-5554");
        dcs.setCapability("platformName", "Android");
        dcs.setCapability("platformVersion", "9.0");
        dcs.setCapability("appPackage","com.android.calculator2");
        dcs.setCapability("appActivity","com.android.calculator2.Calculator");
        dcs.setCapability("adbExecTimeout", 30000); // Increase timeout to 30 seconds (default is 20s)
        dcs.setCapability("newCommandTimeout", 600);  // Increase timeout for commands

        try{
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),dcs);
        } catch (MalformedURLException mue) {
            mue.getStackTrace();
        }
    }


    @Test(priority = 1)
    public void additionTest(){
        driver.findElement(By.id("com.android.calculator2:id/digit_8")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();
        String actualResult = driver.findElement(By.id("com.android.calculator2:id/result")).getText();
        Assert.assertEquals(actualResult,"17","Addition error");
    }

    @Test(priority = 2)
    public void subtractionTest(){
        driver.findElement(By.id("com.android.calculator2:id/digit_8")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_sub")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();
        String actualResult = driver.findElement(By.id("com.android.calculator2:id/result")).getText();
        Assert.assertEquals(actualResult,"−1","Subtraction error");
    }

    @Test(priority = 3)
    public void multiplicationTest(){
        driver.findElement(By.id("com.android.calculator2:id/digit_8")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_mul")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();
        String actualResult = driver.findElement(By.id("com.android.calculator2:id/result")).getText();
        Assert.assertEquals(actualResult,"72","Multiplication error");
    }

    @Test(priority = 4)
    public void divisionTest(){
        driver.findElement(By.id("com.android.calculator2:id/digit_8")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_div")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();
        String actualResult = driver.findElement(By.id("com.android.calculator2:id/result")).getText();
        Assert.assertEquals(actualResult.substring(0,5),"0.888","Division error");
    }

    @AfterMethod
    public void clear(){
        driver.findElement(AppiumBy.accessibilityId("clear")).click();
    }
}
