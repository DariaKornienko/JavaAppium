package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase
{
    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
        this.openWikiWebPage();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Нет метода поворота экрана на платформе " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape()
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Нет метода поворота экрана на платформе " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgraundApp(int seconds)
    {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Нет метода поворота экрана на платформе " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPage()
    {
        if (Platform.getInstance().isMw())
        {
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Нет метода поворота экрана на платформе " + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIos()) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkipButton();
        }
    }
}
