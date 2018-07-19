package myTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTests {
	static WebDriver driver;
	static String directory=System.getProperty("user.dir");
	
	@BeforeTest
	public void initiate()
	{
		System.setProperty("webdriver.chrome.driver", directory+"\\EXE\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);
		options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.irctc.co.in/nget/train-search");
	}
	
	@Test
	public void login() throws InterruptedException
	{
		driver.findElement(By.id("loginText")).click();
		driver.switchTo().window(driver.getWindowHandle());
		driver.findElement(By.id("userId")).sendKeys("******");Thread.sleep(1000);		
		driver.findElement(By.id("pwd")).sendKeys("********");
		WebElement captcha=driver.findElement(By.id("nlpAnswer"));
		captcha.click();
		System.out.println("Please enter Captcha");
		Thread.sleep(10000);		
		driver.findElement(By.xpath("//*[contains(text(), 'SIGN IN')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[contains(@placeholder,'From*')]")).sendKeys("PUNE JN - PUNE");
		driver.findElement(By.xpath("//*[contains(@placeholder,'To*')]")).sendKeys("NAGPUR - NGP");
		Thread.sleep(3000);
		for(int i=0;i<=9;i++){
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Journey Date(dd-mm-yyyy)*')])[1]")).sendKeys(Keys.BACK_SPACE);Thread.sleep(1000);}
		driver.findElement(By.xpath("(//input[contains(@placeholder,'Journey Date(dd-mm-yyyy)*')])[1]")).sendKeys("31-07-2018");Thread.sleep(1000);
//		for(int i=0;i<9;i++){
//		driver.findElement(By.xpath("(//*[contains(@placeholder,'Journey Date(dd-mm-yyyy)*')])[1]")).sendKeys(Keys.BACK_SPACE);}		Thread.sleep(3000);
//		driver.findElement(By.xpath("//*[@class='ui-datepicker-calendar ng-tns-c14-5 ng-star-inserted']/thead/tbody/tr[5]/td[2]/a")).click();
		driver.findElement(By.xpath("(//*[contains(text(),'Find Trains')]")).click();
	
	}
	
	@AfterTest
	public void close() throws InterruptedException
	{
		Thread.sleep(10000);
		//driver.close();
	}
	
}