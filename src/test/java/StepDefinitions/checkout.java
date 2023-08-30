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

public class checkout {
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
    
    @Given("User has logged in with valid credentials")
    public void user_has_logged_in_with_valid_credentials() throws InterruptedException {
        driver.navigate().to("https://www.saucedemo.com/");

        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        
        Thread.sleep(2000);
        loginButton.click();
    }

    @When("User adds items to cart and goes to checkout")
    public void user_adds_items_to_cart_and_goes_to_checkout() throws InterruptedException {
        WebElement addToCartButton = driver.findElement(By.cssSelector(".inventory_item button"));
        addToCartButton.click();

        WebElement cartButton = driver.findElement(By.cssSelector(".shopping_cart_container"));
        cartButton.click();

        WebElement checkoutButton = driver.findElement(By.cssSelector(".checkout_button"));
        Thread.sleep(2000);
        checkoutButton.click();
    }

    @And("User enters checkout information and completes checkout")
    public void user_enters_checkout_information_and_completes_checkout() throws InterruptedException {
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        WebElement continueButton = driver.findElement(By.cssSelector(".checkout_buttons .btn_primary.cart_button"));

        firstNameField.sendKeys("John");
        lastNameField.sendKeys("Doe");
        postalCodeField.sendKeys("12345");

        continueButton.click();

        WebElement finishButton = driver.findElement(By.id("finish"));
        Thread.sleep(2000);
        finishButton.click();
    }

    @When("User enters incomplete checkout information and tries to complete checkout")
    public void user_enters_incomplete_checkout_information_and_tries_to_complete_checkout() throws InterruptedException {
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement continueButton = driver.findElement(By.cssSelector(".checkout_buttons .btn_primary.cart_button"));

        firstNameField.sendKeys("John");
        lastNameField.sendKeys("Doe");

        continueButton.click();

        WebElement finishButton = driver.findElement(By.cssSelector(".checkout_buttons .btn_action.cart_button"));
        Thread.sleep(2000);
        finishButton.click();
    }
    
    
    @Then("User should see checkout complete message")
    public void user_should_see_checkout_complete_message() {
        WebElement completeMessage = driver.findElement(By.cssSelector(".complete-header"));
        assertEquals("Thank you for your order!", completeMessage.getText());
    }
    
    @Then("User should see error message indicating missing information")
    public void user_should_see_error_message_indicating_missing_information() {
        WebElement errorMessage = driver.findElement(By.cssSelector(".error-message-container"));
        assertEquals("Error: Postal Code is required",
        errorMessage.getText());
    }
    
}