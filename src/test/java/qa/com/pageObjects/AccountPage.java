package qa.com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.com.abstractcomponents.ReusableUtilities;

public class AccountPage extends ReusableUtilities{

	WebDriver driver;
	
	@FindBy (css=".list-group [href*='newsletter']")
	private WebElement newsletter;
	
	
	public AccountPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnNewsLetterTab()
	{
		click_custom("NewsLetter", newsletter);
	}

}
