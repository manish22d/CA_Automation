package com.ecom.TestCases;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ecom.BaseClass.TestBase;
import com.ecom.Pages.ShopcluesPage;
import com.ecom.Utilities.TestUtility;

public class ShopcluesTest extends TestBase {

	TestUtility testUtil;
	ShopcluesPage shopclues;

	@BeforeTest(alwaysRun = true)
	public void setUp() {
		System.out.println(System.getProperty("os.name"));
		initialization();
		Log.info("Application Launched Successfully");

		shopclues = new ShopcluesPage();
		driver.get(property.getProperty("shopclues"));
		shopclues.closeNotification();
	}

	@Test(priority = 1, enabled = false)
	public void validateUserIsAbleToAddProductToCart(Method method) {
		extentTest = extent.startTest(method.getName());
		shopclues.moveTohomeNKictchen();
		shopclues.clickOnCurtains();

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		shopclues.clickOnFirstResult();

		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));
		shopclues.addToCart();

		System.out.println(shopclues.getNotificationMsg());
		System.out.println(shopclues.isNotificationMsgDisplayed());
	}

	@Test(priority = 1, enabled = true, dataProvider = "productCatagory")
	public void addProductToCart(String productCategory, String product, Method method) {
		extentTest = extent.startTest(method.getName());
		System.out.println(productCategory);
		System.out.println(product);

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(
				By.xpath("//div[@class='main_navigation']//a[contains(text(),'" + productCategory + "')]"))).build()
				.perform();
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(
				driver.findElement(By.xpath("//div[@class='ul_menu_block']//a[contains(text(),'" + product + "')]"))));

		driver.findElement(By.xpath("//div[@class='ul_menu_block']//a[contains(text(),'" + product + "')]")).click();
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size()-1));
		shopclues.clickOnFirstResult();
		
		tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabs.size()-1));
		shopclues.addToCart();

		System.out.println(shopclues.getNotificationMsg());
		System.out.println(shopclues.isNotificationMsgDisplayed());
	}

	@DataProvider(name = "productCatagory")
	public String[][] readTestData() throws ParserConfigurationException, SAXException, IOException {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("testData.xml");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbf.newDocumentBuilder();

		Document doc = dBuilder.parse(is);
		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName("productType");
		int tLength = nodeList.getLength();
		String[][] testData = new String[tLength][2];
		for (int i = 0; i < tLength; i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				testData[i][0] = element.getElementsByTagName("productCategory").item(0).getTextContent();
				testData[i][1] = element.getElementsByTagName("product").item(0).getTextContent();
			}
		}
		return testData;
	}

}
