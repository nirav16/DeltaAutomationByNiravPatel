package com;

import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.prism.paint.Color;
import com.sun.tools.javac.util.Context.Key;

import ch.qos.logback.core.net.SyslogOutputStream;
import utils.ExcelUtils;
import utils.ColorToHexa;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.AfterSuite;
@Listeners(com.ListenerDeltaUI.class)

public class DeltaLoginUI {
	
  WebDriver driver;
  Properties obj ;
  
  
  @BeforeSuite (groups={"sanity","regression"})
  public void openBrowser() {
	  File files = new File("geckodriver.exe");
	  String propertyFilePath = files.getAbsolutePath();
	  String filepath = propertyFilePath.replaceAll("geckodriver.exe", "");
	  String finalPropertypath = (filepath + "\\exe\\geckodriver.exe") ;
	  System.setProperty("webdriver.gecko.driver", finalPropertypath);
		driver = new FirefoxDriver();
		driver.get("http://adjiva.com/qa-test/");
  }
  
  @BeforeMethod (groups={"sanity","regression"})
  public void load_Object() throws IOException  {
	  File files = new File("application.properties");
	  String propertyFilePath = files.getAbsolutePath();
	  String filepath = propertyFilePath.replaceAll("application.properties", "");
	  String finalPropertypath = (filepath + "\\src\\test\\java\\com\\objectRepository\\application.properties") ;
	  FileReader reader = new FileReader(finalPropertypath);
	 // FileInputStream objfile = new FileInputStream(System.getProperty("C:\\Users\\niravni\\eclipse-workspace\\DeltaAutomationUI\\target\\test-classes\\com\\objectRepository\\application.properties"));	
	  obj =  new Properties();
	  obj.load(reader);  
  }
  
  @Test (groups={"regression"},dataProvider="data_invalid")
public void enter_invalid_firstname(String s) {
	  System.out.println("the input value from excel is " +s);
	  WebElement firstname = driver.findElement(By.xpath(obj.getProperty("first_name")));
	  firstname.clear();
	  firstname.sendKeys(s);
	  WebElement message = driver.findElement(By.xpath("//small[@class='help-block'][@data-bv-validator-for='first_name']"));
	  Assert.assertEquals(message.getText(), "This value is not valid");
  }

       
  @Test (groups={"regression"})
  public void blank_first_name() {
	  WebElement firstname = driver.findElement(By.xpath(obj.getProperty("first_name")));
	  firstname.clear();
	  firstname.sendKeys("a");
	  firstname.sendKeys(Keys.BACK_SPACE);
	  WebElement message = driver.findElement(By.xpath("//small[@class='help-block'][@data-bv-validator-for='first_name'][@data-bv-validator='notEmpty']"));
	  Assert.assertEquals(message.getText(), "Please enter your First Name");
  }
  
