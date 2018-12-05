package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends  MainPageObject
{
    private static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wploginattempt";

    public AuthorizationPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public  void clickAuthButton()
    {
        this.waitForElementPresent(LOGIN_BUTTON, "Нет кнопки логина", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Нет клика по кнопке логина", 5);
    }

    public void enterLoginData(String login, String password)
    {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Не удалось ввести логин", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Не удалось ввести пароль", 5);
    }

    public void submitForm()
    {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Нет клика по кнопке сабмита", 5);
    }
}
