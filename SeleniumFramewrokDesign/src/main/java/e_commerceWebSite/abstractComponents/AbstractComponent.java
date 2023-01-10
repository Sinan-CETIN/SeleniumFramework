package e_commerceWebSite.abstractComponents;

import e_commerceWebSite.pageObjects.CartPage;
import e_commerceWebSite.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cart;


    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement orderHeader;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    }


    public CartPage goToCartPage() {
        // driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        cart.click();
        return new CartPage(this.driver);
    }

    public OrderPage goToOrdersPage() {
        orderHeader.click();
        return new OrderPage(this.driver);
    }


    public void waitForElementDisappear(WebElement ele) throws InterruptedException {
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // wait.until(ExpectedConditions.invisibilityOf(ele));
        Thread.sleep(1500);
    }

    public void waitForWebElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
