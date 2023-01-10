package e_commerceWebSite.pageObjects;

import e_commerceWebSite.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css=".totalRow button")
    WebElement checkoutElement;

    @FindBy(css=".cartSection h3")
    private List<WebElement> productTitleList;


    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductDisplay(String productName) {
        boolean match = productTitleList.stream().anyMatch(productElement -> productElement.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goToCheckoutPage() {
        checkoutElement.click();
        return new CheckoutPage(this.driver);
    }
}
