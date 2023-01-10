package e_commerceWebSite.pageObjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static final String URL = "https://rahulshettyacademy.com/client";
    public static final String userEmail = "Sinanctn54@gmail.com";
    public static final String password = "Sinan541";


    public static void main(String[] args) throws InterruptedException {


        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();

        ProductCatalogue productCatalogue = landingPage.loginApplicatin(StandAloneTest.userEmail, StandAloneTest.password);
        productCatalogue.addProductToCart(productName);


        CartPage cartPage =  productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.setSelectCountry("india");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order.".trim()));

    }


}
