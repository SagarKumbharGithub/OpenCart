package qa.com.testSuites;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import qa.com.pageObjects.AccountPage;
import qa.com.pageObjects.LandingPage;
import qa.com.pageObjects.LoginPage;
import qa.com.pageObjects.NewsLetterPage;
import qa.com.pageObjects.RegisterAccountPage;
import qa.com.reports.ExtentListener;
import qa.com.reports.ExtentReport;
import qa.com.reusableComponents.ReadExcel;
import qa.com.testComponents.BaseClass;

@Listeners(qa.com.reports.ExtentListener.class)

public class RegisterAccountSuite extends BaseClass {

	String featureName = "Register";
	SoftAssert softAssert = new SoftAssert();
	RegisterAccountPage registerAccount;
	LandingPage landingPage;
	AccountPage accountPage;
	NewsLetterPage newsletterPage;
	LoginPage loginPage;

	@Test(groups = { "Regression",
			"Register" }, priority = 1, enabled = true, description = "TC_RF_001_Validate Registering an Account by providing only the Mandatory fields")
	public void TC_RF_001_ValidateRegisteringAnAccountByProvidingOnlyMandatoryFields() throws IOException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_001");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String randomString = RandomStringUtils.randomAlphabetic(2);
		String emailId = randomString + input.get("emailId");
		String password = input.get("password");
		String expectedMessage = input.get("successMessage");
		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.filledMandatoryField(firstName, lastName, emailId, password);
		registerAccount.clickOnContinuebutton();
		boolean accountCreatedStatus = registerAccount.verifyAccountCreatedSuccessMessage(expectedMessage);
		// Verify Account created status
		softAssert.assertTrue(accountCreatedStatus);
		// Verify logout option displayed on the screen
		Assert.assertTrue(registerAccount.verifyUserLandOnAccountPage());

	}

	@Test(groups = { "Regression",
			"Register" }, priority = 2, enabled = true, description = "TC_RF_002_Validate Registering an Account by providing all the fields")
	public void TC_RF_002_ValidateRegisteringAnAccountByProvidingAllfieldsDetails() throws IOException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_002");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String randomString = RandomStringUtils.randomAlphabetic(2);
		String emailId = randomString + input.get("emailId");
		String password = input.get("password");
		String expectedMessage = input.get("successMessage");
		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.filledMandatoryField(firstName, lastName, emailId, password);
		registerAccount.clickOnSubscribeButton();
		registerAccount.clickOnContinuebutton();
		boolean accountCreatedStatus = registerAccount.verifyAccountCreatedSuccessMessage(expectedMessage);
		// Verify Account created status
		softAssert.assertTrue(accountCreatedStatus);
		// Verify logout option displayed on the screen
		Assert.assertTrue(registerAccount.verifyUserLandOnAccountPage());
	}

	@Test(groups = { "Regression",
			"Register" }, priority = 3, enabled = true, description = "TC_RF_003_Validate proper notification messages are displayed for the mandatory fields")
	public void TC_RF_003_ValidateProperNotificationMessagesAreDisplayedForTheMandatoryFields() throws IOException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_003");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String expectedFirstNameError = input.get("firstNameErrorMessage");
		String expectedLastNameError = input.get("lastNameErrorMessage");
		String expectedEmailError = input.get("emailErrorMessage");
		String expectedPasswordError = input.get("passwordErrorMessage");
		String expectedPrivacyPolicyError = input.get("privacyPolicyErrorMessage");

		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.clickOnContinuebutton();
		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean firstNameWarningFlag = registerAccount.verifyFirstNameWarningMessage(expectedFirstNameError);
		softAssert.assertTrue(firstNameWarningFlag);

		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean lastNameWarningFlag = registerAccount.verifyLastNameWarningMessage(expectedLastNameError);
		softAssert.assertTrue(lastNameWarningFlag);

		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean emailWarningFlag = registerAccount.verifyEmailWarningMessage(expectedEmailError);
		softAssert.assertTrue(emailWarningFlag);

		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean passwordWarningFlag = registerAccount.verifyPasswordWarningMessage(expectedPasswordError);
		softAssert.assertTrue(passwordWarningFlag);

		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean privacyWarningFlag = registerAccount.verifyPrivacyPolicyWarningMessage(expectedPrivacyPolicyError);
		softAssert.assertTrue(privacyWarningFlag);

		softAssert.assertAll();

	}

	@Test(groups = { "Regression",
			"Register" }, priority = 4, enabled = true, description = "TC_RF_004_Validate Registering an Account when 'Yes' option is selected for Newsletter field")
	public void TC_RF_004_ValidateRegisteringAnAccountWhenYesOptionIsSelectedForNewsletterfield()
			throws IOException, InterruptedException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_004");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String randomString = RandomStringUtils.randomAlphabetic(2);
		String emailId = randomString + input.get("emailId");
		String password = input.get("password");
		String expectedMessage = input.get("successMessage");
		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.filledMandatoryField(firstName, lastName, emailId, password);
		registerAccount.clickOnSubscribeButton();
		registerAccount.clickOnContinuebutton();
		boolean accountCreatedStatus = registerAccount.verifyAccountCreatedSuccessMessage(expectedMessage);
		// Verify Account created status
		softAssert.assertTrue(accountCreatedStatus);
		// Verify logout option displayed on the screen
		softAssert.assertTrue(registerAccount.verifyUserLandOnAccountPage());
		ExtentListener.saveScreenShot("Registration Success Screenshot captured");
		// Verify newsletter checkbox option selected by default
		accountPage = new AccountPage(getDriver());
		accountPage.clickOnNewsLetterTab();
		newsletterPage = new NewsLetterPage(getDriver());
		boolean newsletterCheckboxSelected = newsletterPage.verifyNewsLetterCheckboxSelectedBydefault();
		softAssert.assertTrue(newsletterCheckboxSelected);
		softAssert.assertAll();

	}

	@Test(groups= {"Regression","Register"},priority=5,enabled=true, description = "TC_RF_005_Validate Registering an Account when 'No' option is selected for Newsletter field")
	public void TC_RF_005_ValidateRegisteringAnAccountWhenNoOptionIsSelectedForNewsletterfield()
			throws IOException, InterruptedException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_005");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String randomString = RandomStringUtils.randomAlphabetic(2);
		String emailId = randomString + input.get("emailId");
		String password = input.get("password");
		String expectedMessage = input.get("successMessage");
		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.filledMandatoryField(firstName, lastName, emailId, password);
		registerAccount.clickOnContinuebutton();
		boolean accountCreatedStatus = registerAccount.verifyAccountCreatedSuccessMessage(expectedMessage);
		// Verify Account created status
		softAssert.assertTrue(accountCreatedStatus);
		// Verify logout option displayed on the screen
		softAssert.assertTrue(registerAccount.verifyUserLandOnAccountPage());
		ExtentListener.saveScreenShot("Registration Success Screenshot captured");
		// Verify newsletter checkbox option selected by default
		accountPage = new AccountPage(getDriver());
		accountPage.clickOnNewsLetterTab();
		newsletterPage = new NewsLetterPage(getDriver());
		boolean newsletterCheckboxSelected = newsletterPage.verifyNewsLetterCheckboxSelectedBydefault();
		softAssert.assertFalse(newsletterCheckboxSelected);
		softAssert.assertAll();
	}

	@Test(groups = { "Regression",
			"Register" }, priority = 6, enabled = true, description = "TC_RF_006_Validate different ways of navigating to 'Register Account' page")
	public void TC_RF_006_ValidateDifferentWaysOfNavigatingToRegisterAccountPage()
			throws IOException, InterruptedException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_006");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");

		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnLoginTab();
		loginPage = new LoginPage(getDriver());
		loginPage.clickOnContinueButtonForAccountRegistrationFromLoginPage();
		registerAccount = new RegisterAccountPage(getDriver());
		boolean headingTextStatus = registerAccount.verfiyHeadingTextDisplyedInRegisterAccontPage("Register Account");
		Assert.assertTrue(headingTextStatus);

	}

	@Test(groups = { "Regression",
			"Register" }, priority = 7, enabled = true, description = "TC_RF_007_Validate Registering an Account by providing the existing account details (i.e. existing email address)")
	public void TC_RF_007() throws IOException, InterruptedException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_007");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String emailId = input.get("emailId");
		String password = input.get("password");
		String expectedEmailWarningMessage = input.get("expectedEmailWarningMessage");
		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);

		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.filledMandatoryField(firstName, lastName, emailId, password);
		registerAccount.clickOnSubscribeButton();
		registerAccount.clickOnContinuebutton();
		boolean warningMessageStatus = registerAccount
				.verifyEmailWarningMessage(expectedEmailWarningMessage);
		// Verify email warning message If match with expected warning message then
		// return true else true
		Assert.assertTrue(warningMessageStatus);

	}

	@Test(groups = { "Regression", "Register" }, priority = 8, enabled = true, description = "TC_RF_008_Validate Registering an Account by providing an invalid email address into the E-Mail field")
	public void TC_RF_008_ValidateRegisteringAnAccountByProvidingAnInvalidEmailAddresIntoTheEMailfield() throws IOException, InterruptedException 
	{
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_008");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String emailId = input.get("emailId");
		String password = input.get("password");
		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);

		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.filledMandatoryField(firstName, lastName, emailId, password);
		registerAccount.clickOnSubscribeButton();
		registerAccount.clickOnContinuebutton();

		boolean warningMessageStatus = registerAccount
				.verifyEmailWarningMessage("E-Mail Address does not appear to be valid!");
		// Verify email warning message If match with expected warning message then
		// return true else true
		Assert.assertTrue(warningMessageStatus);

	}

	@Test(groups = { "Regression", "Register" }, priority = 9, enabled = true, description = "TC_RF_009_Validate Registering an Account by using the Keyboard keys")
	public void TC_RF_009_ValidateRegisteringAnAccountByUsingKeyboardKeys() throws IOException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_009");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String randomString = RandomStringUtils.randomAlphabetic(2);
		String emailId = randomString + input.get("emailId");
		String password = input.get("password");
		String expectedMessage = input.get("successMessage");
		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.registerAccountByUsingKeyBoardKeys_Tab_Spacebar_Enter(firstName, lastName, emailId, password);

		boolean accountCreatedStatus = registerAccount.verifyAccountCreatedSuccessMessage(expectedMessage);
		// Verify Account created Status true-> Created , true ->Not created
		Assert.assertTrue(accountCreatedStatus);
	}

	@Test(groups = { "Regression", "Register" }, priority = 10, enabled = true, description = "TC_RF_010_Validate all the fields in the Register Account page have the proper placeholders")
	public void TC_RF_010_ValidateRegisterAccountFieldsPlacehoders() throws IOException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_010");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");

		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		boolean firstNamePlacehoderValue = registerAccount.verifyPlacehoderValueforFirstNameField("First Name");
		// Verify first name placehoder value
		softAssert.assertTrue(firstNamePlacehoderValue);
		boolean lastNamePlacehoderValue = registerAccount.verifyPlacehoderValueforLastNameField("Last Name");
		// Verify last name placehoder value
		softAssert.assertTrue(lastNamePlacehoderValue);
		boolean emailPlacehoderValue = registerAccount.verifyPlacehoderValueforEmailField("E-Mail");
		// Verify Email placehoder value
		softAssert.assertTrue(emailPlacehoderValue);
		boolean passwordPlacehoderValue = registerAccount.verifyPlacehoderValueforPasswordField("Password");
		// Verify Password placehoder value
		softAssert.assertTrue(passwordPlacehoderValue);
		softAssert.assertAll();

	}

	@Test(groups = { "Regression", "Register" }, priority = 11, enabled = true, description = "TC_RF_011_Validate whether the Mandatory fields in the Register Account page are accepting only spaces")
	public void TC_RF_011_ValidateMandatoryFieldAcceptiongSpacesOnly() throws IOException {
		Map<String, String> input = ReadExcel.getExcelData("Register", "TC_RF_011");
		String testCaseTitle = input.get("TestCaseID") + "_" + input.get("testCaseTitle");
		String authorName = input.get("authorName");
		String browserName = input.get("browserName");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String emailId = input.get("emailId");
		String password = input.get("password");
		String expectedFirstNameError = input.get("firstNameErrorMessage");
		String expectedLastNameError = input.get("lastNameErrorMessage");
		String expectedEmailError = input.get("emailErrorMessage");
		String expectedPasswordError = input.get("passwordErrorMessage");

		ExtentReport.createTest(testCaseTitle, featureName, authorName, browserName);
		initiate_driver(browserName);
		landingPage = new LandingPage(getDriver());
		landingPage.clickOnMyAccountTab();
		landingPage.clickOnRegisterTab();
		registerAccount = new RegisterAccountPage(getDriver());
		registerAccount.filledMandatoryField(firstName, lastName, emailId, password);

		registerAccount.clickOnContinuebutton();
		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean firstNameWarningFlag = registerAccount.verifyFirstNameWarningMessage(expectedFirstNameError);
		softAssert.assertTrue(firstNameWarningFlag);

		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean lastNameWarningFlag = registerAccount.verifyLastNameWarningMessage(expectedLastNameError);
		softAssert.assertTrue(lastNameWarningFlag);

		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean emailWarningFlag = registerAccount.verifyEmailWarningMessage(expectedEmailError);
		softAssert.assertTrue(emailWarningFlag);

		// Validate flag >> true once match with warning text with expected warning
		// message
		boolean passwordWarningFlag = registerAccount.verifyPasswordWarningMessage(expectedPasswordError);
		softAssert.assertTrue(passwordWarningFlag);

		softAssert.assertAll();

	}

}
