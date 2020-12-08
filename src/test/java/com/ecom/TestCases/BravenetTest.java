package com.ecom.TestCases;

import org.testng.annotations.BeforeTest;

import com.ecom.BaseClass.TestBase;
import com.ecom.Utilities.TestUtility;

public class BravenetTest  extends TestBase {
	
	TestUtility testUtil;
	
	@BeforeTest(alwaysRun = true)
	public void setUp() {
		System.out.println(System.getProperty("os.name"));
		initialization();
		Log.info("Application Launched Successfully");

		driver.get(property.getProperty("bravenet"));
	}

}
