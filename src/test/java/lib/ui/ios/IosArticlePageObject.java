package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IosArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CLOSE_ONBOARDING_BUTTON = "id:places auth close";
        //MY_LIST_BY_NAME_TPL = "xpath://android.widget.TextView[@text='{MY_LIST_NAME}']";
    }

    public IosArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}