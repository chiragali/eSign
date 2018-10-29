package com.automation.index;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.indexpage.PackageIndexpage;
import com.automation.init.SeleniumInit;
import com.automation.utility.Common;
import com.automation.utility.TestData;

public class PackageIndex_mobile extends SeleniumInit{

public static int step, numberOfFailure;

	@Test
	public void TC_DB_01()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_01: Verify submit signed loan document with one signer/borrower.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
			
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	
	@Test
	public void TC_DB_02()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_02: Verify E2E workflow for package creation and upload signed document for three signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}


	@Test
	public void TC_DB_03()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_03: Verify E2E workflow for package creation and upload signed document with two signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
	/*	Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		*/
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(1);
		packageIndexpage.enterRLname(1);
		packageIndexpage.enterREmail(1);
		packageIndexpage.enterRMobile(1);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
	/*	Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");
*/
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		/*Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}

	@Test
	public void TC_DB_04()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_04: Verify Package status set to Draft.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
	/*	Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(1);
		packageIndexpage.enterRLname(1);
		packageIndexpage.enterREmail(1);
		packageIndexpage.enterRMobile(1);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
	*/		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
			
		/*Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}

	@Test
	public void TC_DB_05()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_05: Verify email notification resend on email address update for borrower.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logMandetoryAssert("Verify that user has received and package creation notification.");
		if(packageVerification.verifyEmail())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		driver.close();
		Common.SwitchtoTab(driver, 0);
		Common.pause(2);
		
		Common.clickOn(driver, driver.findElement(By.xpath(".//td[text()='"+PackageIndexpage.reference+"']")));
		
		Common.logstep("Step "+ (step++) +" Select Borrower from Package Information panel.");
		packageVerification=packageIndexpage.selectSigner("b");
	
		Common.logstep("Step "+ (step++) +" Click on Edit/Pencil icon to edit signer information.");
		packageVerification=packageIndexpage.clickEdit();
	
		Common.logstep("Step "+ (step++) +" Update information for Borrower.");
		packageIndexpage.txtEditFname("b");
		packageIndexpage.txtEditLname("b");
		packageIndexpage.txtEditEmail("b");
		
		Common.logstep("Step "+ (step++) +" Click 'Save' icon to update signer details.");
		packageVerification=packageIndexpage.clickUpdate();
	
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logMandetoryAssert("Verify that user has received and package creation notification after update email.");
		if(packageVerification.verifyEmail())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_06()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_06: Verify resend email notiication on update email address for guarantor.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.gemail);
		
		Common.logMandetoryAssert("Verify that user has received and package creation notification.");
		if(packageVerification.verifyEmail())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		driver.close();
		Common.SwitchtoTab(driver, 0);
		Common.pause(2);
		
		Common.clickOn(driver, driver.findElement(By.xpath(".//td[text()='"+PackageIndexpage.reference+"']")));
		
		Common.logstep("Step "+ (step++) +" Select Guarantor from Package Information panel.");
		packageVerification=packageIndexpage.selectSigner("g");
	
		Common.logstep("Step "+ (step++) +" Click on Edit/Pencil icon to edit signer information.");
		packageVerification=packageIndexpage.clickEdit();
	
		Common.logstep("Step "+ (step++) +" Update information for Borrower.");
		packageIndexpage.txtEditFname("g");
		packageIndexpage.txtEditLname("g");
		packageIndexpage.txtEditEmail("g");
		
		Common.logstep("Step "+ (step++) +" Click 'Save' icon to update signer details.");
		packageVerification=packageIndexpage.clickUpdate();
	
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.gemail);
		
		Common.logMandetoryAssert("Verify that user has received and package creation notification after update email.");
		if(packageVerification.verifyEmail())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_07()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_07: Verify resend email notiication on update email address for relationship manager.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.remail);
		
		Common.logMandetoryAssert("Verify that user has received and package creation notification.");
		if(packageVerification.verifyEmail())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		driver.close();
		Common.SwitchtoTab(driver, 0);
		Common.pause(2);
		
		Common.clickOn(driver, driver.findElement(By.xpath(".//td[text()='"+PackageIndexpage.reference+"']")));
		
		Common.logstep("Step "+ (step++) +" Select Relationship Manager from Package Information panel.");
		packageVerification=packageIndexpage.selectSigner("r");
	
		Common.logstep("Step "+ (step++) +" Click on Edit/Pencil icon to edit signer information.");
		packageVerification=packageIndexpage.clickEdit();
	
		Common.logstep("Step "+ (step++) +" Update information for Relationship Manager.");
		packageIndexpage.txtEditFname("r");
		packageIndexpage.txtEditLname("r");
		packageIndexpage.txtEditEmail("r");
		
		Common.logstep("Step "+ (step++) +" Click 'Save' icon to update signer details.");
		packageVerification=packageIndexpage.clickUpdate();
	
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.remail);
		
		Common.logMandetoryAssert("Verify that user has received and package creation notification after update email.");
		if(packageVerification.verifyEmail())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_08()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_08: Verify Draft Package status changed to Sent for one signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
	/*	Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(1);
		packageIndexpage.enterRLname(1);
		packageIndexpage.enterREmail(1);
		packageIndexpage.enterRMobile(1);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
	*/		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save as Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Package Name' to select.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickResendPkg();
		
		Common.logstep("Step "+ (step++) +" Accept confirmation to send package information notification.");
		packageVerification=packageIndexpage.acceptConfirmation();
		
		
		Common.logMandetoryAssert("Verify that package status changed to 'SENT' from Draft.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			numberOfFailure++;
		}
		
		
		/*
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_09()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_09: Verify E2E sign workflow for resent package for one signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.pause(2);
		
		Common.logstep("Step "+ (step++) +" Click on 'Package Name' to select.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickResendPkg();
		
		Common.logstep("Step "+ (step++) +" Accept confirmation to send package information notification.");
		packageVerification=packageIndexpage.acceptConfirmation();
		
		Common.pause(2);	
		
		Common.logMandetoryAssert("Verify that package status changed to 'SENT' from Draft.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			numberOfFailure++;
		}
		
		
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		/*Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		*/
	/*	Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
	/*	Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
	*/	
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_10()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_10: Verify Draft Package status changed to Sent for three signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.pause(3);
		
		Common.logstep("Step "+ (step++) +" Click on 'Package Name' to select.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickResendPkg();
		
		Common.logstep("Step "+ (step++) +" Accept confirmation to send package information notification.");
		packageVerification=packageIndexpage.acceptConfirmation();
		
		Common.pause(20);
		
		Common.logMandetoryAssert("Verify that package status changed to 'SENT' from Draft.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			numberOfFailure++;
		}
		
		/*	Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		*/
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_11()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_11: Verify E2E sign workflow for resent package for three signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		/*Common.logstep("Step "+ (step++) +" Click on Draft Icon to select.");
		packageIndexpage.clickDraftIcon();*/
		
		Common.logstep("Step "+ (step++) +" Click on 'Package Name' to select.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickResendPkg();
		
		Common.logstep("Step "+ (step++) +" Accept confirmation to send package information notification.");
		packageVerification=packageIndexpage.acceptConfirmation();
		
		Common.logMandetoryAssert("Verify that package status changed to 'SENT' from Draft.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	
	@Test
	public void TC_DB_12()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_12: Verify error message for create package without sign document for one signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
	
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickOnlySendPkg();
	
		Common.waitForElement(driver, ".//*[text()='Error']");
		
		Common.logMandetoryAssert("Verify validation error message that signature document is not available to sign.");
		if(packageVerification.verifyDocumentValidation())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		/*Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
	*/	if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}

	@Test
	public void TC_DB_13()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_13:  Verify error message for create package without sign document for three signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
	/*	Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
	*/	
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickOnlySendPkg();
		
		Common.waitForElement(driver, ".//*[text()='Error']");
		
		Common.logMandetoryAssert("Verify validaation error message that signature document is not available to sign.");
		if(packageVerification.verifyDocumentValidation())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		/*Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		*/
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}


 @Test
 public void TC_DB_14()
 {
  step=1;
  numberOfFailure=0;
  
  Common.logcase("TC_DB_14:  Verify the DRAFT status for create and send package without signing document for one signer.");
 
  Common.logstep("Step "+ (step++) +" Enter Username.");
  packageIndexpage.enterUsername(TestData.getUserName());
  
  Common.logstep("Step "+ (step++) +" Enter Password.");
  packageIndexpage.enterPassword(TestData.getPassword());
  
  Common.logstep("Step "+ (step++) +" Click on Login button.");
  packageVerification=packageIndexpage.clickLogin();
  Common.pause(5);
  Common.logMandetoryAssert("Verify user has logged in successfuly.");
  
  if(packageVerification.verifyLogin())
  {
   Common.AssertPassed();
   Assert.assertTrue(true);
  }
  else
  {
   Common.AssertFailed();
   Common.makeScreenshot(driver, "LoginFailed");
   numberOfFailure++;
  }
  
  Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
  packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
 
  Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
  packageVerification=packageIndexpage.clickCreate();

  Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
  packageIndexpage.enterReference();
  packageIndexpage.enterFname();
  packageIndexpage.enterLname();
  packageIndexpage.enterEmail();
  packageIndexpage.enterMobile();
  
  Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
  packageIndexpage.chooseAttchments();
  
  /*Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
  packageIndexpage.chooseGuarantor();
  packageIndexpage.enterGFname();
  packageIndexpage.enterGLname();
  packageIndexpage.enterGEmail();
  packageIndexpage.enterGMobile();
  
  Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
  packageIndexpage.chooseRManager();
  packageIndexpage.enterRFname(2);
  packageIndexpage.enterRLname(2);
  packageIndexpage.enterREmail(2);
  packageIndexpage.enterRMobile(2);
  
  Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
  packageIndexpage.warfAttachmentRM();*/
   
  Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
  packageVerification=packageIndexpage.clickCreatepkg();
  
  /*Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
  packageVerification=packageIndexpage.chooseServiceCheckBox();
 
  Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
  packageIndexpage.uploadFile();
  */
  
  Common.logstep("Step "+ (step++) +" Click on 'Save Draft' button.");
  packageVerification=packageIndexpage.clickDraft();
  
  Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
  if(packageVerification.verifyDraftPkg())
  {
   Common.AssertPassed();
   Assert.assertTrue(true);
  }
  else
  {
   Common.AssertFailed();
   Common.makeScreenshot(driver, "PackageCreatedFailed");
   numberOfFailure++;
  }
  
  Common.logstep("Step "+ (step++) +" Click on 'Package Name' to select.");
	packageIndexpage.clickRefpackageontable();
  
  Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
  packageVerification=packageIndexpage.clickResendPkg();
  
  Common.logstep("Step "+ (step++) +" Accept confirmation to send package information notification.");
  packageVerification=packageIndexpage.acceptConfirmation();
  
  Common.waitForElement(driver, ".//*[text()='Error']");
  
  Common.logMandetoryAssert("Verify validation error message that signature document is not available to sign.");
  if(packageVerification.verifyDocumentValidation())
  {
   Common.AssertPassed();
   Assert.assertTrue(true);
  }
  else
  {
   Common.AssertFailed();
   Common.makeScreenshot(driver, "PackageCreatedFailed");
   numberOfFailure++;
  }
  
 
  if(numberOfFailure > 0)
  {
   Assert.assertTrue(false);
  }
  else
  {
   Assert.assertTrue(true);
  }
 }
	
	
	
	@Test
	public void  TC_DB_15()
	{
		
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_15: Verify signature document required error message for DRAFT package with three signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save as Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on 'Package Name' to select.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickResendPkg();
		
		packageVerification=packageIndexpage.acceptConfirmation();
		
        Common.waitForElement(driver, ".//*[text()='Error']");
		
		Common.logMandetoryAssert("Verify validation error message that signature document is not available to sign.");
		
		if(packageVerification.verifyDocumentValidation())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ValidationFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void  TC_DB_26()
	{
		
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_26:  Verify BYPASS functionality for package in DRAFT status.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save as Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on 'Package Name' to select.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'BY-PASS' button.");
		packageVerification=packageIndexpage.clickByPassPkg();
		
		packageVerification=packageIndexpage.acceptByPassConfirmation();
		
		Common.logMandetoryAssert("Verify that package is BY-PASS successfully and found in package table with BY-PASS Status.");
		
		if(packageVerification.verifyBYPASSpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ByPassFailed");
			numberOfFailure++;
		}
		
        /*Common.waitForElement(driver, ".//*[text()='Error']");
		
		Common.logMandetoryAssert("Verify validation error message that signature document is not available to sign.");
		if(packageVerification.verifyDocumentValidation())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}*/
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
	}
	
	@Test
	public void TC_DB_27()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_27: Verify BYPASS functionality for package in SENT status for one signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with SENT Status.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SentIconFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Refrence name package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'BY-PASS' button.");
		packageVerification=packageIndexpage.clickByPassPkg();
		
		packageVerification=packageIndexpage.acceptByPassConfirmation();
		
		Common.logMandetoryAssert("Verify that package is BY-PASS successfully and found in package table with BY-PASS Status.");
		
		if(packageVerification.verifyBYPASSpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "BYPASSFailed");
			numberOfFailure++;
		}
		
		/*Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}*/
		
		
		
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_28()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_28: Verify BYPASS functionality for package in SENT status for three signer.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with SENT Status.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'BY-PASS' button.");
		packageVerification=packageIndexpage.clickByPassPkg();
		
		packageVerification=packageIndexpage.acceptByPassConfirmation();
		
		Common.logMandetoryAssert("Verify that package is BY-PASS successfully and found in package table with BY-PASS Status.");
		
		if(packageVerification.verifyBYPASSpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "BYPASSFailed");
			numberOfFailure++;
		}
		
		
		/*Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void  TC_DB_23()
	{
		
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_23:  Verify Archived functionality for status 'DRAFT'.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save as Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on 'Draft' icon to send package.");
		packageIndexpage.clickDraftIcon();
		
		Common.logstep("Step "+ (step++) +" Click on 'Archive' button.");
		packageVerification=packageIndexpage.clickArchivePkg();
		
		packageVerification=packageIndexpage.acceptArchiveConfirmation();
		
		packageVerification=packageIndexpage.acceptArchiveOK();
		
		Common.logstep("Step "+(step++)+" Choose Inactive from Filter");
		packageVerification=packageIndexpage.ChooseInactive();
		//packageIndexpage.ChooseInactive();
		
		Common.logMandetoryAssert("Verify that package is ARCHIVED successfully and found in package table with ARCHIVED Status.");
		
		if(packageVerification.verifyArchivedpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ArchivedFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}		
	
	@Test
	public void TC_DB_24()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_24: Verify Archived functionality for status 'OPTED_OUT'.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ArchivedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		/*Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();*/
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Opt Out' button");
		packageVerification=packageIndexpage.clickOptout();
		
		Common.logstep("Step "+ (step++) +" Choose reason from Opt out radio box");
		packageVerification=packageIndexpage.ChooseOptradio();
		
		Common.logstep("Step " +(step++) + " Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logMandetoryAssert("Verify that package is Opted-Out successfully and found in package table with OPTED-OUT Status.");
		
		if(packageVerification.verifyOptOutIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "OptOutFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on Reference name package ");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Archive' button.");
		packageVerification=packageIndexpage.clickArchivePkg();
		
		packageVerification=packageIndexpage.acceptArchiveConfirmation();
		
		packageVerification=packageIndexpage.acceptArchiveOK();
		
		Common.logstep("Step "+(step++)+" Choose Inactive from Filter");
		packageVerification=packageIndexpage.ChooseInactive();
		//packageIndexpage.ChooseInactive();
		
		Common.logMandetoryAssert("Verify that package is ARCHIVED successfully and found in package table with ARCHIVED Status.");
		
		if(packageVerification.verifyArchivedpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ArchivedFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		/*Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}*/
		
		/*Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	*/
	
		/*Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}*/
		
	}
	
	@Test
	public void TC_DB_25()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_25 : Verify Archived functionality for status 'DECLINED'.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		/*Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();*/
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Decline' button");
		packageVerification=packageIndexpage.clickDecline();
		
		Common.logstep("Step "+ (step++) +" Choose reason from Decline radio box");
		packageVerification=packageIndexpage.ChooseDeclineradio();
		
		Common.logstep("Step " +(step++) + "Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logMandetoryAssert("Verify that package is Declined successfully and found in package table with DECLINED Status.");
		
		if(packageVerification.verifyDeclinedIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "DeclineFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sent' icon to send package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Archive' button.");
		packageVerification=packageIndexpage.clickArchivePkg();
		
		packageVerification=packageIndexpage.acceptArchiveConfirmation();
		
		packageVerification=packageIndexpage.acceptArchiveOK();
		
		Common.logstep("Step "+(step++)+" Choose Inactive from Filter");
		packageVerification=packageIndexpage.ChooseInactive();
		//packageIndexpage.ChooseInactive();
		
		Common.logMandetoryAssert("Verify that package is ARCHIVED successfully and found in package table with ARCHIVED Status.");
		
		if(packageVerification.verifyArchivedpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ArchivedFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		/*Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}*/
		
		/*Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	*/
	
		/*Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}*/
		
	}
	
	@Test
	public void TC_DB_22()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_22: Verify Archived functionality for status 'COMPLETED'.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}
		
		Common.logstep("Step " +(step++) + "Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logMandetoryAssert("Verify that package is Completed successfully and found in package table with COMPLETED Status.");
		
		if(packageVerification.verifyCompletedIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCompletedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Archive' button.");
		packageVerification=packageIndexpage.clickArchivePkg();
		
		packageVerification=packageIndexpage.acceptArchiveConfirmation();
		
		packageVerification=packageIndexpage.acceptArchiveOK();
		
		Common.logstep("Step "+(step++)+" Choose Inactive from Filter");
		packageVerification=packageIndexpage.ChooseInactive();
		//packageIndexpage.ChooseInactive();
		
		Common.logMandetoryAssert("Verify that package is ARCHIVED successfully and found in package table with ARCHIVED Status.");
		
		if(packageVerification.verifyArchivedpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ArchiveFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		/*Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}*/
		
		/*Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	*/
	
		/*Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}*/
		
	}
	
	@Test
	public void TC_DB_29()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_29: Verify BYPASS functionality for package in SENT status for three signer when borrower has already signed.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signOnlyBorrowerDocument(step,PackageIndexpage.email,"b");
		
		Common.logstep("Step " +(step++) + "Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logstep("Step "+ (step++) +" Click on Package Row");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) + " Verify the package signed by Borrower is in SENT Status.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SentIconFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +"Verify that only Borrower has signed the package");
		
		if(packageVerification.verifyOnlyBorrowerSign())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "BorrowerSignFailed");
			numberOfFailure++;
		}
		
					
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		
		Common.logstep("Step "+ (step++) +" Click on 'BY-PASS' button.");
		packageVerification=packageIndexpage.clickByPassPkg();
		
		packageVerification=packageIndexpage.acceptByPassConfirmation();
		
		Common.waitForElement(driver, ".//*[text()='Error']");
		
		Common.logMandetoryAssert("Verify validation error message that package is not allowed to do BY-PASS when only Borrower has signed.");
		
		if(packageVerification.verifyDocumentValidation())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ByPassValidationFailed");
			numberOfFailure++;
		}
		
		packageVerification=packageIndexpage.acceptOnlyBorrowerConfirmation();
		
		//packageVerification=packageIndexpage.acceptConfirmation();
		
		/*Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");*/
		
		/*Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		/*Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_30()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_30: Verify 'mark as complete' functionality for READY status  package.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		/*Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();*/
		
		Common.logstep("Step " +(step++) + " Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logstep("Step " +(step++) + " Verify Ready Icon");
		
		if(packageVerification.verifyReadyIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReadyIconFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on Mark as Complete button");	
		
		packageIndexpage.clickRefpackageontable();
		packageVerification=packageIndexpage.clickMarkasCompletePkg();
		
		
		packageVerification=packageIndexpage.acceptMarkasCompleteConfirmation();
		
		Common.logstep("Step "+ (step++) +" Verify the Completed Icon for the package ");
		
		if(packageVerification.verifyCompletedIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "CompletedIconFailed");
			numberOfFailure++;
		}
		
		
		/*Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	*/
	
		/*Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}*/
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_31() throws InterruptedException
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_31: Verify 'Set to Draft' functionality from BY-PASS status");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with SENT Status.");
		
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SentIconPackageFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'BY-PASS' button.");
		packageVerification=packageIndexpage.clickByPassPkg();
		
		packageVerification=packageIndexpage.acceptByPassConfirmation();
		
		Common.logMandetoryAssert("Verify that package is BY-PASS successfully and found in package table with BY-PASS Status.");
		
		if(packageVerification.verifyBYPASSpkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ByPassPackageFailed");
			numberOfFailure++;
		}
		
		/*Common.logstep("Step " +(step++) + "Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();*/
		
		Common.logstep("Step "+ (step++) +" Click on 'BY-PASS' Icon.");
		
		packageIndexpage.clickRefpackageontable();
		
		packageIndexpage.clickByPassIcon();
		
		/*Common.logstep("Step "+ (step++) +" Click on 'Set To Draft' Icon.");
		packageVerification=packageIndexpage.clickSetToDrraft();*/
		
		Common.logstep("Step "+ (step++) +" Click on 'Set To Draft' Icon.");
		packageVerification=packageIndexpage.clickSetToDrraft2();
		
		packageVerification=packageIndexpage.acceptSetToFraftConfirmation();
		//packageIndexpage.clickRefpackageontable();
		
	   Common.logMandetoryAssert("Verify that Icon changes to 'DRAFT' ");
	   
		if(packageVerification.verifyDraftIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "DraftIconFailed");
			numberOfFailure++;
		}
		
		
		/*Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_32()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_32: Verify the functionality when only 'required' checkbox is clicked.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		
		Common.pause(5);
		Common.logMandetoryAssert(" Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		/*Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();*/
		
		Common.logstep("Step "+ (step++) +" Choose any random Required Attachments.");
		packageIndexpage.chooserequired();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		//packageIndexpage.uploadWFAR();
		
		packageIndexpage.uploadWFARrequired();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void TC_DB_33()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase(" TC_DB_33: Verify Trash Functionality for SENT Icon.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with SENT Status.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Trash' button.");
		packageVerification=packageIndexpage.clickTrashPkg();
		packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		packageVerification=packageIndexpage.acceptArchiveOK();
		
		Common.logstep("Step "+(step++)+" Choose Inactive from Filter");
		packageVerification=packageIndexpage.ChooseInactive();
		//packageIndexpage.ChooseInactive();
		
		Common.logMandetoryAssert("Verify that package is Trashed successfully and found in package table with TRASHED Status.");
		packageIndexpage.clickRefpackageontable();
		
		if(packageVerification.verifyTrashPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
			
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "TrashFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
		
		/*Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.log("==============================================<br><b><h2> &nbsp;&nbsp; Complete sign document process for Borrower.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.email,"b");
			
		Common.log("==============================================<br><b><h2> Complete sign document process for Guarantor.</h2></b>==============================================<br>");
		step=signDocument(step,PackageIndexpage.gemail,"g");

		Common.log("==============================================<br><b><h2> &nbsp;&nbsp;Complete sign document process for Relationship Manager.</h2></b>&nbsp;&nbsp;==============================================<br>");
		step=signDocument(step,PackageIndexpage.remail,"r");
		
		Common.logMandetoryAssert("Verify that Borrower has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.email,"b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationBorrower");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Guarantor has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.gemail,"g"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationGuarantor");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that Relationship Manager has received an email notification after completion of 3 signer process.");
		if(packageVerification.verifyPkgComplete(PackageIndexpage.remail,"r"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "packageCompleteEmailNotificationRelationshipManager");
			driver.close();
			Common.SwitchtoTab(driver, 0);
			numberOfFailure++;
		}*/
		
		
	}
	
	@Test
	public void  TC_DB_34()
	{
		
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_34: Verify Trash Functionality for DRAFT Icon.");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save as Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Trash' button.");
		packageVerification=packageIndexpage.clickTrashPkg();
		packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		packageVerification=packageIndexpage.acceptArchiveOK();
		
		Common.logstep("Step "+(step++)+" Choose Inactive from Filter");
		packageVerification=packageIndexpage.ChooseInactive();
		//packageIndexpage.ChooseInactive();
		
		Common.logMandetoryAssert("Verify that package is Trashed successfully and found in package table with TRASHED Status.");
		packageIndexpage.clickRefpackageontable();
		
		if(packageVerification.verifyTrashPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
			
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "TrashFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
        /*Common.waitForElement(driver, ".//*[text()='Error']");
		
		Common.logMandetoryAssert("Verify validation error message that signature document is not available to sign.");
		if(packageVerification.verifyDocumentValidation())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}*/
		
	}
	
	@Test
	public void TC_DB_35()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_35 : Verify Trash Functionality for Ready Status Package. ");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		/*Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();*/
		
		Common.logstep("Step " +(step++) + " Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logstep("Step " +(step++) + " Verify Ready Icon");
		
		packageIndexpage.clickRefpackageontable();
		if(packageVerification.verifyReadyIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReadyIconFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Trash' button.");
		
		packageIndexpage.clickRefpackageontable();
		packageVerification=packageIndexpage.clickTrashPkg();
		packageVerification=packageIndexpage.acceptTrashConfirmation();
		packageVerification=packageIndexpage.acceptArchiveOK();
		
		Common.logstep("Step "+(step++)+" Choose Inactive from Filter");
		packageVerification=packageIndexpage.ChooseInactive();
		//packageIndexpage.ChooseInactive();
		
		Common.logMandetoryAssert("Verify that package is Trashed successfully and found in package table with TRASHED Status.");
		packageIndexpage.clickRefpackageontable();
		
		if(packageVerification.verifyTrashPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
			
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "TrashFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
		
		/*Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	*/
	
		/*Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}*/
		
	}
	
	@Test
	public void TC_DB_36()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_01: Verify Show My Packages Functionality .");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++)+ " Click on Show My Packages checkbox");
		packageVerification=packageIndexpage.clickShowMyPackagesn();
		Common.pause(10);
		
		Common.logstep("Step "+ (step++)+ " Verifying the Sender Details ");
		
		/*if(packageVerification.verifyShowMyPackage())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ShowMyPackageFailed");
			numberOfFailure++;
		}*/
		
		
		Common.logMandetoryAssert("Verify that all the packages created by current users are displayed.");
		
		if(packageVerification.verifyShowMyPackagedynamic())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ShowMyPackageFailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
	}	
	
	/*@Test
	public void TC_DB_16()
	{
		step=1;
		numberOfFailure=0;
		
		Common.logcase(" TC_DB_16 : Verify user can review package details for  SENT status");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with SENT Status.");
		if(packageVerification.verifySentIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Review' button.");
		packageVerification=packageIndexpage.clickreviewPkg();
	//	packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		Common.pause(3);
		Common.SwitchtoTab(driver, 1);
		Common.pause(5);	
		
		Common.logMandetoryAssert(" Verify the review package created.");
		
		if(packageVerification.verifyreviewpackage())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReviewPackagefailed");
			numberOfFailure++;
		}
	}	
	
	@Test
	public void  TC_DB_17()
	{
		
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_17: Verify user can review package details for  DRAFT status ");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save as Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Review' button.");
		packageVerification=packageIndexpage.clickreviewPkg();
	//	packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		Common.pause(3);
		Common.SwitchtoTab(driver, 1);
		Common.pause(5);	
		
		Common.logMandetoryAssert(" Verify the review package created.");
		if(packageVerification.verifyreviewpackage())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReviewPackagefailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
        		
	}
	@Test
	public void TC_DB_18()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_18 : Verify user can review package details for  DECLINED status ");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Decline' button");
		packageVerification=packageIndexpage.clickDecline();
		
		Common.logstep("Step "+ (step++) +" Choose reason from Decline radio box");
		packageVerification=packageIndexpage.ChooseDeclineradio();
		
		Common.logstep("Step " +(step++) + "Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logMandetoryAssert("Verify that package is Declined successfully and found in package table with DECLINED Status.");
		
		if(packageVerification.verifyDeclinedIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "DeclineFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Review' button.");
		packageVerification=packageIndexpage.clickreviewPkg();
	//	packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		Common.pause(3);
		Common.SwitchtoTab(driver, 3);
		Common.pause(5);	
		
		Common.logMandetoryAssert(" Verify the review package created.");
		if(packageVerification.verifyreviewpackage())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReviewPackagefailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
		
	}
	
	@Test
	public void TC_DB_19()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_19: Verify user can review package details for  OPTED-OUT status ");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ArchivedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Opt Out' button");
		packageVerification=packageIndexpage.clickOptout();
		
		Common.logstep("Step "+ (step++) +" Choose reason from Opt out radio box");
		packageVerification=packageIndexpage.ChooseOptradio();
		
		Common.logstep("Step " +(step++) + " Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logMandetoryAssert("Verify that package is Opted-Out successfully and found in package table with OPTED-OUT Status.");
		
		if(packageVerification.verifyOptOutIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "OptOutFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Review' button.");
		packageVerification=packageIndexpage.clickreviewPkg();
	//	packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		Common.pause(3);
		Common.SwitchtoTab(driver, 1);
		Common.pause(5);	
		
		Common.logMandetoryAssert(" Verify the review package created.");
		if(packageVerification.verifyreviewpackage())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReviewPackagefailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
				
	}
	
	@Test
	public void TC_DB_20()
	{
		step=1; numberOfFailure=0;
		Common.logcase("TC_DB_20: Verify user can review package details  for 'COMPLETED' status ");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter Borrower details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Choose 'Cloude Services Agreement' checkbox to upload contract document.");
		packageVerification=packageIndexpage.chooseServiceCheckBox();
		
		Common.logstep("Step "+ (step++) +" Upload any agreenment file template");
		packageIndexpage.uploadFile();
		
		Common.logstep("Step "+ (step++) +" Click on 'Send Package' button.");
		packageVerification=packageIndexpage.clickSendPkg();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table.");
		if(packageVerification.verifyPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(PackageIndexpage.email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}
		
		Common.logstep("Step " +(step++) + "Switchback to Dashboard");
		packageVerification=packageIndexpage.SwitchtoDashboard();
		
		Common.logMandetoryAssert("Verify that package is Completed successfully and found in package table with COMPLETED Status.");
		
		if(packageVerification.verifyCompletedIcon())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCompletedFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Review' button.");
		packageVerification=packageIndexpage.clickreviewPkg();
	//	packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		Common.pause(3);
		Common.SwitchtoTab(driver, 1);
		Common.pause(5);	
		
		Common.logMandetoryAssert(" Verify the review package created.");
		if(packageVerification.verifyreviewpackage())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReviewPackagefailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert("Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg("b"))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}
		
	}
	
	@Test
	public void  TC_DB_21()
	{
		
		step=1;
		numberOfFailure=0;
		
		Common.logcase("TC_DB_21: Verify user can review for the package which is in 'DRAFT' status without having Signature documents..");
	
		Common.logstep("Step "+ (step++) +" Enter Username.");
		packageIndexpage.enterUsername(TestData.getUserName());
		
		Common.logstep("Step "+ (step++) +" Enter Password.");
		packageIndexpage.enterPassword(TestData.getPassword());
		
		Common.logstep("Step "+ (step++) +" Click on Login button.");
		packageVerification=packageIndexpage.clickLogin();
		Common.pause(5);
		Common.logMandetoryAssert("Verify user has logged in successfuly.");
		
		if(packageVerification.verifyLogin())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "LoginFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Select workflow from dropdown.");
		packageIndexpage.selectWorkflow("IBM Digital Contracts Demo v. 1");
	
		Common.logstep("Step "+ (step++) +" Click on 'Create' Button.");
		packageVerification=packageIndexpage.clickCreate();

		Common.logstep("Step "+ (step++) +" Enter 'Borrower 1:' details to create package.");
		packageIndexpage.enterReference();
		packageIndexpage.enterFname();
		packageIndexpage.enterLname();
		packageIndexpage.enterEmail();
		packageIndexpage.enterMobile();
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments.");
		packageIndexpage.chooseAttchments();
		
		Common.logstep("Step "+ (step++) +" Enter 'Guarantor 1:' details to create package.");
		packageIndexpage.chooseGuarantor();
		packageIndexpage.enterGFname();
		packageIndexpage.enterGLname();
		packageIndexpage.enterGEmail();
		packageIndexpage.enterGMobile();
		
		Common.logstep("Step "+ (step++) +" Enter 'Relationship Manager:' details to create package.");
		packageIndexpage.chooseRManager();
		packageIndexpage.enterRFname(2);
		packageIndexpage.enterRLname(2);
		packageIndexpage.enterREmail(2);
		packageIndexpage.enterRMobile(2);
		
		Common.logstep("Step "+ (step++) +" Choose any random WAFR Attachments for Relationship Manager.");
		packageIndexpage.warfAttachmentRM();
			
		Common.logstep("Step "+ (step++) +" Click on Create Button to Create Package.");
		packageVerification=packageIndexpage.clickCreatepkg();
		
		Common.logstep("Step "+ (step++) +" Click on 'Save as Draft' button.");
		packageVerification=packageIndexpage.clickDraft();
		
		Common.logMandetoryAssert("Verify that package has successfully created and found in package table with Draft Status.");
		if(packageVerification.verifyDraftPkg())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "PackageCreatedFailed");
			numberOfFailure++;
		}	
		
		Common.logstep("Step "+ (step++) +" Click on Reference name to select package.");
		packageIndexpage.clickRefpackageontable();
		
		Common.logstep("Step "+ (step++) +" Click on 'Review' button.");
		packageVerification=packageIndexpage.clickreviewPkg();
	//	packageVerification=packageIndexpage.acceptTrashConfirmation();
		
		Common.pause(3);
		Common.SwitchtoTab(driver, 1);
		Common.pause(5);	
		
		Common.logMandetoryAssert(" Verify the review package created.");
		if(packageVerification.verifyreviewpackage())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ReviewPackagefailed");
			numberOfFailure++;
		}
		
		if(numberOfFailure > 0)
		{
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
		
	}*/
	
	
	
	private int signDocument(int step,String email,String signer) {
		// TODO Auto-generated method stub
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step
				++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage(signer))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert(" Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg(signer))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}
		
		driver.close();
		Common.SwitchtoTab(driver, 1);
		driver.close();
		Common.SwitchtoTab(driver, 0);
		
		return step;
	}
	
	private int signOnlyBorrowerDocument(int step,String email,String signer) {
		// TODO Auto-generated method stub
		
		Common.logstep("Step "+ (step++) +" Open Mailinator to check if user has received package created notification.");
		packageVerification=packageIndexpage.checkMailinator(1);
		
		Common.logstep("Step "+ (step
				++) +" Enter test email address.");
		packageIndexpage.entermailinatorEmail(email);
		
		Common.logstep("Step "+ (step++) +" Click on email notiction received from 'ACME Business Banking'.");
		packageVerification=packageIndexpage.checkEmailContent();
		
		Common.logstep("Step "+ (step++) +" Click on 'Go to My documents' button to navigate Dashboard.");
		packageIndexpage.clickGoToDocument();
		
		Common.SwitchtoTab(driver, 2);
		
		Common.logstep("Step "+ (step++) +" Navigate to other tab and accept Consent.");
		packageIndexpage.clickAccept();
	
		Common.logstep("Step "+ (step++) +" Click on 'Request SMS Authentication Code' button.");
		packageIndexpage.clickRequestSMS();
		
		Common.logstep("Step "+ (step++) +" Enter SMS Code.");
		packageIndexpage.enterSMSCode();
	
		Common.logstep("Step "+ (step++) +" Click on Authenticate button to verify SMS Code.");
		packageIndexpage.clickAuthnticate();
			
		Common.logMandetoryAssert("Verify that user navigates to 'Documents Portal'.");
		if(packageVerification.verifyDocPortalpage(signer))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "NavigateToPortalFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'View Document' of 'Product Disclosure Statement'.");
		packageIndexpage.clickViewDocument();
		
		Common.logstep("Step "+ (step++) +" Click on 'Back to loan documents portal' button of 'Product Disclosure Statement'.");
		packageIndexpage.clickBackToLoanPortal();
		
		Common.logMandetoryAssert("Verify that 'Not Viewed' state has been changed to 'Viewed'.");
		if(packageVerification.verifyDocumentState())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ViewedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Click on 'Sign Online' button.");
		packageIndexpage.clickSignOnline();
		
		Common.logstep("Step "+ (step++) +" Click on Down Arrow button move at the end of document.");
		packageIndexpage.clickArrow();
		
		Common.logstep("Step "+ (step++) +" Click on 'Click to Sign' check mark area.");
		packageIndexpage.clickToSign();
		
		Common.logstep("Step "+ (step++) +" Click on 'Ok' to accept confirmation");
		packageVerification=packageIndexpage.clickAcceptAlert();
		
		Common.waitForElementClickable(driver,".//a[@id='backButton']");
		
		Common.logstep("Step "+ (step++) +" Click on 'Return to Home' button to navigate back to Portal.");
		packageVerification=packageIndexpage.clickReturn();	
		
		Common.logMandetoryAssert("Verify that 'eSign documents' status changed to 'Signed'.");
		if(packageVerification.verifySignStatus())
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "SignedStateChangeFailed");
			numberOfFailure++;
		}
		
		Common.logstep("Step "+ (step++) +" Upload Print and Signed WFAR Documents.");
		packageIndexpage.uploadWFAR();
		
		Common.logstep("Step "+ (step++) +" Click on 'Submit loan documents' to submit signed loan document.");
		packageVerification=packageIndexpage.clickSubmit();	
	
		Common.logMandetoryAssert(" Verify Thank you message for borrower.");
		if(packageVerification.verifyThanksMsg(signer))
		{
			Common.AssertPassed();
			Assert.assertTrue(true);
		}
		else
		{
			Common.AssertFailed();
			Common.makeScreenshot(driver, "ThanksMessageAssert");
			numberOfFailure++;
		}
		
		/*driver.close();
		Common.SwitchtoTab(driver, 1);
		driver.close();
		Common.SwitchtoTab(driver, 0);*/
		
		return step;
	}	
	
}
