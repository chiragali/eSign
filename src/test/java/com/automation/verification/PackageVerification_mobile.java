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
import com.automation.init.AbstractPage;
import com.automation.utility.Common;
import com.automation.utility.TestData;

public class PackageVerification_mobile extends AbstractPage{
	
	public static String Sendername;

	public PackageVerification_mobile(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy (xpath=".//span[@id='user_fullname']")
	WebElement fullUserName;
	
	public boolean verifyLogin() {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, fullUserName);
		
		Common.log(fullUserName.getText());
		if(fullUserName.getText().trim().equalsIgnoreCase(TestData.getSenderName()))
		{
			return true;
		}
		
		return false;
	}
				
	@FindBy (xpath="//table[@id='packages']//tr[1]//td[1]")
	WebElement pkgName;
	
	public boolean verifyPkg() {
		// TODO Auto-generated method stub
		
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		
		return pkgName.getText().contains(PackageIndexpage.reference);
	}
	
	public boolean verifyDraftPkg() {
		// TODO Auto-generated method stub
		
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='DRAFT']";
		WebElement draftPkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return pkgName.getText().contains(PackageIndexpage.reference) && draftPkg.isDisplayed();
	}
	
	public boolean verifyBYPASSpkg() {
		// TODO Auto-generated method stub
		
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='BYPASS']";
		WebElement bypassPkgicon=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return pkgName.getText().contains(PackageIndexpage.reference) && bypassPkgicon.isDisplayed();
	}
	
	public boolean verifyOptOutIcon() {
		// TODO Auto-generated method stub
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='OPTED_OUT']";
		WebElement OptoutPkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return pkgName.getText().contains(PackageIndexpage.reference) && OptoutPkg.isDisplayed();
	}
	
		
	public boolean verifyArchivedpkg() {
		// TODO Auto-generated method stub
		
		Common.pause(20);
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		/*String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='BYPASS']";*/
		WebElement ArchivePkg=driver.findElement(By.xpath("//table[@id='packages']//tr[1]//td[3][contains(text(),'ARCHIVED')]"));
		return pkgName.getText().contains(PackageIndexpage.reference) && ArchivePkg.isDisplayed();
	}

	public boolean verifyDocPortalpage(String signer) {
		// TODO Auto-generated method stub
		String start="//div[@class='user-info']//*[contains(text(),'";
		String end="')]";
		if (signer.contains("b")) 
		{
			Common.log("  ->Verify Borrower is navigated to portal.");
			WebElement borrower = driver.findElement(By.xpath(start + PackageIndexpage.fname + end));
			WebElement referencePkg = driver.findElement(By.xpath(start + PackageIndexpage.reference + end));
			return borrower.isDisplayed() && referencePkg.isDisplayed();
		} 
		else if (signer.contains("g")) 
		{
			Common.log("  ->Verify Guarantor is navigated to portal.");
			WebElement borrower = driver.findElement(By.xpath(start + PackageIndexpage.gfname + end));
			WebElement referencePkg = driver.findElement(By.xpath(start + PackageIndexpage.reference + end));
			return borrower.isDisplayed() && referencePkg.isDisplayed();
		}
		else
		{
			Common.log("  ->Verify Relationship Manager is navigated to portal.");
			WebElement borrower = driver.findElement(By.xpath(start + PackageIndexpage.rfname + end));
			WebElement referencePkg = driver.findElement(By.xpath(start + PackageIndexpage.reference + end));
			return borrower.isDisplayed() && referencePkg.isDisplayed();
		}
	}
	  
	@FindBy (xpath=".//Button[contains(text(),'View document')]/../..//*[text()='Viewed']")
	WebElement viewedState;

	public boolean verifyDocumentState() {
		// TODO Auto-generated method stub
		return viewedState.isDisplayed();
	}

	public boolean verifySignStatus() {
		// TODO Auto-generated method stub
		
		String start=".//div[contains(text(),'Cloud Services Agreement - ";
		String end="')]/../..//*[text()='Signed']";
		WebElement signed=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return signed.isDisplayed();
	}
	
	@FindBy (xpath=".//*[contains(text(),'Thank you')]")
	WebElement txtThanks;

	public boolean verifyThanksMsg(String signer) {
		// TODO Auto-generated method stub
		if (signer.contains("b")) 
		{
			return txtThanks.getText().contains(PackageIndexpage.fname) && txtThanks.getText().contains(PackageIndexpage.lname);
		} 
		else if (signer.contains("g")) 
		{
			return txtThanks.getText().contains(PackageIndexpage.gfname) && txtThanks.getText().contains(PackageIndexpage.gfname);
		}
		else
		{
			return txtThanks.getText().contains(PackageIndexpage.rfname) && txtThanks.getText().contains(PackageIndexpage.rfname);
		}
	}

	public boolean verifyFileUploaded(List<WebElement> docsState, int i) {
		// TODO Auto-generated method stub
		Common.waitForElementClickable(driver, "//a[text()='Submit loan documents']");
		Common.pause(3);
		return docsState.get(i).getText().contains("File uploaded");
	}

	public boolean verifyPkgComplete(String email, String string) {
		// TODO Auto-generated method stub
		String emailSubject=".//div[@title='FROM']/..//div[contains(text(),'Package complete, reference number')]";
		
		Common.logstep("  -> Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		if (string.contains("b")) 
		{
			Common.logstep("  -> Enter test email address for Borrower: "+ email);
			packageIndexpage.entermailinatorEmail(email);	
		
			for(int i=0;i<3;i++)
			{
				if(Common.waitForElementAndRefresh(driver, emailSubject)) {
					driver.close();
					Common.SwitchtoTab(driver, 0);
					return true;
				}
			}
			return false;
		} 
		else if (string.contains("g")) 
		{
			Common.logstep("  -> Enter test email address for Guarantor : "+ email);
			packageIndexpage.entermailinatorEmail(email);	

			for(int i=0;i<3;i++)
			{
				if(Common.waitForElementAndRefresh(driver, emailSubject)) {
					driver.close();
					Common.SwitchtoTab(driver, 0);
					return true;
				}
			}
			return false;
		}
		else
		{
			Common.logstep("  -> Enter test email address for Relationship Manager: "+ email);
			packageIndexpage.entermailinatorEmail(email);	

			for(int i=0;i<3;i++)
			{
				if(Common.waitForElementAndRefresh(driver, emailSubject)) {
					driver.close();
					Common.SwitchtoTab(driver, 0);
					return true;
				}
			}
			return false;
		}
	}

	
	public boolean verifyEmail() {
		// TODO Auto-generated method stub
		String emailSubject=".//div[@title='FROM']/..//div[contains(text(),'"+PackageIndexpage.reference+"')]";
		for(int i=0;i<7;i++)
		{
			if(Common.waitForElementAndRefresh(driver, emailSubject)) {
				Common.pause(2);
				return driver.findElement(By.xpath(".//div[@title='FROM']/..//div[contains(text(),'"+PackageIndexpage.reference+"')]")).isDisplayed();
			}
		}
		return false;
	}

	public boolean verifySentIcon() {
		// TODO Auto-generated method stub
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='SENT']";
		WebElement draftPkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		Common.waitForElement(driver,start+PackageIndexpage.reference+end );
		
		return draftPkg.isDisplayed();
	}
	
	@FindBy (xpath=".//*[text()='Error']")
	WebElement errorMessage;

	public boolean verifyDocumentValidation() {
		// TODO Auto-generated method stub
		return errorMessage.isDisplayed();
	}

	public boolean verifyDeclinedIcon() {
		// TODO Auto-generated method stub
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='DECLINED']";
		WebElement DEclinePkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return pkgName.getText().contains(PackageIndexpage.reference) && DEclinePkg.isDisplayed();
	}

	public boolean verifyCompletedIcon() {
		// TODO Auto-generated method stub
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='COMPLETED']";
		WebElement CompletePkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return pkgName.getText().contains(PackageIndexpage.reference) && CompletePkg.isDisplayed();
	}

	
	@FindBy (xpath="//span[@title='signed 1 of 1 total signatures'][contains(text(),'Borrower 1')]")
	WebElement OnlyBorrower;
	
	public boolean verifyOnlyBorrowerSign() {
		// TODO Auto-generated method stub
		/*String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='SENT']";
		WebElement draftPkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));*/
		Common.waitForElement(driver,OnlyBorrower);
		Common.highlightElement(driver, OnlyBorrower);
		Common.pause(2);
		return OnlyBorrower.isDisplayed();
	}

	public boolean verifyDraftIcon() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String start=".//*[contains(text(),'";
				String end="')]/..//*[@title='DRAFT']";
				WebElement draftPkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
				Common.waitForElement(driver,start+PackageIndexpage.reference+end );
				
				return draftPkg.isDisplayed();
	}

	public boolean verifyReadyIcon() {
		// TODO Auto-generated method stub
		Common.pause(25);
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='READY']";
		WebElement CompletePkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return pkgName.getText().contains(PackageIndexpage.reference) && CompletePkg.isDisplayed();
	}

	public boolean verifyTrashPkg() {
		// TODO Auto-generated method stub
		Common.pause(20);
		Common.log(pkgName.getText());
		Common.log(PackageIndexpage.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='TRASHED']";
		WebElement CompletePkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));
		return pkgName.getText().contains(PackageIndexpage.reference) && CompletePkg.isDisplayed();
	}

	public boolean verifyShowMyPackage() 
	
	{
		 WebElement packagename=null;
		
		Common.pause(5);
		String Username= fullUserName.getText();
		int size = Username.length();
		Sendername = Username.substring(8, size);
		Common.log(" Sender Name is --> "+ Sendername);
		
		List<WebElement> Sendersname = driver.findElements(By.xpath("//table[@id='packages']//tr"));
		int tablesize = Sendersname.size();
		
		Common.log(" Total rows in table is --> "+ tablesize);
		
		for(int i=1;i<tablesize-1;i++)
		{
			
		WebElement actualname = driver.findElement(By.xpath("//table[@id='packages']//tr["+i+"]//td[5]"));
		
		if(! fullUserName.getText().contains(actualname.getText()))	
			{
				packagename = driver.findElement(By.xpath("//table[@id='packages']//tr["+i+"]//td[1]"));
				Common.log(packagename.getText()+" ----> " + actualname.getText());
				return false;
			}
		}
		
		return true;
		
	} 
	
