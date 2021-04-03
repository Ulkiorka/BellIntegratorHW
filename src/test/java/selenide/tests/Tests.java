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
    }

    @Test
    public void firstSelenide(){
        open("https://www.google.ru/");
        $(By.name("q")).setValue("Открытие").pressEnter();
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

}
