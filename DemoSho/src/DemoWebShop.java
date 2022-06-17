import java.util.concurrent.TimeUnit;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class DemoWebShop {
WebDriver driver;
Logger log=Logger.getLogger(DemoWebShop.class.getName());
@BeforeSuite
public void openBrowser() {
	System.setProperty("webdriver.chrome.driver","D:\\Office\\chromedriver_win32 (1)\\chromedriver.exe");
driver=new ChromeDriver();
Layout la=new SimpleLayout();
Appender ap= new ConsoleAppender(la);

log.info("Browser open succesfully");
}
@BeforeTest
public void enterUrl() {
	driver.get("http://demowebshop.tricentis.com/register");
	log.info("Url open succesfully");
}
@BeforeClass
public void maximizeScreen() {
	driver.manage().window().maximize();
	log.info("Maximize screen succesfully");
}
@BeforeMethod
public void pageloadTimeout() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	log.info("Pageload timeout succesfully");
}
//@Parameters({"fn","ln","email","password","conpass"})
@Test(priority = 1)
//public void registerData(String fn,String ln,String email,String password,String conpass) {
public void registerData() {	
driver.findElement(By.xpath("//*[@id=\"gender-male\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"FirstName\"]")).sendKeys("Jay");
	driver.findElement(By.xpath("//*[@id=\"LastName\"]")).sendKeys("Dake");
	driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys("jay@12gmail.com");
	driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys("dell@123");
	driver.findElement(By.xpath("//*[@id=\"ConfirmPassword\"]")).sendKeys("dell@123");
	driver.findElement(By.xpath("//*[@id=\"register-button\"]")).click();
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
	log.info("Register data succesfully");
}
@Test(priority = 2,dataProvider = "getData")
public void loginData(String email,String password) {
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
	driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);
	driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(password);
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).click();
	log.info("Login data succesfully");

}
@DataProvider
public Object[][] getData(){
	return new Object[][] {
		new Object[] {"jay@12gmail.com","dell@123"}
	};
}
@Test(priority = 3)
public void addTocard() {
	Actions ac1=new Actions(driver);
	ac1.moveToElement(driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[3]/a"))).perform();
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[3]/ul/li[2]/a")).click();
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div/div[2]/div[3]/div[2]/input")).click();
	driver.findElement(By.xpath("//*[@id=\"topcartlink\"]/a/span[1]")).click();
	log.info("Add To Card succesfully done");
	
}
@Test(priority = 4)
public void cheakOut() {
	driver.findElement(By.xpath("//*[@id=\"termsofservice\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
	log.info("Cheakout Succesfully done");
}
@Test(priority = 5)
public void bilingAddress() {
	WebElement w1=driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_CountryId\"]"));
	Select s1=new Select(w1);
	s1.selectByValue("41");
	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_City\"]")).sendKeys("Aurangabad");
	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_Address1\"]")).sendKeys("New Osmanpura");
	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_Address2\"]")).sendKeys("ABC Complex");
	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_ZipPostalCode\"]")).sendKeys("00000");
	driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_PhoneNumber\"]")).sendKeys("9876543321");
	driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/input")).click();
	log.info("Address succesfully added");
}
@Test(priority = 6)
public void shipingAddress() {
	driver.findElement(By.xpath("//*[@id=\"PickUpInStore\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"shipping-buttons-container\"]/input")).click();
	log.info("Shiping Address succesfully Addreed");
}
@Test(priority = 7)
public void paymentMethod() {
	driver.findElement(By.xpath("//*[@id=\"paymentmethod_1\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/input")).click();
	log.info("paymenet method succesfully added");
}
@Test(priority = 8)
public void paymentInformation() {
	driver.findElement(By.xpath("//*[@id=\"payment-info-buttons-container\"]/input")).click();
	log.info("Payment information succesfully added");
}
@Test(priority = 9)
public void confirmOrder() {
	driver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/input")).click();
	log.info("Confirm order succesfully added");
}
@Test(priority = 10)
public void orderDetails() {
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/ul/li[2]/a")).click();
	driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[1]/a[2]")).click();
log.info("Order bill dowanload succesfully");
}
}
