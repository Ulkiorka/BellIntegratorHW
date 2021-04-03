package ru.bellintegrator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageObjectGoogleWithSearch {

    private String selectorSearchItems="//div[@class='g']";
    private String selectorURL = ".//div[@class='r']/a[@href]";
    private String selectorNamePage = ".//div[@class='r']/a[@href]";
    private String selectorDiscriprion = ".//div[@class='s']";

    private WebDriver driver;

    private List<WebElement> searchItsems = new ArrayList<>();
    private List<Map<String,Object>> collectResults = new ArrayList<>();

    public PageObjectGoogleWithSearch(WebDriver driver, String search){
        this.driver=driver;
        this.driver.get("https://www.google.ru/search?q="+search);
        searchItsems=driver.findElements(By.xpath(selectorSearchItems));
    }

    public PageObjectGoogleWithSearch(WebDriver driver){
        this.driver=driver;
        searchItsems=driver.findElements(By.xpath(selectorSearchItems));
    }

    public List<Map<String, Object>> getCollectResults() {
        for(WebElement result : searchItsems){
            collectResults.add(Map.of(
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectorURL)).getAttribute("href"),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DISCRIPTION", result.findElement(By.xpath(selectorDiscriprion)).getText()
            ));
        }
        return collectResults;
    }
    public boolean goPage(String namePage){
        WebElement pageLink = (WebElement) collectResults.stream()
                .filter(x->x.get("NAME_PAGE").toString().contains(namePage))
                .findFirst()
                .get().get("WEB_ELEMENT");
        pageLink.findElement(By.xpath(selectorNamePage)).click();
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for(String tab : tabs){
            driver.switchTo().window(tab);
            if(driver.getTitle().contains(namePage))
                return true;
        }
        return false;
    }

}
