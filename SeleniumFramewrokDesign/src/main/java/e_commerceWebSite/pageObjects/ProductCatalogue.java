package e_commerceWebSite.pageObjects;

import e_commerceWebSite.abstractComponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {


    WebDriver driver;
    By produscListBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public ProductCatalogue(WebDriver driver) {
        super(driver);
        WebDriverManager.chromedriver().setup();
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".mb-3")
    List<WebElement> productList;

    @FindBy(className = "ng-animating")
    WebElement spinner;


    public List<WebElement> getProductList() {
        super.waitForElementToAppear(this.produscListBy);
        return productList;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementDisappear(spinner);
    }

}
