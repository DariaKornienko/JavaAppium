package Tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigatonUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    private static final String
            name_of_folder = "Test",
            login = "DariaTest123",
            password = "Qweasd123";

    @Test
    public void testEx5SavingTwoArticles() {
        //Первая статья
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String title_before = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList();
            ArticlePageObject.newMyListName(name_of_folder);
        } else  if (Platform.getInstance().isIos()){
            ArticlePageObject.addArticleToMySaved();
            ArticlePageObject.closeOnboardingButton();
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("После логина не та страница", title_before, ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        //Вторая статья
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("sland of Indonesia");
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList();
            ArticlePageObject.myListName(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigatonUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }

        MyListsPageObject.swipeByArticleToDelete("sland of Indonesia");


        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String title_after = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Заголовок оставшейся статьи не совпадает сзаголовком первой стаьи",
                title_after,
                title_before
        );
    }
}