package tests;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;

import java.time.Duration;

public class Exercise1 {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\saina\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testSaucedemoWorkflow() {
        // Step 2: Login to the site
        loginPage.login("standard_user", "secret_sauce");
        // Step 4: Get first product name and price, store in text file
        String productName = productsPage.getFirstProductName();
        String productPrice = productsPage.getFirstProductPrice();
        productsPage.saveProductDetails(productName, productPrice);
        // Step 5: Add product to cart
        productsPage.addToCart();
        // Step 6: Verify product is in the cart
        driver.navigate().to("https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.isProductInCart(productName));

        // Step 7: Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
