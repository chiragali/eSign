package com.automation.utility;


import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.omg.CORBA.NO_IMPLEMENT;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.beust.jcommander.Parameter;

import org.openqa.selenium.OutputType;


/**
 * Define Common Webdriver
 */
public class Common_demo {

	public static final String JavaScriptExecutor = null;

	public static void logcase(String msg) {
		
		System.out.println(msg);
		Reporter.log("<br><strong>" + msg + "</strong></br>");
		
	}

	public static void logstep(String msg) {
		
		System.out.println(msg);
		Reporter.log("<strong> <h3 style=\"color:DarkViolet\"> "+msg+"</h3> </strong>");
		
	}

	/*public static void waitforElement(WebDriver driver, WebElement webelement) {
		(new WebDriverWait(driver, 35)).until(ExpectedConditions.visibilityOf(webelement));
	}*/

	public static void type(WebElement webelement, String value) {
		
		webelement.sendKeys(value);
		
	}

	public static void clickOn(WebDriver driver, WebElement webelement)  {
		
		Common_demo.highlight(driver, webelement);
		Common_demo.pause(3);
		webelement.click();
	}

	public static void pause(int sec)  {
		
		try {
			Thread.sleep(sec*1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void highlight(WebDriver driver, WebElement webelement) {
		
		Actions action = new Actions(driver);
		action.moveToElement(webelement).build().perform();
				
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; "
				+ "border: 2px solid red;');", webelement);
		
		
	}

	public static void makescreenshot(WebDriver driver, String screenshotname) {
		
		/*1. Take the Screenshot.
		2. Add Extension
		3. Copy the Screenshot
		   -> Create the ScreenFolder and give the Path of screenshot.
		   -> Create the Screenshit folder.
		   -> Create the object of Screenshot and pass the Parameter.*/
		
		
		File scrshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String namewithextension = screenshotname+".png";
		
		String screenpath = "E:/New Projects/eSign/test-output/Screenshot" + File.separator;
		String screenfolder = "screenshots";
		
		File screenshotfolder = new File(screenpath+screenfolder);
		if(!screenshotfolder.getAbsoluteFile().exists())
		{
			screenshotfolder.mkdir();
		}

		try {
			FileUtils.copyFile(scrshot, new File(screenshotfolder + 
					File.separator + namewithextension).getAbsoluteFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Screenshot Failed ");
		}

	}

	public static void waitforElementClickable(WebDriver driver, WebElement webelement) {

		WebDriverWait wdw = new WebDriverWait(driver, 30);
		wdw.until(ExpectedConditions.elementToBeClickable(webelement));
	//	wdw.wait(ExpectedConditions.elementToBeClickable(webelement))

	}		

	public static void waitForElement(WebDriver driver, WebElement WebElement) {
		WebDriverWait wdw = new WebDriverWait(driver, 30);
		wdw.until(ExpectedConditions.visibilityOf(WebElement));
			}

	public static void switchToNewtabWithUrl(WebDriver driver, String uRL, int tab) {

		((JavascriptExecutor)driver).executeScript("window.open();");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tab));
		driver.get(uRL);

	}							
				
}
