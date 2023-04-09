package StepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

public class LoginStep {

    WebDriver driver;

    @Before
    public void setUp() {
        // Set up Chrome driver and wait times
        System.setProperty("webdriver.chrome.driver", "/Users/naufalazhar/Documents/ChromeDriver/chromedriver");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Close the driver after each test case
        driver.quit();
    }
    
//    User opens the login page successfully
    @Given("User navigates to the login page")
    public void user_navigates_to_the_login_page() {
         driver.navigate().to("https://www.saucedemo.com/");
    }

    @Then("The login page should be displayed")
    public void the_login_page_should_be_displayed() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        assertEquals(true, usernameField.isDisplayed());
        assertEquals(true, passwordField.isDisplayed());
        assertEquals(true, loginButton.isDisplayed());
        driver.quit();
    }
    
//    User logs in with valid credentials
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @When("User enters valid {string} and {string}")
    public void user_enters_valid_username_and_password(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    @And("User clicks the login button")
    public void user_clicks_the_login_button() throws InterruptedException {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
		Thread.sleep(3000);
    }

    @Then("The user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.id("inventory_container")).isDisplayed());
        driver.quit();
    }
    
	@Then("Failed login and showing message")
	public void Failed_login_and_showing_message() {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed());
	}
	

//    User logs out successfully
    @Given("User is logged in")
    public void user_is_logged_in() {
        driver.navigate().to("https://www.saucedemo.com/");
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");

        loginButton.click();
    }

    @When("User clicks the logout button")
    public void user_clicks_the_logout_button() throws InterruptedException {
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        Thread.sleep(2000);
        
        logoutButton.click();
    }

    @Then("The user should be logged out successfully")
    public void the_user_should_be_logged_out_successfully() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        
        assertEquals(true, loginButton.isDisplayed());
        driver.quit();
    }
}
