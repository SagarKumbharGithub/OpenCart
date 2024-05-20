package qa.com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.com.abstractcomponents.ReusableUtilities;

public class LandingPage extends ReusableUtilities{
	
	WebDriver driver;
	
	@FindBy (xpath="//span[text()='My Account']")
	private WebElement myAccount;
	
	@FindBy (xpath="//a[text()='Register']")
	private WebElement register;
	
	@FindBy (css=".dropdown-item[href*='login']")
	private WebElement login;
	
	
	public LandingPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Click on my account tab from the landing page
	 */
	public void clickOnMyAccountTab()
	{
		click_custom("My Account Tab", myAccount);
	}
	/**
	 *  Click on the register tab
	 */
	public void clickOnRegisterTab()
	{
		click_custom("Register Tab", register);
	}
	/**
	 *  Click on the Login tab
	 */
	public void clickOnLoginTab()
	{
		click_custom("Login Tab", login);
	}
	


}
