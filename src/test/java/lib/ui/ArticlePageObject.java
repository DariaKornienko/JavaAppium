package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            MY_LIST_BY_NAME_TPL,
            CLOSE_ONBOARDING_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    private static String getListXpathByName(String name_of_list)
    {
        return MY_LIST_BY_NAME_TPL.replace("{MY_LIST_NAME}", name_of_list);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Не найден заголовок на странице", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIos()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT,"Не найден конец страницы",70);
        } else if (Platform.getInstance().isIos()){
            this.swipeUpTitleElementAppear(FOOTER_ELEMENT,"Не найден конец страницы",70);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,"Не найден конец страницы",70);
        }
    }


    public void addArticleToMyList()
    {
        this.waitForElementPresent(
                OPTIONS_BUTTON,"Не найдена кнопка дополнительных опций",15);

        this.waitForElementAndClick(
                OPTIONS_BUTTON,"Не найдена кнопка дополнительных опций",15);

        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,"Не найдена кнопка дополнительных опций",15);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,"Не найдена кнопка дополнительных опций",15);
    }

    public void newMyListName(String name_of_folder)
    {
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,"Не найдена кнопка подтверждения",15);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,"Не удалось очистить поле ввода",5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,name_of_folder,"Не удалось ввести название списка",15);

        this.waitForElementAndClick(MY_LIST_OK_BUTTON,"Не найдена кнопка ОК",5);
    }

    public void myListName(String name_of_folder)
    {
        String my_list_name = getListXpathByName(name_of_folder);
        this.waitForElementPresent(my_list_name, "Не удалось найти папку '" + my_list_name + "'");
        this.waitForElementAndClick(my_list_name,"Папка не найдена",15 );
    }

    public void addArticleToMySaved()
    {
        if (Platform.getInstance().isMw()) {
            this.deleteArticleFromMySaved();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Не удалось добавить статью в список", 10);
    }

    public void deleteArticleFromMySaved()
    {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Не кликнуть на удаление статьи", 5);
        }
        this.waitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Не удалось найти кнопку добавления после удаления статьи, 5");
    }

    public void closeOnboardingButton()
    {
        this.waitForElementAndClick(CLOSE_ONBOARDING_BUTTON, "Не удалось закрыть онбординг", 10);
    }

    public void closeArticle()
    {
        if (!Platform.getInstance().isMw()) {
            this.waitForElementPresent(CLOSE_ARTICLE_BUTTON, "Не найден крестик");
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Не удалось закрыть статью", 15);
        } else {
            System.out.println("Не требуется закрывать статью");
        }
    }

    public void assertPageTitle()
    {
        this.assertElementPresent(TITLE,"Заголовок статьи не найден");
    }
}
