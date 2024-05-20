package qa.com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.com.abstractcomponents.ReusableUtilities;

public class LoginPage extends ReusableUtilities{

	WebDriver driver;
	
	@FindBy(css=".text-end [href*='register']")
	WebElement register;
	
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnContinueButtonForAccountRegistrationFromLoginPage()
	{
		click_custom("Register Account_Login Page", register);
	}
	

}
