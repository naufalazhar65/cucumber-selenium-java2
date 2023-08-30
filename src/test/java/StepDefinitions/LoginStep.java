package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

public class LoginStep {
    private WebDriver driver;
    
    @Before
    public void setUp() {
        // Set up Chrome driver and wait times
    	ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Close the driver after each test case
        driver.quit();
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("I should be on the inventory page")
    public void iShouldBeOnTheInventoryPage() {
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.saucedemo.com/inventory.html", currentUrl);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        WebElement errorElement = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(errorElement.isDisplayed());
    }

    @And("User clicks the logout button")
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
    }

    @Then("The login page should be displayed")
    public void the_login_page_should_be_displayed() {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        assertEquals(true, usernameField.isDisplayed());
        assertEquals(true, passwordField.isDisplayed());
        assertEquals(true, loginButton.isDisplayed());
    }

}
