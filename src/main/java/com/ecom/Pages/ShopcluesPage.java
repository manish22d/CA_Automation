package com.ecom.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecom.BaseClass.TestBase;

public class ShopcluesPage extends TestBase {

	@FindBy(xpath = "//div[@class='moe-button-wrapper']/button[1]")
	WebElement dontAllowNotification;

	@FindBy(xpath = "//li[@id='nav_46']/a")
	WebElement homeNKictchen;

	@FindBy(xpath = "//div[@id='column_1']/ul/li/a[contains(text(),'Curtains')]")
	WebElement curtains;

	@FindBy(xpath = "//div[@id='product_list']/div[@class='row'][1]/div[1]/a[2]")
	WebElement fristResult;

	@FindBy(id = "add_cart")
	WebElement addToCartButton;

	@FindBy(xpath = "//div[@id='snackbar']")
	WebElement notification;

	public ShopcluesPage() {
		PageFactory.initElements(driver, this);
	}

	public void closeNotification() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(dontAllowNotification));
		dontAllowNotification.click();
	}

	public void moveTohomeNKictchen() {
//		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(homeNKictchen));

		Actions action = new Actions(driver);
		action.moveToElement(homeNKictchen).build().perform();
	}

	public void clickOnCurtains() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(curtains));
		curtains.click();
	}

	public void clickOnFirstResult() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(fristResult));
		fristResult.click();
	}

	public void addToCart() {
		addToCartButton.click();
	}

	public String getNotificationMsg() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(notification));
		return notification.getText();
	}

	public boolean isNotificationMsgDisplayed() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(notification));
		return notification.isDisplayed();
	}
}
