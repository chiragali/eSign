package com.automation.indexpage;

import java.io.File;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.automation.init.AbstractPage;
import com.automation.utility.Common;
import com.automation.utility.Common_demo;
import com.automation.verification.PackageVerification_demo;

public class PackageIndexpage_demo extends AbstractPage{

	public static String RefName,BrwMail; 
				
	public PackageIndexpage_demo(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy (xpath=".//form[@id='login']//input[@name='username']")
	WebElement txtUsername;
			
	public void enterUsername(String userName) {
	
		Common_demo.waitForElement(driver, txtUsername); //.waitforElement(driver, txtUsername);
		Common_demo.pause(2);
		Common_demo.type(txtUsername, userName);
		Common_demo.logcase("Username Entered is "+ txtUsername.getAttribute("value"));
	}

	@FindBy (xpath=".//form[@id='login']//input[@name='password']")
	WebElement txtPassword;

	public void enterPassword(String password) {
		
		Common_demo.type(txtPassword, password);
		Common.logcase("Password entere is " + txtPassword.getAttribute("value"));
	}
	
	@FindBy (xpath=".//button[@title='login']")
	WebElement btnLogin;

	public PackageVerification_demo clickLogin()  {
		// TODO Auto-generated method stub
		Common_demo.clickOn(driver, btnLogin);
		return new PackageVerification_demo(driver);
	}

	@FindBy (id="workflows")
	WebElement workflow;
	public void selectworkflow() {
		
	Select select = new Select(workflow);
	select.selectByVisibleText("IBM Digital Contracts Demo v. 1");// .deselectByVisibleText("IBM Digital Contracts Demo v. 1");
		
	}
	
	
	@FindBy (xpath="//*[@id='create_package']")
	WebElement createpkg;
	
	public PackageVerification_demo ClickCreateWF() {
		
		Common_demo.waitforElementClickable(driver, createpkg);
		Common_demo.clickOn(driver, createpkg);
		return new PackageVerification_demo(driver);
	}

	@FindBy (xpath="//*[@id='packageReference']")
	WebElement Intreference;

	public void referenceName() {
	String RefName = "Refauto_"+Common.generateRandomChars(6);
	Common_demo.type(Intreference, RefName);
	Common_demo.logcase("Internal reference is "+Intreference.getAttribute("value"));
	}

	@FindBy (xpath=".//input[contains(@class,'firstName')]")
	List <WebElement> txtFname;
	
	public void enterFirstName() {
		
		String BFirstName = "BorrowerFirstN"+Common.generateRandomChars(6);
		Common_demo.type(txtFname.get(0), BFirstName);
		Common_demo.logcase("First Name is "+txtFname.get(0).getAttribute("value"));
	}

	@FindBy (xpath=".//input[contains(@class,'lastName')]")
	List <WebElement> txtLname;

	public void enterLastName() {

		String BLastName = "BorrowerLastN"+Common.generateRandomChars(6);
		Common_demo.type(txtLname.get(0), BLastName);
		Common_demo.logcase("First Name is "+txtLname.get(0).getAttribute("value"));
			
	}
			
	@FindBy (xpath=".//input[contains(@class,'email')]")
	List <WebElement> txtemail;
	
	public void enterEmail() {
		
		
		String BrwMail = "Bmail_"+Common.generateRandomChars(5)+"@mailinator.com";
		Common_demo.type(txtemail.get(0), BrwMail);
		Common_demo.logcase(BrwMail);
		Common_demo.logcase(" Email address of Borrower is "+txtemail.get(0).getAttribute("value"));
	}
	
	@FindBy (xpath=".//input[contains(@class,'phone')]")
	List <WebElement> txtMobile;
	
	public void enterMobile() {
		
		String Mobile="+61444444444";
		Common_demo.type(txtMobile.get(0), Mobile);
		Common_demo.logcase(" MObile number enteed is "+txtMobile.get(0).getAttribute("value"));
		
	}

	
	@FindBy (xpath="//span[@class='tablecell']//input")
	List<WebElement> checkBoxes;
	
	@FindBy (xpath="//span[@class='tablecell']")
	List<WebElement> attachmentTitle;
	
	public void chooseattachments() {
		
	int randnum = Common.randBetween(0,checkBoxes.size()-1 );
	
	for (int j=0;j<=randnum;j++)
	{
		checkBoxes.get(j).click();
		Common_demo.logcase(" Attachments clicked is "+attachmentTitle.get(j).getText());
	}
		
	}

	@FindBy (xpath="//button[@id='createpkgid']")
	WebElement crtpkg;
	
	public PackageVerification_demo clickCreatepkg() {
		
			Common_demo.waitforElementClickable(driver, crtpkg);
			Common_demo.clickOn(driver, crtpkg);
			return new PackageVerification_demo(driver);
	}
	
	
	@FindBy (xpath=".//span[@id='fileupload']//span[text()='Cloud Services Agreement']/../input")
	WebElement chkAgreement;
	
	public void CloudServicechkbox() {
		
		Common_demo.waitForElement(driver, chkAgreement);
		Common_demo.clickOn(driver, chkAgreement);
		
	}

	@FindBy (xpath=".//input[@type='file']")
	WebElement upldFile;
	
	public void uploadCloudAgreement() {
		
		Common_demo.waitForElement(driver, upldFile);
		
		File file = new File("Resources/Cloud Hosting Agreement.pdf");
		upldFile.sendKeys(file.getAbsolutePath().toString());
				
	}

	@FindBy (xpath=".//div[contains(@id,'uploadSuccesfull')]")
	WebElement uploaduccess;

	@FindBy (xpath="//*[@id='sendPackage']")
	WebElement ClickSend;
	
	public PackageVerification_demo ClickSendNow() {
		Common_demo.waitForElement(driver, uploaduccess);
		Common_demo.clickOn(driver, ClickSend);
		Common.waitForElement(driver, "//table[@id='packages']//tr[1]//td[1][contains(text(),'"+PackageIndexpage.reference+"')]");
		return new PackageVerification_demo(driver);
	}
			
	public PackageVerification_demo OpenMailinatorURL() {
		
		String URL = "http://www.mailinator.com/";
		Common_demo.switchToNewtabWithUrl(driver, URL, 1);
	//	driver.get("http://www.mailinator.com/");
		return new PackageVerification_demo(driver);
	}

	@FindBy(xpath="//*[@id='inboxfield']")
	WebElement mailinbox;		


	public void entermailinator(String brwMail2) {
		
		System.out.println(brwMail2);
		Common_demo.waitForElement(driver, mailinbox);
		mailinbox.sendKeys(brwMail2+Keys.ENTER);
		
	}
  	
}
