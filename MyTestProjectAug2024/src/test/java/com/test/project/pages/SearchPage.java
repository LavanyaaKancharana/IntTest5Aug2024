package com.test.project.pages;

import com.test.project.helpers.Helper;
import com.test.project.runners.Hook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchPage extends Helper implements BasePage {

    @Autowired
    private Hook hooks;

    private String phoneBrand;

    @FindBy(xpath = "//span[@class='hm-icon-label' and text()='All']")
    private WebElement allMenuLabel;

    @FindBy(xpath = "//*[text()='Mobile Phones']")
    private WebElement mobilePhonesLabel;

    @FindBy(css = "#p_123-title > span")
    private WebElement brandsSectionLabel;

    @FindBy(css = "#p_n_feature_thirteen_browse-bin-title > span")
    private WebElement modelYearSectionLabel;

    @FindBy(xpath = "//*[text()='Camera Resolution']")
    private WebElement cameraResolutionSectionLabel;

    @FindBy(xpath = "//*[@id='priceRefinements']//*[text()='Price']")
    private WebElement priceSectionLabel;

    @FindBy(xpath = "//*[@aria-label='Go - Submit price range']/parent::*//*[text()='Go']")
    private WebElement submitPriceRangeGoButton;

    @FindBy(id = "p_36/range-slider_slider-item_lower-bound-slider")
    private WebElement lowerEndPriceSlider;

    @FindBy(id= "p_36/range-slider_slider-item_upper-bound-slider")
    private WebElement upperEndPriceSlider;

    @FindBy(css = "#sp-cc-accept")
    private WebElement acceptCookiesButton;

    @FindBy(css = "#p_36/range-slider > form > div:nth-child(12) > label.a-form-label.sf-range-slider-label.sf-lower-bound-label > span")
    private WebElement lowerBoundPriceLabel;

    @FindBy(css = "#p_36/range-slider > form > div:nth-child(12) > label.a-form-label.sf-range-slider-label.sf-upper-bound-label > span")
    private WebElement upperBoundPriceLabel;

    public String getPhoneBrand(){
        return phoneBrand;
    }

    public void clickOnAllMenu() throws InterruptedException {
        try{
            waitForVisibilityOf(acceptCookiesButton);
            acceptCookiesButton.click();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("navigated to the required url");
        }
        clickElement(allMenuLabel);
        Thread.sleep(2000);
    }

    public void chooseFromShopByDepartment(String shopByDepartmentName) throws InterruptedException {
        String shopByDeptXpath = "//div[text()='shop by department']/parent::li//following-sibling::*//a//*[text()='"+shopByDepartmentName+"']";
        clickOnCustomisedElementUsingXpath(shopByDeptXpath);
    }

    public void chooseFromElectronics(String chooseElectronics) throws InterruptedException {
        String electronicsXpath = "//div[text()='electronics']/parent::li//following-sibling::*//a[text()='"+chooseElectronics+"']";
        waitForPresenceOf(By.xpath(electronicsXpath));
        clickOnCustomisedElementUsingXpath(electronicsXpath);
    }

    public void chooseMobilesFromPhoneTypes(String phoneType) throws InterruptedException {
        String xpathOfEle = "//*[text()='"+phoneType+"']";
        clickOnCustomisedElementUsingXpath(xpathOfEle);
    }

    public void choosePhoneBrand(String brand) throws InterruptedException {
        browserRefresh();
        scrollToElement(brandsSectionLabel);
        String xpathOfEle = "//*[@aria-label='"+brand+"']//*[contains(@class,'navigation-checkbox aok-float-left')]";
        clickOnCustomisedElementUsingXpath(xpathOfEle);
        phoneBrand = brand.trim();
    }

    public void choosePhoneModelYear(String phnModelYear) throws InterruptedException {
        scrollToElement(modelYearSectionLabel);
        String xpathOfEle = "//*[@aria-label='"+phnModelYear+"']//*[contains(@class,'s-navigation-checkbox aok-float-left')]";
        clickOnCustomisedElementUsingXpath(xpathOfEle);
    }

    public void chooseCameraResolution(String cameraResolution) throws InterruptedException {
        waitForVisibilityOf(cameraResolutionSectionLabel);
        scrollToElement(cameraResolutionSectionLabel);
        String xpathOfEle = "//*[@aria-label='"+cameraResolution+"']//*[contains(@class,'s-navigation-checkbox aok-float-left')]";
        clickOnCustomisedElementUsingXpath(xpathOfEle);
    }

    public void choosePhonesPriceRange(String priceRange) throws InterruptedException {
        Thread.sleep(2000);
        scrollToElement(priceSectionLabel);

       Actions actions = new Actions(hooks.getDriver());
        try{
            waitForVisibilityOf(lowerEndPriceSlider);
            actions.dragAndDropBy(lowerEndPriceSlider, 40, 0).perform();

            waitForVisibilityOf(upperEndPriceSlider);
            actions.dragAndDropBy(upperEndPriceSlider, -50, 0).perform();

            clickElement(submitPriceRangeGoButton);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }finally {
            Thread.sleep(2000);
        }

    }

    public void setCriteriaToChooseAPhone(String brand, String cameraResolution, String modelYear, String priceRange ) throws InterruptedException {
        choosePhoneBrand(brand);
        choosePhonesPriceRange(priceRange);
        choosePhoneModelYear(modelYear);
        chooseCameraResolution(cameraResolution);
    }
}
