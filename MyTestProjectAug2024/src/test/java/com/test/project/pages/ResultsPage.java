package com.test.project.pages;

import com.test.project.helpers.Helper;
import com.test.project.runners.Hook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultsPage implements BasePage {

    private static final String RESULTS_LOCATOR = "//*[contains(@data-component-type,'search-results')]//*[text()='Results']";


    @Autowired
    private Helper helper;

    @Autowired
    SearchPage searchPage;

    @Autowired
    private Hook hooks;

    public List<String> getMobilesSearchResults() throws InterruptedException {
        List<String>  phonesResultsListForOurSearch = new ArrayList<>();
        List<String>  phoneResultsLinks = new ArrayList<>();
        List<WebElement> attainedResultsList = hooks.getDriver().findElements(By.xpath("//*[@data-cy='title-recipe']//*[contains(text(),'"+searchPage.getPhoneBrand()+"')]"));
        for(WebElement element:attainedResultsList){
            phonesResultsListForOurSearch.add(element.getText());
            phoneResultsLinks.add(hooks.getDriver().findElement(By.xpath("//*[text()='"+element.getText()+"']//parent::a")).getAttribute("href"));
        }
        for(String object: phoneResultsLinks){
            System.out.println(object);
        }
        return phoneResultsLinks;
    }


    public void assertResultsArePresent(){
        helper.waitForPresenceOf(By.xpath(RESULTS_LOCATOR));
    }
}