  @Test(groups={"regression"}, dataProvider="data_invalid")
  public void enter_invalid_lastname(String s) {
	  System.out.println("the input value from excel is " +s);
	  WebElement lastname = driver.findElement(By.xpath(obj.getProperty("last_name")));
	  lastname.clear();
	  lastname.sendKeys(s);
	  WebElement message = driver.findElement(By.xpath("//small[@class='help-block'][@data-bv-validator-for='last_name']"));
	  Assert.assertEquals(message.getText(), "This value is not valid");
	  
  }
  @Test (groups={"regression"})
  public void blank_lastname() {
	  WebElement lastname = driver.findElement(By.xpath(obj.getProperty("last_name")));
	  lastname.clear();
	  lastname.sendKeys("a");
	  lastname.sendKeys(Keys.BACK_SPACE);
	  WebElement message = driver.findElement(By.xpath("//small[@class='help-block'][@data-bv-validator-for='last_name'][@data-bv-validator='notEmpty']"));
	  Assert.assertEquals(message.getText(), "Please enter your Last Name");
  } 
  @Test(groups={"regression"}, dataProvider="data_invalid")
  public void enter_invalid_username(String s) {
	  System.out.println("the input value from excel is " +s);
	  WebElement username = driver.findElement(By.xpath(obj.getProperty("username")));
	  username.clear();
	  username.sendKeys(s);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='user_name'][@data-bv-validator='stringLength']"));
	  Assert.assertEquals(message.getText(), "This value is not valid");
	  
  } 
  @Test(groups={"regression"})
  public void blank_username() {
	  WebElement username = driver.findElement(By.xpath(obj.getProperty("username")));
	  username.clear();
	  username.sendKeys("a");
	  username.sendKeys(Keys.BACK_SPACE);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='user_name'][@data-bv-validator='notEmpty']"));
	  Assert.assertEquals(message.getText(), "Please enter your Username");
	  //
  } 
  @Test(groups={"regression"}, dataProvider="data_invalid")
  public void enter_invalid_password(String s) {
	  WebElement password = driver.findElement(By.xpath(obj.getProperty("password")));
	  password.clear();
	  password.sendKeys(s);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='user_password'][@data-bv-validator='stringLength']"));
	  Assert.assertEquals(message.getText(), "This value is not valid");
  } 
  @Test 
  public void blank_password() {
	  WebElement password = driver.findElement(By.xpath(obj.getProperty("password")));
	  password.clear();
	  password.sendKeys("a");
	  password.sendKeys(Keys.BACK_SPACE);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='user_password'][@data-bv-validator='notEmpty']"));
	  Assert.assertEquals(message.getText(), "Please enter your Password");
	  
  }
  @Test(groups={"regression"}, dataProvider="data_invalid")
  public void enter_invalid_confirm_password(String s) {
	  WebElement confirmPassword = driver.findElement(By.xpath(obj.getProperty("confirm_password")));
	  confirmPassword.clear();
	  confirmPassword.sendKeys(s);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='confirm_password'][@data-bv-validator='stringLength']"));
	  Assert.assertEquals(message.getText(), "This value is not valid");
	  
  } 
  @Test
  public void blank_confirm_password() {
	  WebElement confirmPassword = driver.findElement(By.xpath(obj.getProperty("confirm_password")));
	  confirmPassword.clear();
	  confirmPassword.sendKeys("a");
	  confirmPassword.sendKeys(Keys.BACK_SPACE);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='confirm_password'][@data-bv-validator='notEmpty']"));
	  Assert.assertEquals(message.getText(), "Please confirm your Password");
	  
  } 
  @Test(groups={"regression"}, dataProvider="data_invalid")
  public void enter_invalid_email(String s) {
	  WebElement email = driver.findElement(By.xpath(obj.getProperty("email")));
	  email.clear();
	  email.sendKeys(s);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='email'][@data-bv-validator='stringLength']"));
	  Assert.assertEquals(message.getText(), "This value is not valid");
	  
  } 
  @Test
  public void blank_email() {
	  WebElement email = driver.findElement(By.xpath(obj.getProperty("email")));
	  email.clear();
	  email.sendKeys("a");
	  email.sendKeys(Keys.BACK_SPACE);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='email'][@data-bv-validator='notEmpty']"));
	  Assert.assertEquals(message.getText(), "Please enter your Email Address");
	  
  }
  
  @Test (groups={"sanity","regression"})
  public void valid_first_name() {
	  WebElement firstname = driver.findElement(By.xpath(obj.getProperty("first_name")));
	  firstname.clear();
	  firstname.sendKeys("aaa");
	  WebElement header_firstname = driver.findElement(By.xpath(obj.getProperty("header_firstname")));
	  String color = header_firstname.getCssValue("color");
	  String actualHex = ColorToHexa.convertColorToHexa(color);
	  System.out.println("the colour of header is " +actualHex);
	  Assert.assertEquals(actualHex, "#3c763d");

	  //Assert. #a94442 #3c763d
	  
  }
  
  @Test (groups={"sanity","regression"})
  public void valid_last_name() {
	  WebElement lastname = driver.findElement(By.xpath(obj.getProperty("last_name")));
	  lastname.clear();
	  lastname.sendKeys("aaa");
	  WebElement header_firstname = driver.findElement(By.xpath(obj.getProperty("header_lastname")));
	  String color = header_firstname.getCssValue("color");
	  String actualHex = ColorToHexa.convertColorToHexa(color);
	  System.out.println("the colour of header is " +actualHex);
	  Assert.assertEquals(actualHex, "#3c763d");

	  //Assert. #a94442 #3c763d
	  
  }
  @Test (groups={"sanity","regression"})
  public void valid_username() {
	  WebElement username = driver.findElement(By.xpath(obj.getProperty("username")));
	  username.clear();
	  username.sendKeys("aaa12345");
	  WebElement header_firstname = driver.findElement(By.xpath(obj.getProperty("header_username")));
	  String color = header_firstname.getCssValue("color");
	  String actualHex = ColorToHexa.convertColorToHexa(color);
	  System.out.println("the colour of header is " +actualHex);
	  Assert.assertEquals(actualHex, "#3c763d");

	  //Assert. #a94442 #3c763d
	  
  }
  @Test (groups={"sanity","regression"})
  public void valid_password() {
	  WebElement password = driver.findElement(By.xpath(obj.getProperty("password")));
	  password.clear();
	  password.sendKeys("aaa12345");
	  WebElement header_firstname = driver.findElement(By.xpath(obj.getProperty("header_password")));
	  String color = header_firstname.getCssValue("color");
	  String actualHex = ColorToHexa.convertColorToHexa(color);
	  System.out.println("the colour of header is " +actualHex);
	  Assert.assertEquals(actualHex, "#3c763d");

	  //Assert. #a94442 #3c763d
	  
  }
  @Test (groups={"sanity","regression"})
  public void valid_confirm_password() {
	  WebElement confirmPassword = driver.findElement(By.xpath(obj.getProperty("confirm_password")));
	  confirmPassword.clear();
	  confirmPassword.sendKeys("aaa12345");
	  WebElement header_firstname = driver.findElement(By.xpath(obj.getProperty("header_confirm_password")));
	  String color = header_firstname.getCssValue("color");
	  String actualHex = ColorToHexa.convertColorToHexa(color);
	  System.out.println("the colour of header is " +actualHex);
	  Assert.assertEquals(actualHex, "#3c763d");

	  //Assert. #a94442 #3c763d
	  
  }
  @Test (groups={"sanity","regression"})
  public void valid_email() {
	  WebElement email = driver.findElement(By.xpath(obj.getProperty("email")));
	  email.clear();
	  email.sendKeys("aaa12345");
	  WebElement header_firstname = driver.findElement(By.xpath(obj.getProperty("header_email")));
	  String color = header_firstname.getCssValue("color");
	  String actualHex = ColorToHexa.convertColorToHexa(color);
	  System.out.println("the colour of header is " +actualHex);
	  Assert.assertEquals(actualHex, "#3c763d");

	  //Assert. #a94442 #3c763d
	  
  }
  
  @Test(priority=2, groups={"sanity","regression"})
  public void create_user() {
	  WebElement firstname = driver.findElement(By.xpath(obj.getProperty("first_name")));
	  WebElement lastname = driver.findElement(By.xpath(obj.getProperty("last_name")));
	  WebElement dept = driver.findElement(By.xpath(obj.getProperty("department")));
	  WebElement username = driver.findElement(By.xpath(obj.getProperty("username")));
	  WebElement password = driver.findElement(By.xpath(obj.getProperty("password")));
	  WebElement confirmPassword = driver.findElement(By.xpath(obj.getProperty("confirm_password")));
	  WebElement email = driver.findElement(By.xpath(obj.getProperty("email")));
	  WebElement contNumber = driver.findElement(By.xpath(obj.getProperty("contact_number")));
	  WebElement submit = driver.findElement(By.xpath(obj.getProperty("submit_button")));
	  firstname.clear();
	  lastname.clear();
	  username.clear();
	  password.clear();
	  confirmPassword.clear();
	  email.clear();
	  contNumber.clear();
	  firstname.sendKeys("nirav");
	  lastname.sendKeys("patel");
	  org.openqa.selenium.support.ui.Select sel = new org.openqa.selenium.support.ui.Select(dept);
	  sel.selectByIndex(2);
	  username.sendKeys("newUser1");
	  password.sendKeys("1234asdf");
	  confirmPassword.sendKeys("1234asdf");
	  email.sendKeys("asd@gmail.com");
	  contNumber.sendKeys("9510951095");
	  submit.click();
	  WebElement head = driver.findElement(By.xpath(obj.getProperty("submitMessage")));
	  Assert.assertEquals(head.getText(), "Thanks");
	  
  }
  @Test(groups={"regression"}, dataProvider="data_invalid")
  public void enter_invalid_contact_number(String s) {
	  WebElement contNumber = driver.findElement(By.xpath(obj.getProperty("contact_number")));
	  contNumber.clear();
	  contNumber.sendKeys(s);
	  WebElement message = driver.findElement(By.xpath("//small[@data-bv-validator-for='contact_no'][@data-bv-validator='stringLength']"));
	  Assert.assertEquals(message.getText(), "This value is not valid");
	  
  }
  @DataProvider
  public Object[][] data_invalid() throws Exception {
	  Object[][] testObjArray = ExcelUtils.getTableArray("DataExcel.xlsx","InvalidData");

      return (testObjArray);
    };
   
   
  
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  

  @AfterSuite (groups={"sanity","regression"})
  public void closedBrowser() {
	  driver.quit();
  }

}
