package ru.bellintegrator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageObjectBell {
    private WebDriver chromeDriver;
    WebElement searchField;
    WebElement searchButton;
    List<WebElement> news;

    public List<WebElement> getNews() {
        return chromeDriver.findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//*[@class=\"short__desc\"]"));
    }

    PageObjectBell(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
        searchField = chromeDriver.findElement(By.xpath("//*[@id=\"input-search\"]"));
        searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"button-search\"]"));
    }

    public void find(String wordFind){
        searchField.click();
        searchField.sendKeys(wordFind);
        searchButton.click();
    }

}
