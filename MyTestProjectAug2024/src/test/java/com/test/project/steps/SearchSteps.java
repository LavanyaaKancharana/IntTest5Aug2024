package com.test.project.steps;

import com.test.project.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchSteps {

    @Autowired
    private SearchPage searchPage;

    @Given("User goes to the amazon all menu")
    public void userGoesToTheAmazonAllMenu() throws InterruptedException {
        searchPage.clickOnAllMenu();
    }

    @And("User chooses {string} to shop by department")
    public void userChoosesToShopByDepartment(String deptName) throws InterruptedException {
        searchPage.chooseFromShopByDepartment(deptName);
    }

    @And("User chooses {string} from electronics")
    public void userChoosesFromElectronics(String electronicsName) throws InterruptedException {
        searchPage.chooseFromElectronics(electronicsName);
    }

    @And("User goes to mobiles section to choose {string} phones in the menu")
    public void userGoesToMobilesSectionToChoosePhonesInTheMenu(String phonesType) throws InterruptedException {
        searchPage.chooseMobilesFromPhoneTypes(phonesType);
    }

    @When("the user sets the search criteria with the brand {string}, the camResolution {string}, the modelYear {string} and the priceRange {string}")
    public void theUserSetsTheSearchCriteriaWithTheBrandTheCamResolutionTheModelYearAndThePriceRange(String brand, String cameraResolution, String modelYear, String priceRange) throws InterruptedException {
        searchPage.setCriteriaToChooseAPhone(brand, cameraResolution, modelYear, priceRange);
    }
}
