package ru.bellintegrator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PageFactoryBell {
    private WebDriver chromeDriver;

    @FindBy(how = How.ID, id="input-search")
    WebElement searchField;

    @FindBy(how= How.ID, id="button-search")
    WebElement searchButton;

    @FindAll(@FindBy(how = How.XPATH, using="//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]"))
    List<WebElement> news;

    public List<WebElement> getNews() {
        return news;
    }

    public PageFactoryBell(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
    }

    public void find(String wordFind){
        searchField.click();
        searchField.sendKeys(wordFind);
        searchButton.click();
    }


}
