package com.automation.verification;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.automation.indexpage.PackageIndexpage;
import com.automation.indexpage.PackageIndexpage_demo;
import com.automation.init.AbstractPage;
import com.automation.utility.Common;
import com.automation.utility.Common_demo;
import com.automation.utility.TestData;

public class PackageVerification_demo extends AbstractPage{

	public PackageVerification_demo(WebDriver driver) {
		super(driver);
	}

	
	@FindBy (xpath="//*[@id='user_fullname']")
	WebElement userName;
	
	public boolean verifyUser() {
		
		Common_demo.waitForElement(driver, userName);
		
		if(userName.getText().trim().equalsIgnoreCase(TestData.getSenderName())) 
		{
			return true;
		}
		return false;
	}

	@FindBy (xpath="//table[@id='packages']//tr[1]//td[1]")
	WebElement pkgName;
	
	public boolean verifyPkg() {
		
		Common_demo.logcase(pkgName.getText());
		Common_demo.logcase(packageIndexpage_demo.RefName);
				
		if(pkgName.getText().contains(packageIndexpage_demo.RefName))
		{
			return true;
		}	
		return false;
	}

	

}
