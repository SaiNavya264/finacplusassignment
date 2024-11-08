package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileWriter;
import java.io.IOException;

public class ProductsPage extends BasePage{
    @FindBy(css = ".inventory_item_name")
    private WebElement firstProductName;

    @FindBy(css = ".inventory_item_price")
    private WebElement firstProductPrice;

    @FindBy(css = ".btn_inventory")
    private WebElement addToCartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstProductName() {
        return firstProductName.getText();
    }

    public String getFirstProductPrice() {
        return firstProductPrice.getText();
    }

    public void saveProductDetails(String productName, String productPrice) {
        try (FileWriter writer = new FileWriter("productDetails.txt")) {
            writer.write("Product Name: " + productName + "\n");
            writer.write("Product Price: " + productPrice + "\n");
        } catch (IOException e) {
            System.out.println("Error while saving product details: " + e.getMessage());
        }
    }

    public void addToCart() {
        addToCartButton.click();
    }
}
