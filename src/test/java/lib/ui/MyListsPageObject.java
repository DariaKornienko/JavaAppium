package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            REMOVE_FROM_SAVED_BUTTON,
            ARTICLE_BY_TITLE_TPL;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementPresent(folder_name_xpath, "Не найден список " + name_of_folder);
        this.waitForElementAndClick(folder_name_xpath,"Не удалось кликнуть по списку " + name_of_folder,5);
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Статья '" + article_title + "' не найдена", 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Статья '" + article_title + "' осталась в списке", 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);

        if (!Platform.getInstance().isMw()) {
            this.swipeElementToLeft(article_xpath, "Не удалось удалить статью");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Не удалось кликнуть по удалению статьи из сохраненных", 15);
        }

        if (Platform.getInstance().isIos()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Не цдалось найти сохраненню статью");
        }
        if (Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }
}





