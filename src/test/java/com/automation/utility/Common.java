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
import org.openqa.selenium.OutputType;


/**
 * Define Common Webdriver
 */
public class Common {

	Date date = new Date();
	protected static Wait<WebDriver> wait;
	public static String alerttext;

	/**
	 * Find web-element for given locator.
	 * 
	 * @param elementName
	 * @return
	 */
	public static WebElement findElement(WebDriver driver, String elementName) {

		String locator;

		locator = elementName;

		int count = 0;
		while (count < 4) {
			try {
				if (locator.startsWith("link=") || locator.startsWith("LINK=")) {
					locator = locator.substring(5); // remove "link=" from
													// locator
					try {
						if (locator.contains(" "))
							return driver.findElement(By
									.partialLinkText(locator));

						return driver.findElement(By.linkText(locator));
					} catch (Exception e) {
						return null;
					}
				}
				if (locator.startsWith("id=")) {
					locator = locator.substring(3); // remove "id=" from locator
					try {
						return driver.findElement(By.id(locator));
					} catch (Exception e) {
						return null;
					}
				} else if (locator.startsWith("//")) {
					try {
						return driver.findElement(By.xpath(locator));
					} catch (Exception e) {
						return null;
					}
				} else if (locator.startsWith("css=")) {

					locator = locator.substring(4); // remove "css=" from
													// locator
					try {
						return driver.findElement(By.cssSelector(locator));
					} catch (Exception e) {
						return null;
					}
				} else if (locator.startsWith("name=")) {

					locator = locator.substring(5); // remove "name=" from
													// locator
					try {
						return driver.findElement(By.name(locator));
					} catch (Exception e) {
						return null;
					}
				} else {
					try {
						return driver.findElement(By.id(locator));
					} catch (Exception e) {
						return null;
					}

				}
			} catch (StaleElementReferenceException e) {
				e.toString();

				count = count + 1;
				// System.out.println("Trying["+
				// count+"] to recover from a stale element :" +
				// e.getMessage());
			}
			count = count + 4;
		}
		return null;
	}

