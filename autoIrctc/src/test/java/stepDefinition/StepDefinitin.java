package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitin {
	static WebDriver driver;
	static String directory=System.getProperty("user.dir");
	WebDriverWait wait;
	
	@Given("^Url is given$")
	public void url_is_given() throws Throwable {
		System.setProperty("webdriver.chrome.driver", directory+"\\EXE\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@When("^Invoke browser and open url$")
	public void invoke_browser_and_open_url() throws Throwable {
		driver.get("https://www.irctc.co.in/nget/train-search");
	}

	@When("^Enter \"([^\"]*)\" and \"([^\"]*)\"$")
	public void enter_username_and_password(String username, String password) throws Throwable {
	wait=new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("loginText")))).click();
		//driver.findElement(By.id("loginText")).click();
		driver.switchTo().window(driver.getWindowHandle());
		WebElement userid=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("userId"))));
		userid.sendKeys(username);
		WebElement passwd=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("pwd"))));
		passwd.sendKeys(password);
		WebElement captcha=driver.findElement(By.id("nlpAnswer"));
		captcha.click();
		System.out.println("Please enter Captcha");
		Thread.sleep(10000);		
	}

	@Then("^click on login button$")
	public void click_on_login_button() throws Throwable {
		driver.findElement(By.xpath("//*[contains(text(), 'SIGN IN')]")).click();
		//driver.close();
	}
	
	@Given("^To station and From station is known$")
	public void to_station_and_from_station_is_known() throws Throwable {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(@placeholder,'From*')]")).sendKeys("PUNE JN - PUNE");
		driver.findElement(By.xpath("//*[contains(@placeholder,'To*')]")).sendKeys("NAGPUR - NGP");
		Thread.sleep(1000);
	}

	@When("^Enter date$")
	public void enter_date() throws Throwable {
		for(int i=0;i<=9;i++){
			WebElement date=driver.findElement(By.xpath("(//input[contains(@placeholder,'Journey Date(dd-mm-yyyy)*')])[1]"));
					date.sendKeys(Keys.BACK_SPACE);
			}
		WebElement datevalue=driver.findElement(By.xpath("(//input[contains(@placeholder,'Journey Date(dd-mm-yyyy)*')])[1]"));
		datevalue.sendKeys("31-07-2018");
	}

	@Then("^Click on Find Trains$")
	public void click_on_Find_Trains() throws Throwable {
		WebElement findTrain=driver.findElement(By.xpath("//*[contains(text(), 'Find trains')]"));
				findTrain.click();
	}
}
