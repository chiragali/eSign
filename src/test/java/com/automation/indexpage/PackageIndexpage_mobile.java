package com.automation.indexpage;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.automation.index.PackageIndex;
import com.automation.init.AbstractPage;
import com.automation.utility.Common;
import com.automation.verification.PackageVerification;
import com.automation.verification.PackageVerification_mobile;

public class PackageIndexpage_mobile extends AbstractPage{

	public static String reference,fname,lname,email;
	public static String gfname,glname,gemail;
	public static String rfname,rlname,remail;
	public static String mobile="+61444444444";
	
	public PackageIndexpage_mobile(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy (xpath=".//form[@id='login']//input[@name='username']")
	WebElement txtUsername;
	
	public void enterUsername(String userName) {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, txtUsername);
		Common.pause(2);
		Common.type(txtUsername, userName);
		Common.log("  -> Username entered: "+txtUsername.getAttribute("value"));
	}
	
	@FindBy (xpath=".//form[@id='login']//input[@name='password']")
	WebElement txtPassword;

	public void enterPassword(String password) {
		// TODO Auto-generated method stub
		Common.type(txtPassword, password);
		Common.log("  -> Password entered: "+txtPassword.getAttribute("value"));
	}
	
	@FindBy (xpath=".//button[@title='login']")
	WebElement btnLogin;

