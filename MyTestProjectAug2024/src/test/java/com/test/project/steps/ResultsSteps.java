package com.test.project.steps;

import com.test.project.pages.ResultsPage;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;

public class ResultsSteps {

    @Autowired
    private ResultsPage resultsPage;

    @Then("^results are displayed$")
    public void resultsAreDisplayed() {
        resultsPage.assertResultsArePresent();
    }

    @Then("the user should see the results based on their search")
    public void theUserShouldSeeTheResultsBasedOnTheirSearch() throws InterruptedException {
        resultsPage.assertResultsArePresent();
        assertFalse("the mobiles result list is empty",resultsPage.getMobilesSearchResults().isEmpty());
    }
}
