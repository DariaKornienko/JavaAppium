package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigatorUI;
import lib.ui.ios.IosNavigatorUI;
import lib.ui.mobile_web.MWNavigatorUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigatonUIFactory
{
    public static NavigationUI get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigatorUI(driver);
        } else if (Platform.getInstance().isIos()) {
            return new IosNavigatorUI(driver);
        } else {
            return new MWNavigatorUI(driver);
        }
    }
}
