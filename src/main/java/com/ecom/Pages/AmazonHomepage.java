package com.ecom.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecom.BaseClass.TestBase;

public class AmazonHomepage extends TestBase {

	@FindBy(id = "nav-link-accountList")
	WebElement AccountAndList;

	@FindBy(xpath = "//div[@id='nav-al-wishlist']/a")
	WebElement CreteWishList;

	@FindBy(id = "twotabsearchtextbox")
	WebElement searchBox;

	@FindBy(xpath = "//div[@id='search']//div[@class='sg-col-inner']/div/span[1]")
	WebElement serachResultCount;

	@FindBy(xpath = "//div[@id='reviewsRefinements']/ul/li[1]")
	WebElement fourStarAndUp;

	@FindBy(xpath = "//div[@class='s-main-slot s-result-list s-search-results sg-row']/div[2]//a[1]")
	WebElement firstSerachResult;

	
	@FindBy(id = "priceblock_ourprice")
	WebElement priceBox;
	
	@FindBy(xpath = "//div[@id='buyBoxAccordion']//div[@id='glowContextualIngressPt_feature_div']//a")
	WebElement selectLocationLink;
	
	@FindBy(id = "GLUXZipUpdateInput")
	WebElement enterPinBox;
	
	public AmazonHomepage() {
		PageFactory.initElements(driver, this);
	}

	public void moveToAccountAndList() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(AccountAndList));

		Actions action = new Actions(driver);
		action.moveToElement(AccountAndList).build().perform();
	}

	public void navigateToCreateWishlist() {
		moveToAccountAndList();
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(CreteWishList));
		CreteWishList.click();
	}

	public void searchAProduct(String searchString) {
		searchBox.sendKeys(searchString);
		searchBox.sendKeys(Keys.ENTER);
	}

	public String getSearchResultCount() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(serachResultCount));
		return serachResultCount.getText();
	}
	
	public void selectFourRating() {
		fourStarAndUp.click();
	}
	
	public void clickOnFirstResult() {
		firstSerachResult.click();
	}

	public String getPrice() {
		return priceBox.getText();
	}
	
	public void enterPIN(String Pin) {
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(selectLocationLink));
		selectLocationLink.click();
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(enterPinBox));
		enterPinBox.sendKeys(Pin+Keys.ENTER);
	}

}
