package selenide.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenide.pageObjects.GoogleMainPage;
import selenide.pageObjects.GoogleSearchResult;
import selenide.pageObjects.OpenMainPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Tests {

    @BeforeEach
    public void option(){
        Configuration.timeout = 6000;
        Configuration.browser="chrome";
        Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        /*
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        WebDriver driver;
        driver = new ChromeDriver(options);
        setWebDriver(driver);
         */
    }

    @Test
    public void firstSelenide(){
        open("https://www.google.ru/");
        $(By.name("q")).setValue("Открытие").pressEnter();
        //$$(By.xpath("//div[@class='g']")).shouldHave(size(6));
        ElementsCollection resultSearch = $$(By.xpath("//div[@class='g']"));
        System.out.println(resultSearch);
        SelenideElement elem = resultSearch.find(text("Открытие (банк) — Википедия"));
        System.out.println("_______");
        System.out.println(elem.getText());
        SelenideElement elemOtkr = $(By.xpath("//div[@class='g']")).shouldHave(text("Банк «Открытие» — вклады, кредитные и дебетовые ..."));
        elemOtkr.$(By.xpath(".//a[@href]")).click();
        switchTo().window(1);
        System.out.println(title());
        System.out.println($(By.xpath("//*[@class='main-page-exchange main-page-info__card']")).getText());
    }

    @Test
    public void otkrSelenide(){
        GoogleMainPage googlePage = open("https://www.google.ru/", GoogleMainPage.class);
        GoogleSearchResult googleSearchResult = googlePage.search("Открытие");
        OpenMainPage openPage = googleSearchResult.goLinkByName("Банк «Открытие» — вклады, кредитные и дебетовые");
        System.out.println(openPage.getCourse("USD","Банк покупает"));
        Assertions.assertTrue(
                openPage.getCourse("USD","Банк покупает")
                        <
                        openPage.getCourse("USD","Банк продает"),
                "Курс покупки USD не меньше курса продажи"
        );
    }

    /*

        source()

        import static com.codeborne.selenide.Screenshots.getScreenShotAsFile;

        File screenshot = getScreenShotAsFile();


        $("#submit").click();


        waitUntil(Condition, milliseconds)
        waitWhile(Condition, milliseconds)



        visible / appear // e.g. $(“input”).shouldBe(visible)
        present / exist // условия присутствия элемента в DOM
        hidden / disappear // not(visible)
        readonly // e.g. $(“input”).shouldBe(readonly)
        name // e.g. $(“input”).shouldHave(name(“fname”))
        value // e.g. $(“input”).shouldHave(value(“John”))
        type // e.g. $(“#input”).shouldHave(type(“checkbox”))
        id // e.g. $(“#input”).shouldHave(id(“myForm”))
        empty // e.g. $(“h2”).shouldBe(empty)
        attribute(name) // e.g. $(“#input”).shouldHave(attribute(“required”))
        attribute(name, value) // e.g. $(“#list li”).shouldHave(attribute(“class”, “active checked”))
        cssClass(String) // e.g. $(“#list li”).shouldHave(cssClass(“checked”))
        focused
        enabled
        disabled
        selected
        matchText(String regex)
        text(String substring)
        exactText(String wholeText)
        textCaseSensitive(String substring)
        exactTextCaseSensitive(String wholeText)



        byText - поиск элемента по точному тексту
        withText - поиск элемента по тексту (подстроке)
        by - поиск элемента по атрибуту
        byTitle - поиск по атрибуту “title”
        byValue - поиск по атрибуту “value”
        */

}