	public static void moveToObjectelement(WebDriver driver, String xpath) {

		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));

	}

	/**
	 * Perform horizontal scrolling
	 * 
	 * @param driver
	 * @param element
	 */
	public static void scrollToHorizontal(WebDriver driver, WebElement element) {

		Actions dragger = new Actions(driver);
		WebElement draggablePartOfScrollbar = element;

		// drag downwards
		int numberOfPixelsToDragTheScrollbarDown = 50;
		for (int i = 10; i < 500; i = i + numberOfPixelsToDragTheScrollbarDown) {
			try {
				// this causes a gradual drag of the scroll bar, 10 units at a
				// time
				dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
						.moveByOffset(numberOfPixelsToDragTheScrollbarDown, 0)
						.release().perform();
				Thread.sleep(1000L);
			} catch (Exception e1) {

			}
		}
	}

	/**
	 * Perform vertical scrolling
	 * 
	 * @param driver
	 * @param element
	 */
	public static void scrollToVertical(WebDriver driver, WebElement element) {

		Actions dragger = new Actions(driver);
		WebElement draggablePartOfScrollbar = element;

		// drag downwards
		int numberOfPixelsToDragTheScrollbarDown = 50;
		for (int i = 10; i < 500; i = i + numberOfPixelsToDragTheScrollbarDown) {

			try {
				// this causes a gradual drag of the scroll bar, 10 units at a
				// time
				dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
						.moveByOffset(0, numberOfPixelsToDragTheScrollbarDown)
						.release().perform();
				Thread.sleep(1000L);
			} catch (Exception e1) {

			}
		}

	}

	/**
	 * Checks checkbox or toggle element.
	 * 
	 * @param element
	 *            Checkbox element.
	 */

	public static void checkChkBox(WebElement element) {

		boolean isCheckBoxSet;
		isCheckBoxSet = element.isSelected();
		if (!isCheckBoxSet) {
			element.click();
		}

	}
	
	public static void movetoalert(WebDriver webDriver) {

		 try {
		        Alert alert = webDriver.switchTo().alert();
		        alerttext = alert.getText();
		    } catch (Exception e) {
		    }
	}

	/**
	 * Open Mailinator
	 * 
	 * @param emailAddress
	 */
	public static void openMailinator(WebDriver driver, String emailAddress) {
		pause(3);
		String emailParsed[] = emailAddress.split("@");
		String url = "http://" + emailParsed[0] + ".mailinator.com";
		goToUrl(driver, url);
	}

	/**
	 * Gets current time in the following format Month, Date, Hours, Minutes,
	 * Seconds, Millisecond.
	 * 
	 * @return Current date.
	 */
	public static String getCurrentTimeStampString() {

		java.util.Date date = new java.util.Date();

		SimpleDateFormat sd = new SimpleDateFormat("MMddHHmmssSS");
		TimeZone timeZone = TimeZone.getDefault();
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(timeZone
				.getOffset(date.getTime()), "GMT"));
		sd.setCalendar(cal);
		return sd.format(date);
	}

	/**
	 * Takes screenshot and adds it to TestNG report.
	 * 
	 * @param driver
	 *            WebDriver instance.
	 */
	public static void makeScreenshot(WebDriver driver, String screenshotName) {

		WebDriver augmentedDriver = new Augmenter().augment(driver);

		/* Take a screenshot */
		File screenshot = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		String nameWithExtention = screenshotName + ".png";

		/* Copy screenshot to specific folder */
		try {
			/*
			 * String reportFolder = "target" + File.separator +
			 * "failsafe-reports" + File.separator + "firefox" + File.separator;
			 */
			String reportFolder = "E:/Project/eSign/test-output/Screenshot" + File.separator;
			String screenshotsFolder = "screenshots";
			File screenshotFolder = new File(reportFolder + screenshotsFolder);
			if (!screenshotFolder.getAbsoluteFile().exists()) {
				screenshotFolder.mkdir();
			}
			FileUtils.copyFile(screenshot, new File(screenshotFolder
					+ File.separator + nameWithExtention).getAbsoluteFile());
		} catch (IOException e) {
			log("Failed to capture screenshot: " + e.getMessage());
		}
		log(getScreenshotLink(nameWithExtention, nameWithExtention)); // add
																		// screenshot
																		// link
																		// to
																		// the
																		// report
	}

	/**
	 * Log given message to Reporter output.
	 * 
	 * @param msg
	 *            Message/Log to be reported.
	 */
	public static void log(String msg) {
		System.out.println(msg);
		Reporter.log("</br>&nbsp;&nbsp;&nbsp;&nbsp;"+msg+"</br>");
	}
	
	public static void logcase(String msg) {
		System.out.println(msg);
		Reporter.log("<strong> <h3 style=\"color:DarkViolet\"> " + msg
				+ "</h3> </strong>");

	}

	public static void logstep(String msg) {
		System.out.println(msg);
		Reporter.log("<br><strong>" + msg + "</strong></br>");
	}
	
	public static void AssertPassed() {
		System.out.println();
		Reporter.log("<strong> <h3 style=\"color:DarkGreen\">  &#10004; <i>  SUCCESSFUL </i></h3> </strong> ");

	}
	
	public static void AssertFailed() {
		System.out.println();
		Reporter.log("<strong> <h3 style=\"color:DarkRed\"> &#10008; <i> UNSUCCESSFUL </i></h3> </strong>");

	}

	
	public static void logOptionalAssert(String msg) {
		System.out.println(msg);
		Reporter.log("<h3><b style='color:#E27A12'>&nbsp;&nbsp;&nbsp;"+msg + "</h3></b>");

	}
	
	public static void logMandetoryAssert(String msg) {
		System.out.println(msg);
		Reporter.log("<h3><b style='color:#1466B8'>&nbsp;&nbsp;&nbsp;"+msg + "</h3></b>");

	}

	public static void logStatus(String Status) {
           System.out.println(Status);
		if (Status.equalsIgnoreCase("Pass")) {
			log("<hr size='15px' noshade color='green'>");
		} else if (Status.equalsIgnoreCase("Fail")) {
			log("<hr size='15px' noshade color='red'>");
		}
	}

	/**
	 * Generates link for TestNG report.
	 * 
	 * @param screenshot_name
	 *            Screenshot name.
	 * @param link_text
	 *            Link text.
	 * @return Formatted link for TestNG report.
	 */
	public static String getScreenshotLink(String screenshot_name,
			String link_text) {

		log("<br><Strong><font color=#FF0000>--Failed</font></strong>");
		return "<a href='screenshots/" + screenshot_name
				+ "' target='_new'>" + link_text + "</a>";
	}

	/**
	 * Checks whether the needed WebElement is displayed or not.
	 * 
	 * @param element
	 *            Needed element
	 * 
	 * @return true or false.
	 */
	public static boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementNotDisplayed(WebElement element) {
		try {
			return !element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Wait(max. 1 minute) till given element does not disappear from page.
	 * 
	 * @param by
	 *            Locator of element.
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean waitForElementIsDisplayed(WebElement by)
			throws InterruptedException {

		for (int second = 0;; second++) {
			if (second >= 60) {

				break;
			}
			try {
				if (isElementDisplayed(by))
					break;
			} catch (Exception e) {
			}
			pause(1);
		}
		return false;
	}

	/**
	 * Checks if given elements is checked.
	 * 
	 * @param locator
	 *            Locator of element.
	 * 
	 * @return true if checked else false.
	 */
	public static boolean isChecked(WebDriver driver, String locator) {

		return findElement(driver, locator).isSelected();

	}

	/**
	 * Checks whether the needed WebElement is displayed or not.
	 * 
	 * @param elementLocator
	 * @return
	 */
	public static boolean isElementDisplayed(WebDriver driver, By elementLocator) {
		try {
			return driver.findElement(elementLocator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Set data in to clipboard
	 * 
	 * @param string
	 */
	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}

	/**
	 * Checks whether the visibility of Element Located
	 * 
	 * @param by
	 * @return
	 */
	public static ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
		return new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				return element.isDisplayed() ? element : null;
			}
		};
	}

	/**
	 * Wait up to String locator present
	 * 
	 * @param selector
	 */
	public static void waitForElement(WebDriver driver, String xpath) {
		wait = new WebDriverWait(driver, 60);
		try {
			wait.until(visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
		}
	}

	/**
	 * Finds handle to second window other than given handle to current window
	 * and switches to as well.
	 * 
	 * @param handleCurrentWindow
	 * @return handleSecondWindow
	 */
	public static String findAndSwitchToSecondWindow(WebDriver driver,
			String handleCurrentWindow) {
System.out.println("***********************");
		pause(1000);
		//Set<String> windows = driver.getWindowHandles();
		//System.out.println("Size = "+windows.size());
		String handleSecondWindow = "";
		for (String window :  driver.getWindowHandles()) {
		
			//if (!window.contains(handleCurrentWindow)) {
				System.out.println("Switchin to..... "+window);
				//handleSecondWindow = window;
				driver.switchTo().window(handleSecondWindow);
			//}
		}

		// Switch to the second window.
		/*try {

			pause(2000);

			//driver.switchTo().window(handleSecondWindow);

		} catch (Throwable failure) { // If there is problem in switching
										// window, then re-try.

			pause(1000);

			driver.switchTo().window(handleSecondWindow);

		}*/

		return handleSecondWindow;

	}

	/**
	 * Select data from dropwon or combobox by Value.
	 * 
	 * @param element
	 * @param value
	 */
	public static void selectFromCombo(WebElement element, String value) {
		
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * Select data form dropdown or combobox by visible element
	 * 
	 * @param element
	 * @param value
	 */
	public static void selectFromComboByVisibleText(WebElement element,
			String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		
	}
	
	/**
	 * Select data form dropdown or combobox by Index
	 * 
	 * @param element
	 * @param value
	 * @return 
	 */
	
	public static String selectFromComboByIndex(WebElement element, int value) {
		
		Select select = new Select(element);
		select.selectByIndex(value);
		return select.getFirstSelectedOption().getText();
	}

	/**
	 * Wait up to By element present
	 * 
	 * @param element
	 */
	public static void waitForElement(WebDriver driver, By element) {

		try {
			wait = new WebDriverWait(driver, 750);
			wait.until(visibilityOfElementLocated(element));
		} catch (Exception e) {
		}
	}

	/**
	 * Clicks on visible or not visible element.
	 * 
	 * @param element
	 *            Web element.
	 */

	public static void jsClick(WebDriver driver, WebElement element) {
		highlightElement(driver, element);
		System.out.println("============Apply NOw:::::::::Test::::::"+element.getText());
		((JavascriptExecutor) driver).executeScript(
				"return arguments[0].click();", element);
		// this.waitForAjax("0");
	}

	/**
	 * Highlight the element and click on same
	 * 
	 * @param driver
	 * @param element
	 */
	public static void clickOn(WebDriver driver, WebElement element) {
		highlightElement(driver, element);
		waitForElementClickable(driver, element);
		element.click();
		FluentwaitForElementClickable(driver, element);// For Firefox
	}

	/**
	 * Generates random symbols;
	 * 
	 * @param length
	 *            Length of the generated symbols.
	 * 
	 * @return StringBuffer object.
	 */
	public static String generateRandomChars(int length) {
		String random = RandomStringUtils.randomAlphabetic(length);
		return random;
	}

	public static String generateRandomNumeric(int length) {
		String random = RandomStringUtils.randomNumeric(length);
		return random;
	}
	
	/**
	 * Generate Random Number in Length
	 * 
	 * @param length
	 * @return
	 */
	public static int generateRandomNumber(int length) {

		Random rand = new Random();
		int numNoRange = rand.nextInt();
		return numNoRange;

	}

	/**
	 * Mouse Hover in Web element
	 * 
	 * @param element
	 */
	public static void mouseOver(WebDriver driver, WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();

	}

	/**
	 * Get text in a given element.
	 * 
	 * @param elementName
	 *            Locator of element.
	 * 
	 * @return text in given element.
	 */
	public static String getText(WebDriver driver, String elementName) {

		String text;

		try {
			text = findElement(driver, elementName).getText();

		} catch (Exception e) {

			text = "Element was not found";
		}

		return text;
	}

	/**
	 * 
	 * Get text in of given Element using JavaScript
	 * 
	 * @param driver
	 * @param element
	 *            webElement
	 * @return
	 */
	public static String getTextJS(WebDriver driver, WebElement element) {
		return (String) ((JavascriptExecutor) driver).executeScript(
				"return jQuery(arguments[0]).text();", element);
	}

	/**
	 * Get value of given element dynamically.
	 * 
	 * @param locator
	 *            Locator of element.
	 * 
	 * @return Dynamic value.
	 */
	public String getValue(WebDriver driver, String locator) {

		return findElement(driver, locator).getAttribute("value");
	}

	/**
	 * Checks if given element is being displayed on page.
	 * 
	 * @param elementName
	 *            Locator of element.
	 * 
	 * @return true if displayed else false.
	 */
	public static boolean isElementDisplayed(WebDriver driver,
			String elementName) {

		WebElement webElement;
		try {
			webElement = findElement(driver, elementName);
			return webElement.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Wait till given element is present.
	 * 
	 * @param locator
	 *            Locator of element.
	 */
	public static void waitForConditionIsElementPresent(WebDriver driver,
			String locator) {

		for (int second = 0;; second++) {
			if (second >= 10) {
				break;
			}

			try {
				if (isElementPresent(driver, locator))
					break;
			} catch (Throwable failure) {
			}

			pause(1000);
		}

	}

	/**
	 * Checks if element loaded in browser memory.
	 * 
	 * @param locator
	 *            Locator of element.
	 * @return true if loaded else false.
	 */
	public static boolean isElementPresent(WebDriver driver, String locator) {

		WebElement webElement = findElement(driver, locator);
		if (webElement != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Pauses for given seconds.
	 * 
	 * @param secs
	 */
	public static void pause(int secs) {
		try {
			Thread.sleep(secs * 1000);
		} catch (InterruptedException interruptedException) {

		}
	}

	/**
	 * Get random numeric of given lenth.
	 * 
	 * @param length
	 *            desired length.
	 * @return
	 */
	public static int randomNumericValueGenerate(int length) {

		Random randomGenerator = new Random();

		int randomInt = randomGenerator.nextInt(length);
		return randomInt;
	}

	/**
	 * Clears and type new value into given text-box.
	 * 
	 * @param Web
	 *            Element Locator of element.
	 * 
	 * @param value
	 *            New text/value.
	 */
	public static void type(WebElement webElement, String value) {
		webElement.clear();
		webElement.sendKeys(value);
	}

	public static void typekeys1(WebElement webElement, Keys escape) {
		webElement.clear();
		webElement.sendKeys(escape);
	}
	
	
	public static void type1(WebElement webElement, String value) {
	
		webElement.sendKeys(value);
	}
	
	/**
	 * Wait till all ajax calls finish.
	 * 
	 * @param num
	 *            Number of ajax calls to finish.
	 */
	public static void waitForAjax(WebDriver driver, String num) {

		String ajax;

		ajax = ajaxFinised(driver, num);

		for (int second = 0;; second++) {
			if (second >= 20) {
				break;
			} else if (ajax.equals("true")) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Wait till ajax call finish.
	 * 
	 * @throws InterruptedException
	 */
	public void waitForAjax(WebDriver driver) throws InterruptedException {

		String ajax;
		ajax = ajaxFinised(driver, "1");

		for (int second = 0;; second++) {
			if (second >= 15) {
				break;
			} else if (ajax.equals("true")) {
				break;
			}
			Thread.sleep(1000);
		}

	}

	/**
	 * Checks that all ajax calls are completed on page.
	 * 
	 * @param num
	 *            Number of ajax calls to wait for completion.
	 * 
	 * @return "true" if completed else "false".
	 */
	public static String ajaxFinised(WebDriver driver, String num) {

		Object isAjaxFinished;

		JavascriptExecutor js = (JavascriptExecutor) driver;

		isAjaxFinished = js.executeScript("return jQuery.active == " + num);

		return isAjaxFinished.toString();

	}

	/**
	 * Select Random String From Combobox.
	 * 
	 * @param by
	 * @param driver
	 * @return selected random string
	 * @throws InterruptedException
	 */
	public static String selectRandomOptionFromCombo(By by, WebDriver driver)
			throws InterruptedException {
		String selectedOption = "";
		WebElement selectCombo = driver.findElement(by);
		Thread.sleep(2);
		List<WebElement> getAllOption = selectCombo.findElements(By
				.xpath("option"));
		ArrayList<String> arrayOfAllOption = new ArrayList<String>();
	
		for (WebElement ele : getAllOption) {
			
			if (!ele.getText().startsWith("Select")) {
				arrayOfAllOption.add(ele.getText());
			}

		}
	//	System.out.println(" webel  "+getAllOption.size());
	//	System.out.println(" list  "+arrayOfAllOption.size());
		/*for(String a : arrayOfAllOption)
		{
			System.out.println(a);
		}*/
		
		
		int index = new Random().nextInt(arrayOfAllOption.size());
		
	
		if (Integer.signum(index) == -1) {
			index = -index;
			// index=Math.abs(index);
		}
		selectedOption = arrayOfAllOption.get(index);
		System.out.println("Selected Option Is----====>" + selectedOption);
		return selectedOption;
	}

	/**
	 * Get Total Number Of Elements
	 * 
	 * @param driver
	 * @param by
	 * @return interger number of total elements
	 */
	public static int getNumOfElements(WebDriver driver, By by) {
		int i = 0;
		List<WebElement> ele = driver.findElements(by);
		i = ele.size();
		System.out.println("Total Number Of Elements Are >>> " + i);
		return i;
	}

	/**
	 * Refresh Current Page
	 * 
	 * @param driver
	 */
	public static void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * Open URL in New Window
	 * 
	 * @param driver
	 * @param url
	 */
	public static void openUrlInNewTab(WebDriver driver, String url) {
		System.out.println("--------->" + System.getProperty("os.name"));
		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND + "t");
		} else {
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "t");
		}
		driver.get(url);
	}

	/**
	 * Close Current Tab In Web Browser
	 * 
	 * @param driver
	 */
	public static void closeCurrentTab(WebDriver driver) {

		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			driver.findElement(By.tagName("body")).sendKeys(Keys.COMMAND + "w");
		} else {
			driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL + "w");
		}

	}

	/**
	 * Perform Mouse Hover on element
	 * 
	 * @param driver
	 * @param ele
	 */
	public static void mouseHover(WebDriver driver, WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
	}

	/**
	 * Perform Mouse Hover using java sript executer
	 * 
	 * @param driver
	 * @param ele
	 */
	public static void mouseHoverUsingJS(WebDriver driver, WebElement ele) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, ele);
	}

	/**
	 * Go to URL.
	 * 
	 * @param driver
	 * @param url
	 */
	public static void goToUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * Go to previous page
	 * 
	 * @param driver
	 */
	public static void goToPreviuosPage(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * Highlight Element
	 * 
	 * @param driver
	 * @param element
	 */
	public static void highlightElement(WebDriver driver, WebElement element) {
		/*
		 * for (int i = 0; i < 2; i++) { JavascriptExecutor js =
		 * (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		 * element, "color: yellow; border: 2px solid yellow;");
		 * js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		 * element, ""); }
		 */

		// draw a border around the found element

		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '3px solid yellow'", element);
		
		pause(2);
		
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.border = '0px'", element);
	}

	/**
	 * Stop page loading
	 * 
	 * @param driver
	 */
	public static void stopPageLoading(WebDriver driver) {
		driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
	}
	
	public static void waitForElement(WebDriver driver,WebElement webelement) {
		  (new WebDriverWait(driver, 35)).until(ExpectedConditions.visibilityOf(webelement));
		 }
	
	public static String rndmString(int length) {
		  String rnd1 = RandomStringUtils.randomAlphabetic(length).toUpperCase();
		  return rnd1;

		 }
	
	public static int randBetween(int start, int end) {
		  return start + (int) Math.round(Math.random() * (end - start));
		 }
	
	public void waitForElement(WebElement webelement,WebDriver driver) {
		(new WebDriverWait(driver, 35)).until(ExpectedConditions
				.visibilityOf(webelement));

	}
	
	public static void OpenNewWindow(WebDriver driver)
	{	pause(5);
		System.out.println("==========="+System.getProperty("os.name"));
		if(System.getProperty("os.name").equalsIgnoreCase("Windows 8"))
		{
			String NewWindow = Keys.chord(Keys.CONTROL,Keys.SHIFT+"n");
			driver.findElement(By.xpath("//body")).sendKeys(NewWindow);
		}
	}
	
	public static void SwitchWindow(WebDriver driver, String WindowHandle)
	{
		driver.switchTo().window(WindowHandle);
	}
	
	public static void SwitchToLastOpenWindow(WebDriver driver, String WindowHandle)
	{
		for (String Windowa : driver.getWindowHandles()) {
			driver.switchTo().window(Windowa);
		}
	}
	
	public static WebElement getElementByjs(WebDriver driver,String querySelecterString)
	{
		JavascriptExecutor je = (JavascriptExecutor)driver;
		return (WebElement)je.executeScript("return "+querySelecterString);
	}
	
	public static void moveToElementByJs(WebDriver driver,WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static String getTextElementByjs(WebDriver driver,String querySelecterString)
	{
		JavascriptExecutor je = (JavascriptExecutor)driver;
		return (String)je.executeScript("return "+querySelecterString);
	}
	
	@SuppressWarnings("unchecked")
	public static List<WebElement> getElementListByjs(WebDriver driver,String querySelecterString)
	{
		JavascriptExecutor je = (JavascriptExecutor)driver;
		return (List<WebElement>)je.executeScript("return "+querySelecterString);
	}

	
	public static String readProperties(String fileName,String propertieName)
	 {
	    String result="";
	    File file = new File("Data/"+fileName+".properties");
	    FileInputStream fileInput = null;
	    try {
	     fileInput = new FileInputStream(file);
	    } catch (FileNotFoundException e) {
	     e.printStackTrace();
	    }
	    Properties prop = new Properties();
	    try {
	      prop.load(fileInput);
	      result = prop.getProperty(propertieName);
	      System.out.println(result);
	    } catch (Exception e) {
	     System.out.println("Exception: " + e);
	    } finally {
	    }
	    
	    return result;
	 }
	 
	 public static void writeProperties(String fileName,String propertieName,String propertieValue,String Comment)
	 {
		  Properties propwrite = new Properties();
		    
	      try {
	       //set the properties value
	       propwrite.setProperty(propertieName, propertieValue);
	  
	       //save properties to project root folder
	       if(propertieName.contains("ApplicationName"))
	       {
	        propwrite.store(new FileOutputStream("Data/"+fileName+".properties",false), Comment);
	       }else{
	        propwrite.store(new FileOutputStream("Data/"+fileName+".properties",true), Comment);
	       }
	      
	  
	      } catch (IOException ex) {
	       ex.printStackTrace();
	         }
	 }
	 
	
	 public static void switchToNewtabWithUrl(WebDriver driver,String URL)
	 {
	  pause(5);
	  System.out.println("==========="+System.getProperty("os.name"));
	  
	  ((JavascriptExecutor)driver).executeScript("window.open();");
	  pause(3);
	  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(1));
	  
	  pause(3);
	  driver.get(URL);
	 }
	 
	 public static void switchToNewtabWithUrl(WebDriver driver,String URL,int tab)
	 {
	  pause(5);
	  System.out.println("==========="+System.getProperty("os.name"));
	  
	  ((JavascriptExecutor)driver).executeScript("window.open();");
	  pause(3);
	  /*String NewWindow = Keys.chord(Keys.CONTROL,"t");
		driver.findElement(By.xpath("//body")).sendKeys(NewWindow);
	*/	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(tab));
	  
	  pause(3);
	  driver.get(URL);
	 }
	 
	 public static void SwitchtoTab(WebDriver driver,int tabNumber)
	 {
	  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	  driver.switchTo().window(tabs.get(tabNumber));
	 }
	 
	 public static String readDataProperties(String fileName,String propertieName)
	 {
	    String result="";
	    File file = new File("Data/"+fileName+".properties");
	    FileInputStream fileInput = null;
	    try {
	     fileInput = new FileInputStream(file);
	    } catch (FileNotFoundException e) {
	     e.printStackTrace();
	    }
	    Properties prop = new Properties();
	    try {
	      prop.load(fileInput);
	      result = prop.getProperty(propertieName);
	      System.out.println(result);
	    } catch (Exception e) {
	     System.out.println("Exception: " + e);
	    } finally {
	    }
	    
	    return result;
	 }
	 
	 public static void writeDataProperties(String fileName,String propertieName,String propertieValue,String Comment)
	 {
		 Properties propwrite = new Properties();
		    
	      try {
	       //set the properties value
	       propwrite.setProperty(propertieName, propertieValue);
	  
	       //save properties to project root folder
	       if(propertieName.contains("Primary_Address"))
	       {
	        propwrite.store(new FileOutputStream("Data/"+fileName+".properties",false),"");
	       }else{
	        propwrite.store(new FileOutputStream("Data/"+fileName+".properties",true),"");
	       }
	      
	  
	      } catch (IOException ex) {
	       ex.printStackTrace();
	         }
	 }
	 
	 public static File getTheNewestFile(String filePath, String ext) {
	     File theNewestFile = null;
	     File dir = new File(filePath);
	     FileFilter fileFilter = new WildcardFileFilter("*." + ext);
	     File[] files = dir.listFiles(fileFilter);

	     if (files.length > 0) {
	         /* The newest file comes first */
	         Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	         theNewestFile = files[0];
	     }

	     return theNewestFile;
	 }
	 
	 public static void moveToElement(WebElement element,WebDriver driver)
	 {
		 Actions ac=new Actions(driver);
		 ac.moveToElement(element).build().perform();
	 }
	 
	 /**
		 * Wait up to String locator present
		 * 
		 * @param selector
		 */
		public static void waitForElementClickable(WebDriver driver, String xpath) {
			wait = new WebDriverWait(driver, 60);
			try {
				System.out.println("Waiting for the element to be clickable.");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			} catch (Exception e) {
			}
		}
		
		public static void waitForElementClickable(WebDriver driver, WebElement element) {
			wait = new WebDriverWait(driver, 45);
			try {
				System.out.println("Waiting for the element to be clickable.");
				wait.until(ExpectedConditions.elementToBeClickable(element));
			} catch (Exception e) {
			}
		}
		
	
		public static void FluentwaitForElementClickable(WebDriver driver, WebElement element) {
		
				FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).pollingEvery(4, TimeUnit.SECONDS)
				.withTimeout(30,TimeUnit.SECONDS)		
				.ignoring(ElementNotInteractableException.class);		
					
		}
		/**
		 * Wait and refresh up to String locator present and
		 * 
		 * @param selector
		 */
		public static boolean waitForElementAndRefresh(WebDriver driver, String xpath) {
			wait = new WebDriverWait(driver, 2);
			try {		
				wait.until(visibilityOfElementLocated(By.xpath(xpath)));
				return true;
			} catch (Exception e) {
				driver.navigate().refresh();
				return false;
			}
		}
}