public boolean verifyShowMyPackagedynamic() 
	
	{
		 WebElement packagename=null;
		
		Common.pause(5);
		String Username= fullUserName.getText();
		int size = Username.length();
		Sendername = Username.substring(8, size);
		Common.log(" Sender Name is --> "+ Sendername);
		
		List<WebElement> Sendersname = driver.findElements(By.xpath("//table[@id='packages']//tr"));
		int tablesize = Sendersname.size();
		
		Common.log(" Total rows in table is --> "+ tablesize);
		
		List<WebElement> Columns = driver.findElements(By.xpath("//table[@id='packages']//th"));
		int columnsize = Columns.size();
		/*WebElement SenderPos = driver.findElement(By.xpath("//table[@id='packages']//th//div[contains(text(),'Sender')]"));
		int sendsize = */
		Common.log(" Total Columns in table is --> "+ columnsize);
		
		for(int i=1;i<tablesize-1;i++)
		{
			
		WebElement actualname = driver.findElement(By.xpath("//table[@id='packages']//tr["+i+"]//td["+columnsize+"]"));
		
		if(! fullUserName.getText().contains(actualname.getText()))	
			{
				packagename = driver.findElement(By.xpath("//table[@id='packages']//tr["+i+"]//td[1]"));
				Common.log(packagename.getText()+" ----> " + actualname.getText());
				return false;
			}
		}
		
		return true;
		
	} 
/*@FindBy (xpath="//*[@id='package-properties']//h1[contains(text(),'Reference_EISIe')]")

String reviewtab;*/

public boolean verifyreviewpackage() {
	// TODO Auto-generated method stub
	Common.waitForElement(driver, driver.findElement(By.xpath("//*[@id='package-properties']//h1[contains(text(),"+PackageIndexpage.reference+")]")));
	//Common.log(pkgName.getText());
	Common.log(PackageIndexpage.reference);
	WebElement reviewPkg=driver.findElement(By.xpath("//*[@id='package-properties']//h1[contains(text(),"+PackageIndexpage.reference+")]"));
	String reviewPkgname =driver.findElement(By.xpath("//*[@id='package-properties']//h1[contains(text(),"+PackageIndexpage.reference+")]")).getText();
	Common.waitForElement(driver, reviewPkg);
	//return pkgName.getText().contains(PackageIndexpage.reference) && reviewPkg.isDisplayed();
	if(PackageIndexpage.reference.equalsIgnoreCase(reviewPkgname))
	{
		return true;
	}
	else
	{return false;}
}

}