	public PackageVerification clickLogin() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnLogin);
		return new PackageVerification(driver);
	}

	@FindBy (id="workflows")
	WebElement workflow;
	
	public void selectWorkflow(String option) {
		// TODO Auto-generated method stub
		Select selectwf=new Select(workflow);
		selectwf.selectByVisibleText(option);
	}
	
	@FindBy (xpath=".//*[@id='create_package']")
	WebElement btnCreate;

	public PackageVerification clickCreate() {
		// TODO Auto-generated method stub
		btnCreate.click();
		return new PackageVerification(driver);
	}
	
	@FindBy (xpath=".//input[@id='packageReference']")
	WebElement txtReference;

	public void enterReference() {
		// TODO Auto-generated method stub
		reference="Reference_"+Common.generateRandomChars(5);
		Common.waitForElement(driver, ".//h2[text()='IBM Digital Contracts Demo v. 1']");
		Common.type(txtReference,reference);
		Common.log("  -> Internl Reference: "+txtReference.getAttribute("value"));	
	}
	
	@FindBy (xpath=".//input[contains(@class,'firstName')]")
	List<WebElement> txtFname;

	public void enterFname() {
		// TODO Auto-generated method stub
		fname="autoFname"+Common.generateRandomChars(10);
		Common.type(txtFname.get(0), fname);
		Common.log("  -> First Name: "+txtFname.get(0).getAttribute("value"));
	}
	
	@FindBy (xpath=".//input[contains(@class,'lastName')]")
	List<WebElement> txtLname;

	public void enterLname() {
		// TODO Auto-generated method stub
		lname="autoLname"+Common.generateRandomChars(10);
		Common.type(txtLname.get(0), lname);
		Common.log("  -> Last Name: "+txtLname.get(0).getAttribute("value"));

	}
	
	@FindBy (xpath=".//input[contains(@class,'email')]")
	List<WebElement> txtEmail;

	public void enterEmail() {
		// TODO Auto-generated method stub
		email="auto_"+Common.generateRandomChars(20);
		Common.type(txtEmail.get(0), email+"@mailinator.com");
		Common.log("  -> Email : "+txtEmail.get(0).getAttribute("value"));
	}
	
	@FindBy (xpath=".//input[contains(@class,'phone')]")
	List<WebElement> txtMobile;

	public void enterMobile() {
		// TODO Auto-generated method stub
		Common.type(txtMobile.get(0), mobile);
		Common.log("  -> Mobile Number : "+txtMobile.get(0).getAttribute("value"));
	}
	
	@FindBy (xpath="//span[@class='']//input")
	List<WebElement> checkBoxes;
	
	@FindBy (xpath="//span[@class='']")
	List<WebElement> attachmentTitle;

	public void chooseAttchments() {
		// TODO Auto-generated method stub
		int random=Common.randBetween(0, checkBoxes.size()-1);
		Common.log("  -> "+(random+1)+" Attachments going to be select.");
		for(int i=0;i<=random;i++)
		{
			checkBoxes.get(i).click();
			Common.log("  -> "+attachmentTitle.get(i).getText());
			Common.pause(2);
		}
	}

	@FindBy (xpath=".//Button[@id='createpkgid']")
	WebElement createpkg;

	public PackageVerification clickCreatepkg() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, createpkg);
		return new PackageVerification(driver);
	}
	
	@FindBy (xpath=".//span[@id='fileupload']//span[text()='Cloud Services Agreement']/../input")
	WebElement chkAgreement;

	public PackageVerification chooseServiceCheckBox() {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, "//span[@id='fileupload']//span[text()='Cloud Services Agreement']");
		Common.clickOn(driver, chkAgreement);
		return new PackageVerification(driver);
	}
	
	@FindBy (xpath=".//input[@type='file']")
	WebElement upldFile;

	public PackageVerification uploadFile() {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, ".//input[@type='file']");
		File file = new File("Resources/Cloud Hosting Agreement.pdf");
		
		//System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
	
		upldFile.sendKeys(file.getAbsolutePath().toString());
		return new PackageVerification(driver);
	}
	
	@FindBy (xpath=".//Button[@id='sendPackage']")
	WebElement btnSend;

	public PackageVerification clickOnlySendPkg() {
		// TODO Auto-generated method stub
		
		Common.waitForElement(driver, ".//Button[@id='sendPackage']");
		Common.pause(2);
		Common.clickOn(driver, btnSend);
		Common.waitForElement(driver, "//table[@id='packages']//tr[1]//td[1][contains(text(),'"+PackageIndexpage_mobile.reference+"')]");
		return new PackageVerification(driver);
	}
	
	public PackageVerification clickSendPkg() {
		// TODO Auto-generated method stub
		
		Common.waitForElement(driver, ".//div[contains(@id,'uploadSuccesfull')]");
		Common.pause(2);
		Common.clickOn(driver, btnSend);
	//	Common.jsClick(driver, btnSend); // Implemented for Mobile Browser
		Common.waitForElement(driver, "//table[@id='packages']//tr[1]//td[1][contains(text(),'"+PackageIndexpage_mobile.reference+"')]");
		return new PackageVerification(driver);
	}		

	public PackageVerification_mobile checkMailinator(int tabmail) {
		// TODO Auto-generated method stub
		String url="https://www.mailinator.com";
		Common.switchToNewtabWithUrl(driver, url, tabmail);
		return new PackageVerification_mobile(driver);
	}

	public PackageVerification_mobile checkEmailContent() {
		// TODO Auto-generated method stub
			
//		Common.waitForElement(driver1, "//tr[contains(@id,'row')]/td[contains(text(),'"+PackageIndexpage.reference+"')]");
			System.out.println("Entered into Check Email Mailinator :"+PackageIndexpage.reference);
			List<WebElement> emailList=driver1.findElements(By.xpath("//*[contains(@class,'ng-binding')][contains(text(),'"+PackageIndexpage.reference+"')]"));
			Common.log("Size of Mail Subject is "+emailList.size());
			driver1.findElement(By.xpath("//*[contains(@class,'ng-binding')][contains(text(),'"+PackageIndexpage.reference+"')]")).click();
			Common.log("Mail is Clicked");
			return new PackageVerification_mobile(driver1);
	
		/*String emailSubject=".//div[@title='FROM']/..//div[contains(text(),'"+reference+"')]";
		for(int i=0;i<7;i++)
		{
			if(Common.waitForElementAndRefresh(driver, emailSubject)) {
				driver.findElement(By.xpath(".//div[@title='FROM']/..//div[contains(text(),'"+reference+"')]")).click();
				return new PackageVerification_mobile(driver);
			}
		}
		return new PackageVerification_mobile(driver);*/
	}

	@FindBy (xpath="//input[@id='inboxfield']")
	WebElement emailInput;
	
	public void entermailinatorEmail(String mail) {
		// TODO Auto-generated method stub
		String str=mail.toLowerCase();
		emailInput.sendKeys(mail+Keys.ENTER);
		Common.waitForElement(driver, ".//li[contains(@onclick,'"+str+"')]");
	}
	
	@FindBy (xpath="//a[text()='Go to My documents']")
	WebElement btnGoto;

	@FindBy (xpath=".//iframe[@id='msg_body']")
	WebElement frameElement;
	
	@FindBy (xpath=".//*[text()='Submit signed documents']")
	WebElement move4;
	
	public void clickGoToDocument() {
		// TODO Auto-generated method stub
		driver.switchTo().frame(frameElement);
		/*Common.moveToElementByJs(driver, btnGoto);
		*/
		Common.moveToElementByJs(driver, move4);
		Common.pause(5);
		Common.jsClick(driver, btnGoto);
		Common.pause(10);
	}

	@FindBy (xpath=".//button[text()='Accept']")
	WebElement btnAccept;
	
	public void clickAccept() {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, ".//button[text()='Accept']");
		Common.clickOn(driver, btnAccept);
		Common.waitForElement(driver, ".//h2[contains(text(),'Welcome')]");
	}
			
	@FindBy (xpath=".//a[contains(text(),'Request SMS Authentication Code')]")
	WebElement btnSMS;

	public void clickRequestSMS() {
		// TODO Auto-generated method stub
		Common.moveToElementByJs(driver, btnSMS);
		Common.clickOn(driver, btnSMS);
		Common.waitForElement(driver, ".//input[@ng-model='passcode']");
	}
	
	@FindBy (xpath=".//input[@ng-model='passcode']")
	WebElement smsPassword;

	public void enterSMSCode() {
		// TODO Auto-generated method stub
		Common.type(smsPassword, "555555");
		Common.pause(1);
	}
	
	@FindBy (xpath=".//button[@type='submit']")
	WebElement btnAuth;

	public void clickAuthnticate() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnAuth);
		Common.waitForElement(driver, ".//div[@class='user-info']");
	}
	
	@FindBy (xpath=".//Button[contains(text(),'View document')]")
	WebElement btnViewDocument;

	public void clickViewDocument() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnViewDocument);
		Common.waitForElement(driver, ".//Button[contains(text(),'Back to loan documents portal')]");
	}
	
	@FindBy (xpath=".//Button[contains(text(),'Back to loan documents portal')]")
	WebElement btnBackToLoan;

	public void clickBackToLoanPortal() {
		// TODO Auto-generated method stub
		Common.moveToElementByJs(driver, btnBackToLoan);
		Common.clickOn(driver, btnBackToLoan);
		Common.waitForElement(driver, ".//div[@class='user-info']");
	}

	@FindBy (xpath=".//Button[contains(text(),'Sign online')]")
	WebElement btnSignOnline;
	
	public void clickSignOnline() {
		// TODO Auto-generated method stub
		Common.moveToElementByJs(driver, btnViewDocument);
	//	Common.moveToElementByJs(driver, btnSignOnline);
		Common.clickOn(driver, btnSignOnline);
		Common.waitForElement(driver, ".//*[@id='document-view']");
	}
	
	@FindBy (xpath="//div[@id='field-navigator-below']//button")
	WebElement btnDown;

	public void clickArrow() {
		// TODO Auto-generated method stub
		Common.jsClick(driver, btnDown);
		Common.pause(1);
	}
	
	@FindBy (xpath="//img[@class='checkmark']/..")
	WebElement clickSign;

	public void clickToSign() {
		// TODO Auto-generated method stub
		Common.jsClick(driver, clickSign);
		Common.waitForElement(driver, "//div[@id='bind-dialog']");
	}
	
	@FindBy (xpath="//div[@id='bind-dialog']/..//*[contains(text(),'Ok')]")
	WebElement btnOk;

	public PackageVerification_mobile clickAcceptAlert() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnOk);
		Common.pause(3);
		return new PackageVerification_mobile(driver);
	}
	
	@FindBy (xpath="//li[@id='insideGlobalActions']")
	WebElement btnReturnHome;

	public PackageVerification_mobile clickReturn() {
		// TODO Auto-generated method stub
		Common.pause(60);
		Common.clickOn(driver, btnReturnHome);
		Common.waitForElement(driver, ".//div[@class='user-info']");
		return new PackageVerification_mobile(driver);
	}

	@FindBy (xpath="//a[text()='Submit loan documents']")
	WebElement btnSubmitLoan;

	public PackageVerification_mobile clickSubmit() {
		// TODO Auto-generated method stub
		Common.waitForElementClickable(driver, "//a[text()='Submit loan documents']");
		Common.pause(2);
		Common.moveToElementByJs(driver, btnSubmitLoan); // Firefox
	//	Common.clickOn(driver, btnSubmitLoan);
		Common.jsClick(driver, btnSubmitLoan); // Firefox
		Common.waitForElement(driver, ".//*[contains(text(),'Thank you')]");
		return new PackageVerification_mobile(driver);
	}

	@FindBy (xpath="//div[contains(@class,'docs-header')]/..//*[contains(text(),'Upload')]")
	List<WebElement> upload;
			
	@FindBy (xpath=".//div[contains(@class,'docs-header')]/..//*[contains(text(),'Upload')]/../../../..//div[contains(@class,'docs-name')]")
	List<WebElement> docsName;

	@FindBy (xpath="//div[contains(@class,'docs-header')]/..//*[contains(text(),'Upload')]/../../../..//span[contains(@class,'label')]")
	List<WebElement> docsState;
	
	@FindBy (xpath=".//div[@class='points-marker']/i[@ng-show='!checkOn']")
	WebElement checkAgree;

	@FindBy (xpath=".//button[text()='Ready to upload']")
	WebElement btnUpload;
	
	@FindBy (xpath=".//div[@id='uploader']//input[@type='file']")
	WebElement uploadFile;
	
	@FindBy (xpath=".//button[@id='upload-button'][not(contains(@class,'disabled'))]")
	WebElement btnUploadNow;
				
	@FindBy (xpath=".//*[contains(text(),'eSign documents')]")
	WebElement txtEsign;

	public void uploadWFAR() {
		// TODO Auto-generated method stub
		if (upload.size() > 0) {
			Common.log("  --- " + upload.size() + " Documents are avaiable to upload. ---");
			
			for (int i = 0; i < upload.size(); i++) {
				Common.log("  -> Document <b>" + docsName.get(i).getText() + "</b> is going to be upload.");
				Common.waitForElementClickable(driver,
						"//div[contains(@class,'docs-header')]/..//*[contains(text(),'Upload')]");
				Common.pause(5);
				Common.moveToElementByJs(driver,  txtEsign);
				Common.moveToElementByJs(driver,  upload.get(i)); // Firefox
				//Common.pause(20); // Firefox
				//Common.clickOn(driver, upload.get(i));
				Common.jsClick(driver, upload.get(i)); // Firefox
				Common.log("  -> Clicked on Upload button.");
				Common.pause(6); // Firefox
			//	Common.clickOn(driver, checkAgree); 
				Common.jsClick(driver, checkAgree); // Firefox
				Common.log("  -> Check the 'I have printed, signed and scanned all pages of the document' checkbox.");
				Common.waitForElement(driver, ".//button[text()='Ready to upload']");
			//	Common.clickOn(driver, btnUpload);
				Common.jsClick(driver, btnUpload); // Firefox
				Common.log("  -> Click on 'Ready to Upload' Button.");
				Common.pause(2);
				File file = new File("Resources/Cloud Hosting Agreement.pdf");
				
				Common.type(uploadFile,file.getAbsolutePath());
				Common.log("  -> Upload any Document.");
				Common.waitForElement(driver, ".//button[@id='upload-button'][not(contains(@class,'disabled'))]");
				Common.clickOn(driver, btnUploadNow);
				Common.log("  -> Click on Upload Now button to upload the Document.");
				Common.pause(5);
				Common.waitForElementClickable(driver, ".//div[@class='user-info']");
				Common.pause(20);
				Common.logOptionalAssert("  Verify that 'Optional' state has changed to 'File Uploaded'.");
				if (packageVerification.verifyFileUploaded(docsState, i)) {
					Common.AssertPassed();
				} else {
					Common.AssertFailed();
					PackageIndex.numberOfFailure++;
					Common.makeScreenshot(driver, "fileUploadFailed"+Common.randomNumericValueGenerate(5));
				}
			}
		}
		Common.waitForElementClickable(driver, "//a[text()='Submit loan documents']");
	}
	
	@FindBy (xpath=".//*[@id='upload-modal']//button[contains(text(),'Cancel')]")
	WebElement btnCancel;
	
	@FindBy (xpath=".//*[@id='upload-modal']//span[contains(text(),'Your signed document was successfully uploaded')]")
	WebElement MsgUpload;
	
	public void uploadWFARrequired() {
		// TODO Auto-generated method stub
		if (upload.size() > 0) {
			Common.log("  --- " + upload.size() + " Documents are avaiable to upload. ---");
			
			for (int i = 0; i < upload.size(); i++) {
				Common.log("  -> Document <b>" + docsName.get(i).getText() + "</b> is going to be upload.");
				Common.waitForElementClickable(driver,
						"//div[contains(@class,'docs-header')]/..//*[contains(text(),'Upload')]");
				Common.pause(5);
				Common.moveToElementByJs(driver,  txtEsign);
				Common.moveToElementByJs(driver,  upload.get(i)); // Firefox
			//	Common.clickOn(driver, upload.get(i));
				Common.jsClick(driver, upload.get(i));
				Common.log("  -> Cilcked on Upload button.");
				Common.pause(6); // Firefox
				Common.clickOn(driver, checkAgree);
				Common.log("  -> Check the 'I have printed, signed and scanned all pages of the document' checkbox.");
				Common.waitForElement(driver, ".//button[text()='Ready to upload']");
				Common.clickOn(driver, btnUpload);
				Common.log("  -> Click on 'Ready to Upload' Button.");
				Common.pause(2);
				File file = new File("Resources/Cloud Hosting Agreement.pdf");
				
				Common.type(uploadFile,file.getAbsolutePath());
				Common.log("  -> Upload any Document.");
				Common.waitForElement(driver, ".//button[@id='upload-button'][not(contains(@class,'disabled'))]");
				Common.clickOn(driver, btnUploadNow);
				Common.log("  -> Click on Upload Now button to upload the Document.");
				Common.pause(6);
				//Common.waitForElementClickable(driver, ".//div[@class='user-info']");
                Common.waitForElement(driver, ".//*[@id='upload-modal']//span[contains(text(),'Your signed document was successfully uploaded')]");
				
                try {
					if(Common.isElementDisplayed(MsgUpload))
					{
						Common.clickOn(driver, btnCancel);
					    Common.log("  -> Click on Cancel button to close existing. ");	
					
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("File Uploading Completed");
				}
                
                /*Common.clickOn(driver, btnCancel);
                Common.log("  -> Click on Cancel button to close existing. ");*/
                
                Common.pause(20);
                
				Common.logOptionalAssert("  Verify that 'Incomplete' state has changed to 'File Uploaded'.");
				if (packageVerification.verifyFileUploaded(docsState, i)) {
					Common.AssertPassed();
				} else {
					Common.AssertFailed();
					PackageIndex.numberOfFailure++;
					Common.makeScreenshot(driver, "fileUploadFailed"+Common.randomNumericValueGenerate(5));
				}
			}
		}
		Common.waitForElementClickable(driver, "//a[text()='Submit loan documents']");
	}

	@FindBy (xpath=".//*[contains(text(),'Guarantor')]/..//input[@type='checkbox'][contains(@id,'included')]")
	List<WebElement> checkSigner;

	public void chooseGuarantor() {
		// TODO Auto-generated method stub
	//	Common.moveToElement(checkSigner.get(0), driver); // Added while Implementing Firefox
		Common.moveToElementByJs(driver, checkSigner.get(0));
		Common.clickOn(driver, checkSigner.get(0));
		Common.log("  -> Select Gurantor 1 Check box to enter details.");
	}	

	public void enterGFname() {
		// TODO Auto-generated method stub
		gfname="autoGFname"+Common.generateRandomChars(10);
		Common.type(txtFname.get(1), gfname);
		Common.log("  -> Guarantor First Name: "+txtFname.get(1).getAttribute("value"));
	}

	public void enterGLname() {
		// TODO Auto-generated method stub
		glname="autoGLname"+Common.generateRandomChars(10);
		Common.type(txtLname.get(1), glname);
		Common.log("  -> Guarantor Last Name: "+txtLname.get(1).getAttribute("value"));
	}

	public void enterGEmail() {
		// TODO Auto-generated method stub
		gemail="auto_G_"+Common.generateRandomChars(20);
		Common.type(txtEmail.get(1), gemail+"@mailinator.com");
		Common.log("  -> Guarantor Email : "+txtEmail.get(1).getAttribute("value"));
	}

	public void enterGMobile() {
		// TODO Auto-generated method stub
		Common.type(txtMobile.get(1), mobile);
		Common.log("  -> Guarantor Mobile Number : "+txtMobile.get(1).getAttribute("value"));
	}

	@FindBy (xpath=".//*[contains(text(),'Relationship Manager')]/..//input[@type='checkbox'][contains(@id,'included')]")
	List <WebElement> relationsigner;
	
	public void chooseRManager() {
		// TODO Auto-generated method stub
		Common.moveToElementByJs(driver, relationsigner.get(0)); // Firefox
		Common.clickOn(driver, relationsigner.get(0));
		Common.log("  -> Select Relationship Manager Check box to enter details.");
	}
	
		
	/*public void chooseRManager() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, checkSigner.get(1));
		Common.log("  -> Select Relationship Manager Check box to enter details.");
	}*/

	public void enterRFname(int i) {
		// TODO Auto-generated method stub
		rfname="autoRFname"+Common.generateRandomChars(10);
		Common.type(txtFname.get(i), rfname);
		Common.log("  -> Relationship Manager First Name: "+txtFname.get(i).getAttribute("value"));
	}

	public void enterRLname(int i) {
		// TODO Auto-generated method stub
		rlname="autoRLname"+Common.generateRandomChars(10);
		Common.type(txtLname.get(i), rlname);
		Common.log("  -> Relationship Manager Last Name: "+txtLname.get(i).getAttribute("value"));
	}

	public void enterREmail(int i) {
		// TODO Auto-generated method stub
		remail="auto_R_"+Common.generateRandomChars(20);
		Common.type(txtEmail.get(i), remail+"@mailinator.com");
		Common.log("  -> Relationship Manager Email : "+txtEmail.get(i).getAttribute("value"));
	}

	public void enterRMobile(int i) {
		// TODO Auto-generated method stub
		Common.type(txtMobile.get(i), mobile);
		Common.log("  -> Relationship Manager Mobile Number : "+txtMobile.get(i).getAttribute("value"));
	}
	
	@FindBy (xpath="//span[@class='']//input/..")
	List<WebElement> docDesc;

	public void warfAttachmentRM() {
		// TODO Auto-generated method stub
		checkBoxes.get(5).click();
		Common.log("  -> Selected WAFR Attachment: "+docDesc.get(5).getText());
	}
	
	@FindBy (xpath=".//*[@id='saveDraft']")
	WebElement btnDraft;

	public PackageVerification clickDraft() {
		// TODO Auto-generated method stub
		Common.waitForElement(driver, ".//div[contains(@id,'uploadSuccesfull')]");
		Common.pause(2);
	//	Common.clickOn(driver, btnDraft);
		Common.jsClick(driver, btnDraft); 
		Common.waitForElement(driver, "//table[@id='packages']//tr[1]//td[1][contains(text(),'"+PackageIndexpage_mobile.reference+"')]");
		return new PackageVerification(driver);
	}

	public PackageVerification selectSigner(String string) {
		// TODO Auto-generated method stub
		String start=".//*[contains(text(),'";
		String end="')]/..//span[contains(@class,'checkbox')]";
		WebElement element=null;
		if(string.contains("b"))
		{
			element=driver.findElement(By.xpath(start+fname+end));
			Common.clickOn(driver, element);
			Common.pause(2);
		}
		else if(string.contains("g"))
		{
			element=driver.findElement(By.xpath(start+gfname+end));
			Common.clickOn(driver, element);
			Common.pause(2);

		}
		else if(string.contains("r"))
		{
			element=driver.findElement(By.xpath(start+rfname+end));
			Common.clickOn(driver, element);
			Common.pause(2);
		}
		return new PackageVerification(driver);
	}
	
	@FindBy (id="editSignerBtn")
	WebElement btnEdit;

	public PackageVerification clickEdit() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnEdit);
		Common.pause(2);
		return new PackageVerification(driver);
	}
	
	@FindBy (id="firstSignerName")
	WebElement txtFnameEdit;
	
	public void txtEditFname(String str) {
		// TODO Auto-generated method stub
		if(str.contains("b"))
		{
			fname="autoFname"+Common.generateRandomChars(10);
			Common.type(txtFnameEdit, fname);
			Common.log("  -> First Name: "+txtFnameEdit.getAttribute("value"));
		}
		else if(str.contains("g"))
		{
			gfname="autoGFname"+Common.generateRandomChars(10);
			Common.type(txtFnameEdit, gfname);
			Common.log("  -> Guarantor First Name: "+txtFnameEdit.getAttribute("value"));
		}
		else if(str.contains("r"))
		{
			rfname="autoRFname"+Common.generateRandomChars(10);
			Common.type(txtFnameEdit, rfname);
			Common.log("  -> Relationship Manager First Name: "+txtFnameEdit.getAttribute("value"));	
		}
	}

	@FindBy (id="lastSignerName")
	WebElement txtLnameEdit;
	
	public void txtEditLname(String str) {
		// TODO Auto-generated method stub
		if(str.contains("b"))
		{
			lname="autoLname"+Common.generateRandomChars(10);
			Common.type(txtLnameEdit, lname);
			Common.log("  -> Last Name: "+txtLnameEdit.getAttribute("value"));
		}
		else if(str.contains("g"))
		{
			glname="autoGLname"+Common.generateRandomChars(10);
			Common.type(txtLnameEdit, glname);
			Common.log("  -> Guarantor Last Name: "+txtLnameEdit.getAttribute("value"));
		}
		else if(str.contains("r"))
		{
			rlname="autoRLname"+Common.generateRandomChars(10);
			Common.type(txtLnameEdit, rlname);
			Common.log("  -> Relationship Manager Last Name: "+txtLnameEdit.getAttribute("value"));	
		}
	}
	
	@FindBy (id="signerEmail")
	WebElement txtEmailEdit;

	public void txtEditEmail(String str) {
		// TODO Auto-generated method stub
		if(str.contains("b"))
		{
			email="auto_"+Common.generateRandomChars(20);
			Common.type(txtEmailEdit, email+"@mailinator.com");
			Common.log("  -> Email : "+txtEmailEdit.getAttribute("value"));
		}
		else if(str.contains("g"))
		{
			gemail="auto_G_"+Common.generateRandomChars(20);
			Common.type(txtEmailEdit, gemail+"@mailinator.com");
			Common.log("  -> Guarantor Email : "+txtEmailEdit.getAttribute("value"));
		}
		else if(str.contains("r"))
		{
			remail="auto_R_"+Common.generateRandomChars(20);
			Common.type(txtEmailEdit, remail+"@mailinator.com");
			Common.log("  -> Relationship Manager Email : "+txtEmailEdit.getAttribute("value"));
		}
	}

	@FindBy (id="changeSignerDetails")
	WebElement btnUpdate;
	public PackageVerification clickUpdate() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnUpdate);
		Common.pause(5);
		return new PackageVerification(driver);
	}
	
		public void clickDraftIcon() {
		// TODO Auto-generated method stub
		Common.log(PackageIndexpage_mobile.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='DRAFT']";
		WebElement draftPkg=driver.findElement(By.xpath(start+PackageIndexpage_mobile.reference+end));
		Common.waitForElement(driver, start+PackageIndexpage_mobile.reference+end);
		Common.pause(3);
		Common.jsClick(driver, draftPkg);
	//	Common.clickOn(driver, draftPkg);
	//	Common.clickOn(driver, draftPkg);
		Common.pause(2);
		
	}
		
		
	@FindBy (xpath=".//*[@id='sendDraftPackage']")
	WebElement btnReSendPkg;

	public PackageVerification clickResendPkg() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnReSendPkg);
		Common.pause(2);
		return new PackageVerification(driver);
	}
	

	/*@FindBy (xpath=".//li[contains(@class,'envelope')]")
	WebElement btnSendPkg;

	public PackageVerification clickResendPkg() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnSendPkg);
		Common.pause(2);
		return new PackageVerification(driver);
	}*/
	
	@FindBy (xpath=".//*[contains(text(),'send it')]")
	WebElement btnAcceptSend;

	@FindBy (xpath=".//*[contains(text(),'yes, put to BYPASS')]")
	WebElement btnacceptBYPASSsend;
	
	public PackageVerification acceptByPassConfirmation() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnacceptBYPASSsend);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='BYPASS']";
		Common.waitForElement(driver, start+PackageIndexpage_mobile.reference+end);
		System.out.println("BYPASS icon avaiable.");
		return new PackageVerification(driver);
	}
	
	@FindBy (xpath=".//*[contains(text(),'yes, archive it')]")
	WebElement btnacceptarchive;
	
	
	
	public PackageVerification acceptArchiveConfirmation() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnacceptarchive);
		/*String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='BYPASS']";
		Common.waitForElement(driver, start+PackageIndexpage.reference+end);
		System.out.println("Sent icon avaiable.");*/
		return new PackageVerification(driver);
	}

	@FindBy (xpath=".//button[@class='confirm']")
	WebElement btnOKtarchive;
	public PackageVerification acceptArchiveOK() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnOKtarchive);
		/*String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='BYPASS']";
		Common.waitForElement(driver, start+PackageIndexpage.reference+end);
		System.out.println("Sent icon avaiable.");*/
		return new PackageVerification(driver);
	}
	
	
	
	public PackageVerification acceptConfirmation() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnAcceptSend);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='SENT']";
		Common.waitForElement(driver, start+PackageIndexpage_mobile.reference+end);
		System.out.println("Sent icon avaiable.");
		return new PackageVerification(driver);
	}

	public void clickSentIcon() {
		Common.log(PackageIndexpage_mobile.reference);
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='SENT']";
		WebElement sentPkg=driver.findElement(By.xpath(start+PackageIndexpage_mobile.reference+end));
		Common.waitForElement(driver, start+PackageIndexpage_mobile.reference+end);
		Common.clickOn(driver, sentPkg);
	//	Common.clickOn(driver, draftPkg);
		Common.pause(2);
		
	}
	
	public void clickRefpackageontable() {
		Common.log(PackageIndexpage_mobile.reference);
		/*String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='SENT']";*/
		WebElement refPkg=driver.findElement(By.xpath("//table[@id='packages']//tr[1]//td[1][contains(text(),'"+PackageIndexpage_mobile.reference+"')]"));
		Common.waitForElement(driver, refPkg);
		Common.pause(3);
		Common.clickOn(driver, refPkg);
	//	Common.jsClick(driver, refPkg);	
	//	Common.clickOn(driver, draftPkg);
		Common.pause(2);
}
	
	@FindBy (xpath=".//*[@id='bypassPackage']")
	WebElement btnbypassPkg;

	public PackageVerification clickByPassPkg() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnbypassPkg);
		Common.pause(2);
		return new PackageVerification(driver);
	}
	
	@FindBy (xpath=".//*[@id='archivePackage']")
	WebElement btnArchivePkg;

	public PackageVerification clickArchivePkg() {
		// TODO Auto-generated method stub
		Common.clickOn(driver, btnArchivePkg);
		Common.pause(2);
		return new PackageVerification(driver);
	}

	@FindBy (xpath=".//*[@id='activeCombo']")
	WebElement drpdwnFilter;
	public PackageVerification ChooseInactive() {
		// TODO Auto-generated method stub
		Common.selectFromCombo(drpdwnFilter, "inactive");
		Common.pause(2);
		return new PackageVerification(driver);
	}

	@FindBy (xpath=".//button[@id='optout']")
	WebElement btnOptOut;
	
		public PackageVerification clickOptout() {
		// TODO Auto-generated method stub
		Common.pause(60);
		Common.clickOn(driver, btnOptOut);
		Common.pause(20);
	//	Common.closeCurrentTab(driver);
		return new PackageVerification(driver);
	}

				
		public PackageVerification SwitchtoDashboard() {
			// TODO Auto-generated method stub
			Common.SwitchtoTab(driver, 0);
			Common.pause(20);
			return new PackageVerification(driver);
		}

			
		@FindBy (xpath="//div[@class='ui-dialog-buttonset']//span[contains(text(),'Ok')]")
		WebElement btnOptOK;
		
		public PackageVerification ChooseOptradio() {
			// TODO Auto-generated method stub
			for(int i=1;i<=2;i++)
			{
					    
			int j = Common.randBetween(1, 2);
			WebElement clickradbutton=driver.findElement(By.xpath("//*[@id='optout-form']//table//tr["+j+"]//td[1]//input[@type='radio']"));
		//	Common.clickOn(driver, clickradbutton);	
			Common.jsClick(driver, clickradbutton);
			break;
			}
			Common.pause(2);
			Common.clickOn(driver, btnOptOK);
			Common.waitForElement(driver, "//p[contains(text(),'This transaction has been opted out.')]");
			Common.pause(5);
			return new PackageVerification(driver);
		}
		
				
		@FindBy (xpath=".//button[@id='decline']")
		WebElement btnDecline;
		
		public PackageVerification clickDecline() {
			// TODO Auto-generated method stub
			Common.pause(60);
			Common.clickOn(driver, btnDecline);
			Common.pause(20);
		//	Common.closeCurrentTab(driver);
			return new PackageVerification(driver);
		}

		public PackageVerification ChooseDeclineradio() {
			// TODO Auto-generated method stub
			for(int i=1;i<=3;i++)
			{
					    
			int j = Common.randBetween(1, 3);
			WebElement clickradDecbutton=driver.findElement(By.xpath("//*[@id='decline-form']//table//tr["+j+"]//td[1]//input[@type='radio']"));
		//	Common.clickOn(driver, clickradDecbutton);	
			Common.jsClick(driver, clickradDecbutton);
			break;
			}
			Common.pause(2);
			Common.clickOn(driver, btnOptOK);
			Common.waitForElement(driver, "//p[contains(text(),'This transaction has been declined.')]");
			Common.pause(5);
			return new PackageVerification(driver);
		}

		@FindBy (xpath="//button[@class='confirm']")
		WebElement OnlyBorrowerConfirmation;
		public PackageVerification acceptOnlyBorrowerConfirmation() {
			// TODO Auto-generated method stub
			Common.clickOn(driver, OnlyBorrowerConfirmation);
			/*String start=".//*[contains(text(),'";
			String end="')]/..//*[@title='SENT']";
			Common.waitForElement(driver, start+PackageIndexpage.reference+end);
			System.out.println("Sent icon avaiable.");*/
			return new PackageVerification(driver);
		}

	
		@FindBy (xpath=".//*[contains(text(),'yes, complete it')]")
		WebElement btnacceptMarkcomple;
		
		public PackageVerification acceptMarkasCompleteConfirmation() {
			// TODO Auto-generated method stub
			Common.clickOn(driver, btnacceptMarkcomple);
			String start=".//*[contains(text(),'";
			String end="')]/..//*[@title='COMPLETED']";
			Common.waitForElement(driver, start+PackageIndexpage_mobile.reference+end);
			System.out.println("COMPLETED icon avaiable.");
			return new PackageVerification(driver);
		}

		@FindBy (xpath=".//*[@id='markAsComplete']")
		WebElement btntMarkcomple;
		
		public PackageVerification clickMarkasCompletePkg() {
			
			Common.clickOn(driver, btntMarkcomple);
			Common.pause(2);
			return new PackageVerification(driver);
			
		}

		public void clickByPassIcon() {
			Common.log(PackageIndexpage_mobile.reference);
			String start=".//*[contains(text(),'";
			String end="')]/..//*[@title='BYPASS']";
			WebElement bypassiconPkg=driver.findElement(By.xpath(start+PackageIndexpage_mobile.reference+end));
			Common.waitForElement(driver, start+PackageIndexpage_mobile.reference+end);
			Common.highlightElement(driver, bypassiconPkg);
			//Common.pause(50);
			Common.clickOn(driver, bypassiconPkg);
		//	Common.clickOn(driver, draftPkg);
			Common.pause(2);
			
		}
		
	/*	@FindBy (xpath=".//li[contains(@class,'pencil')]//span[contains(text(),'Set to Draft')]")
		WebElement btnSetToDraftPkg;
		
		String start=".//*[contains(text(),'";
		String end="')]/..//*[@title='BYPASS']";
		WebElement bypassiconPkg=driver.findElement(By.xpath(start+PackageIndexpage.reference+end));*/
		
		@FindBy (xpath=".//li[contains(@class,'pencil')][not(contains(@class,'disabled'))]//span[contains(text(),'Set to Draft')]")
		WebElement btnSetToDraftenable;

		public PackageVerification clickSetToDrraft2() {
			for(int i=1;i<6;i++)
			{
				if(Common.isElementDisplayed(btnSetToDraftenable))
				{
					Common.clickOn(driver, btnSetToDraftenable);
					Common.log(" --> Clicked on Set To Draft button ");
					break;
				}
				else
				{
					
					//clickByPassIcon();
					WebElement refPkg1=driver.findElement(By.xpath("//table[@id='packages']//tr[1]//td[1][contains(text(),'"+PackageIndexpage_mobile.reference+"')]"));
					/*Common.waitForElement(driver, refPkg1);
					Common.clickOn(driver, refPkg1);*/
					//Common.log(" --> Click on ESC button ");
					driver.findElement(By.xpath(("//table[@id='packages']//tr[1]//td[1][contains(text(),'"+PackageIndexpage_mobile.reference+"')]"))).sendKeys(Keys.ESCAPE); 
				    String start=".//*[contains(text(),'";
					String end="')]/..//*[@title='BYPASS']";
					WebElement bypassiconPkg1=driver.findElement(By.xpath(start+PackageIndexpage_mobile.reference+end));
				//	Common.waitForElement(driver, start+PackageIndexpage.reference+end);
					Common.highlightElement(driver, bypassiconPkg1);
				//	Common.pause(30);
					Common.log(" --> Click on By-Pass Icon again " );
					Common.clickOn(driver, bypassiconPkg1);
					Common.log(" --> Click on By-Pass Icon done at iteration "+ i);
				}
						
			}
			return new PackageVerification(driver);
		}

		/*@FindBy (xpath=".//li[contains(@class,'pencil')][not(contains(@class,'disabled'))]//span[contains(text(),'Set to Draft')]")
		WebElement btnSetToDraftenable;*/
		
		/*public PackageVerification clickSetToDrraft1() throws InterruptedException {
			// TODO Auto-generated method stub
			//String emailSubject=".//div[@title='FROM']/..//div[contains(text(),'"+reference+"')]";
			for(int i=0;i<5;i++)
			{
				Common.pause(20);		
					try {
						if(Common.isElementDisplayed(btnSetToDraftenable))  {
							Common.clickOn(driver, btnSetToDraftenable);
						//	driver.findElement(By.xpath(".//li[contains(@class,'pencil')][not(contains(@class,'disabled'))]//span[contains(text(),'Set to Draft')]")).click();
							System.out.println("Iteration " +i);
							break;
							//return new PackageVerification(driver);
						}
					} catch (Exception e) {
						
						e.printStackTrace();
					}
					clickByPassIcon();
				//Common.log("Set To Draft button found in iteration "+i);
			}
			return new PackageVerification(driver);
		}*/
		
		public PackageVerification clickSetToDrraft() {
			// TODO Auto-generated method stub
			Common.waitForElementClickable(driver, btnSetToDraftenable);
			Common.clickOn(driver, btnSetToDraftenable);
			Common.pause(2);
			return new PackageVerification(driver);
		}
		
		@FindBy (xpath=".//*[contains(text(),'yes, return to DRAFT')]")
		WebElement btnSetToDraftcomple;
		
		public PackageVerification acceptSetToFraftConfirmation() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			Common.clickOn(driver, btnSetToDraftcomple);
			/*String start=".//*[contains(text(),'";
			String end="')]/..//*[@title='BYPASS']";
			Common.waitForElement(driver, start+PackageIndexpage.reference+end);
			System.out.println("BYPASS icon avaiable.");*/
			clickRefpackageontable();
			return new PackageVerification(driver);
		}

		
		/*@FindBy (xpath=".//input[@title='toggle to include/exclude attachment'][(contains(@class,'wafrAttachmentRequired' ))][@type='checkbox']")
		List<WebElement> checkrequiredBoxes;*/
		
		@FindBy (xpath="//span[@class='labeldx']//input")
		List<WebElement> checkrequiredBoxes;
		
		public void chooserequired() {
			// TODO Auto-generated method stub
			int random1=Common.randBetween(0, checkrequiredBoxes.size()-1);
			Common.log("  -> "+(random1+1)+" Attachments going to be select.");
			for(int i=0;i<=random1;i++)
			{
				checkrequiredBoxes.get(i).click();
				Common.log("  -> "+attachmentTitle.get(i).getText());
				Common.pause(2);
			}	
			
		}

		@FindBy (xpath=".//*[@id='trashPackage']")
		WebElement btnTrash;
		
		public PackageVerification clickTrashPkg() {
			// TODO Auto-generated method stub
			Common.clickOn(driver, btnTrash);
			Common.pause(2);
			return new PackageVerification(driver);
		}
		
		
	
		@FindBy (xpath=".//*[contains(text(),'yes, trash it')]")
		WebElement btnacceptTrash;
		
		public PackageVerification acceptTrashConfirmation() {
			// TODO Auto-generated method stub
			Common.clickOn(driver, btnacceptTrash);
			/*String start=".//*[contains(text(),'";
			String end="')]/..//*[@title='BYPASS']";
			Common.waitForElement(driver, start+PackageIndexpage.reference+end);
			System.out.println("BYPASS icon avaiable.");*/
			return new PackageVerification(driver);
		}

		@FindBy (xpath=".//*[@id='showMyPackages']")
		WebElement ChkbxShowMyPackage;
		
		public PackageVerification clickShowMyPackagesn() {
			// TODO Auto-generated method stub
			Common.clickOn(driver, ChkbxShowMyPackage);
			return new PackageVerification(driver);
		}
		
		@FindBy (xpath=".//*[@id='reviewPackage']")
		WebElement btnreview;

		public PackageVerification clickreviewPkg() {
			// TODO Auto-generated method stub
			Common.clickOn(driver, btnreview);
			Common.pause(2);
			return new PackageVerification(driver);
		}
}
