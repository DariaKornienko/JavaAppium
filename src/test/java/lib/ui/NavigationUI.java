package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUI extends MainPageObject {
    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation()
    {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Не удалось кликнуть по кнопке навигации", 5);
        } else {
            System.out.println("Метод не работает для " + Platform.getInstance().getPlatformVar());
        }
    }
    public void clickMyLists() {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(MY_LISTS_LINK, "Не удалось нажать на кнопку списков", 5);
        } else {
            this.waitForElementAndClick(MY_LISTS_LINK, "Не удалось нажать на кнопку списков", 15);
        }
    }
}
