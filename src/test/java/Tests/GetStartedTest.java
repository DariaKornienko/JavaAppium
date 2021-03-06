package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase
{
    @Test
    public void testPassThroughWelcome()
    {
        if (!Platform.getInstance().isIos()) {
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForAddOrEditPreferredLanguagesLink();
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
