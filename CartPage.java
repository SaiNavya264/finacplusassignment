package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.FileWriter;
import java.io.IOException;

public class CartPage extends BasePage {
    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private static WebElement cartProductName;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public static boolean isProductInCart(String expectedProductName) {
        return cartProductName.getText().equals(expectedProductName);
    }
}
