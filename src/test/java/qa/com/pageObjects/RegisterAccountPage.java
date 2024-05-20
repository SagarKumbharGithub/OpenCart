package qa.com.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.com.abstractcomponents.ReusableUtilities;
import qa.com.reports.ExtentListener;
import qa.com.reports.ExtentLogger;

public class RegisterAccountPage extends ReusableUtilities {

	WebDriver driver;
	

	@FindBy (css="h1")
	private WebElement registerAccountHeading;
	
	@FindBy (name="firstname")
	private WebElement fname;
	
	@FindBy (name="lastname")
	private WebElement lname;
	
	@FindBy (css="[for='input-lastname']")
	private WebElement lNameLabel;
	
	
	@FindBy (name="email")
	private WebElement email;
	
	@FindBy (name="password")
	private WebElement password;
	
	@FindBy (id="input-newsletter")
	private WebElement subscribe;
	
	@FindBy (name="agree")
	private WebElement privacyPolicy;
	
	@FindBy (css=".btn-primary")
	private WebElement continueButton;
	
	@FindBy (xpath="//h1[text()='Your Account Has Been Created!']")
	private WebElement successMsg;
	
	@FindBy (css=".list-group-item[href*='logout']")
	private WebElement logout;
	
	@FindBy (css=".alert-danger")
	private WebElement privacyPolicyError;
	
	@FindBy (css="#error-firstname")
	private WebElement firstNameError;
	
	@FindBy (css="#error-lastname")
	private WebElement lastNameError;

	@FindBy (css="#error-email")
	private WebElement emailError;
	
	@FindBy (css="#error-password")
	private WebElement passwordError;
	
	@FindBy (css=".alert-danger")
	private WebElement emailAlreadyRegisterError;
	
	public RegisterAccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public boolean verfiyHeadingTextDisplyedInRegisterAccontPage(String expectedMessage)
	{
		return verifyText(registerAccountHeading, "Register Account Page Heading :", expectedMessage);
	}
	public void enterFirstName(String firstName)
	{
		sendkey_custom("First Name", fname, firstName);
	}
	
	public void enterLastName(String lastName)
	{
		sendkey_custom("Last Name", lname, lastName);
	}
	public void enterEmailId(String emailID)
	{
		sendkey_custom("Email ID", email, emailID);
	}
	public void enterPassword(String pass)
	{
		sendkey_custom("Password", password, pass);
	}
	
	public void clickOnSubscribeButton()
	{
		click_custom("Subscibe Button", subscribe);
	}
	public void acceptThePrivacyPolicy() 
	{
		scrollIntoViewElement(privacyPolicy);
		click_custom("Privacy Policy Button", privacyPolicy);
	}
	public void clickOnContinuebutton()
	{
		scrollIntoViewElement(continueButton);
		click_custom("Continue Button", continueButton);
	}
	public boolean verifyAccountCreatedSuccessMessage(String expectedMessage)
	{
		return verifyText(successMsg, "Account Created Success Message :", expectedMessage);
	}
	public boolean verifyUserLandOnAccountPage()
	{
		waitForElementToBeVisible(logout);
		return logout.isDisplayed();
	}

	public void registerAccountByUsingKeyBoardKeys_Tab_Spacebar_Enter(String firstName,String lastName, String emailID, String pass)
	{
		fname.sendKeys(firstName);
		fname.sendKeys(Keys.TAB);
		lname.sendKeys(lastName);
		lname.sendKeys(Keys.TAB);
		email.sendKeys(emailID);
		password.sendKeys(pass);
		password.sendKeys(Keys.TAB);
		subscribe.sendKeys(Keys.SPACE);
		subscribe.sendKeys(Keys.TAB);
		privacyPolicy.sendKeys(Keys.SPACE);
		privacyPolicy.sendKeys(Keys.TAB);
		continueButton.sendKeys(Keys.ENTER);
		
	}
	public void filledMandatoryField(String firstName, String lastName, String email, String password)
	{

		enterFirstName(firstName);
		enterLastName(lastName);
		enterEmailId(email);
		enterPassword(password);
		acceptThePrivacyPolicy();
		
	}
	public boolean verifyText(WebElement element, String textName,String expectedWarning)
	{
		boolean warningFlag=false;
		
		String warningText=getText_custom(element);
		if(warningText.equalsIgnoreCase(expectedWarning))
		{
			warningFlag=true;
			ExtentLogger.pass(textName+warningText );			
		}
		else
		{
			warningFlag=false;
			ExtentLogger.fail("Incorrect Text Message :"+textName+warningText );	
		}
		return warningFlag;
				
	}
	public boolean verifyFirstNameWarningMessage(String expectedWarning)
	{
		scrollIntoViewElement(registerAccountHeading);
		ExtentListener.saveScreenShot("Warning Message Screenshot Captured");
		return verifyText(firstNameError, "First Name Warning Message : ",expectedWarning);
		
	}	
	public boolean verifyLastNameWarningMessage(String expectedWarning)
	{
		
		return verifyText(lastNameError, "Last Name Warning Message : ",expectedWarning);
		
	}
	public boolean verifyEmailWarningMessage(String expectedWarning)
	{
		
		return verifyText(emailError, "Email Warning Message : ",expectedWarning);
		
	}
	public boolean verifyPasswordWarningMessage(String expectedWarning)
	{
		
		return verifyText(passwordError, "Password Warning Message : ",expectedWarning);
		
	}
	public boolean verifyPrivacyPolicyWarningMessage(String expectedWarning)
	{
		
		return verifyText(privacyPolicyError, "Privacy Policy Warning Message : ",expectedWarning);
		
	}
	public boolean verifyEmailAlreayWarningMessage(String expectedWarning)
	{
		
		return verifyText(emailAlreadyRegisterError, "Email Already Registered Warning Message : ",expectedWarning);
		
	}
	
	public boolean verifyPlacehoderValueforFirstNameField(String expectedValue)
	{
		return verifyPlacehoderValue("First Name", fname, expectedValue);
	}
	public boolean verifyPlacehoderValueforLastNameField(String expectedValue)
	{
		return verifyPlacehoderValue("Last Name", lname, expectedValue);
	}
	public boolean verifyPlacehoderValueforEmailField(String expectedValue)
	{
		return verifyPlacehoderValue("Email", email, expectedValue);
	}
	public boolean verifyPlacehoderValueforPasswordField(String expectedValue)
	{
		return verifyPlacehoderValue("Password", password, expectedValue);
	}
	public String verifyMandatoryMarkProvidedForLastNameField()
	{
		return lNameLabel.getText();
	}

}
