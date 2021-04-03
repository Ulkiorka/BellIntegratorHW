package ru.bellintegrator;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class Steps {
    @Step("Шаг 1. Проверка наличия имени: {name}")
    public static void checkContainsName(List<Map<String,Object>> resultSearch, String name, WebDriver driver){
        if(resultSearch.stream().anyMatch(x->x.get("NAME_PAGE").toString().contains(name))){
            Assertions.assertTrue(true);
        }
        else {
            CustomUtils.getScreen(driver);
            Assertions.fail("Не найдено: " + name);
        }
    }

    @Step("Шаг 2. Перейдём по ссылке с текстом {textTitle}")
    public static void goPageText(PageObjectGoogleWithSearch googleWithSearch, String textTitle){
        Assertions.assertTrue(googleWithSearch.goPage(textTitle), "Страница "+textTitle+" не найдена");
    }

    @Step("Шаг 3. Проверка курса {moneyType}")
    public static void checkCourse( String moneyType, PageObjectOpen openPage){
        CustomUtils.getScreen(openPage.getDriver(), openPage.getExchangeRates());
        Assertions.assertTrue(
        Double.parseDouble(
        openPage.getCollectExchangeRates().stream()
                .filter(x->x.get("Валюта обмена").contains(moneyType))
                .findFirst()
                .get().get("Банк покупает").replace(",","."))
                <
                Double.parseDouble(
                        openPage.getCollectExchangeRates().stream()
                                .filter(x->x.get("Валюта обмена").contains(moneyType))
                                .findFirst()
                                .get().get("Банк продает").replace(",","."))
        );

    }

}
