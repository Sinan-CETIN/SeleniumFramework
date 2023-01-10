package e_commerceWebSite.pageObjects;

import e_commerceWebSite.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> orderPageProductList;


    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyOrderHistroy(String productName) {
        boolean match = orderPageProductList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }
}
