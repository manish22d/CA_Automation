package com.ecom.TestCases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.BaseClass.TestBase;
import com.ecom.Pages.AmazonHomepage;
import com.ecom.Utilities.TestUtility;

public class AmazonTest extends TestBase {

	AmazonHomepage amazon;
	TestUtility testUtil;

	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void setUp(String browser) {
		System.out.println(System.getProperty("os.name"));
		System.out.println(browser);
		initialization();
		Log.info("Application Launched Successfully");

		amazon = new AmazonHomepage();
		driver.get(property.getProperty("amznURL"));
	}

	@Test(priority = 1, enabled = false)
	public void validateURLNavigation(Method method) {
		extentTest = extent.startTest(method.getName());
		String actualURL = driver.getCurrentUrl();
		assertThat(actualURL, is(equalTo("https://www.amazon.in/")));
	}

	@Test(priority = 1, enabled = false)
	public void validateTitleNavigation(Method method) {
		extentTest = extent.startTest(method.getName());
		String actualTitle = driver.getTitle();
		assertThat(actualTitle, is(equalTo(
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")));
	}

	@Test(priority = 2, enabled = false)
	public void validateCreateWishListNavigation(Method method) {
		extentTest = extent.startTest(method.getName());
		amazon.navigateToCreateWishlist();
		String actualTitle = driver.getTitle();
		assertThat(actualTitle, is(equalTo("Amazon.in")));
	}

	@Test(priority = 2, enabled = false)
	public void validateSearchResult(Method method) throws InterruptedException {
		extentTest = extent.startTest(method.getName());
		amazon.searchAProduct("mi mobile");

		amazon.selectFourRating();
		amazon.clickOnFirstResult();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		System.out.println(amazon.getPrice());
	}

	@Test(priority = 3, enabled = true)
	public void validateDeliveryPinSelected(Method method) {
		extentTest = extent.startTest(method.getName());
		amazon.searchAProduct("mi mobile");

		amazon.selectFourRating();
		amazon.clickOnFirstResult();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		amazon.enterPIN("412101");

		System.out.println("Delivery location is -> " + driver
				.findElement(By.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[2]")).getText());
	}

}
