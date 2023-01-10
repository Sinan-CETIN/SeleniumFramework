package com.automationexercise.AutomationTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Start {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(1000));

        WebElement page = driver.findElement(By.cssSelector("html"));
        boolean isPageVisible = page.isDisplayed();
        Assert.assertTrue(isPageVisible);

        System.out.println("Page is visible");

        closeAd(driver,wait1);

        WebElement productButton = driver.findElement(By.xpath("//a[@href='/products']"));
        productButton.click();

        System.out.println("After Product tab is clicked");

        closeAd(driver,wait1);

        WebElement productsPage = driver.findElement(By.tagName("html"));
        wait.until(ExpectedConditions.visibilityOf(productsPage));

        System.out.println("After Product tab is clicked");

        closeAd(driver,wait1);

        System.out.println("After Ad is closed");


        List<WebElement> productList = driver.findElements(By.cssSelector(".single-products"));

        System.out.println("After taking full list of the products");

        if(productList.size() == 0){
            throw new NoSuchElementException("There is no Product on this page");
        }

        System.out.println("The product list size is " + productList.size());

        WebElement firstProduct = productList.get(0);

//        Actions actions = new Actions(driver);
//        actions.moveToElement(firstProduct).build().perform();
//
//        System.out.println("The move mouse to first element");

        WebElement firstElementClickButton = driver.findElement(By.xpath("//div[@class='product-overlay']//a[@class='btn btn-default add-to-cart'][text()='Add to cart']"));
//        wait.until(ExpectedConditions.elementToBeClickable(firstElementClickButton));
//
//        System.out.println("THE LOCATION IS " + firstElementClickButton.getLocation());
//        System.out.println("THE LOCATION IS " + firstElementClickButton.toString());
//
//        firstElementClickButton.click();
        click(firstProduct, firstElementClickButton,driver);

        System.out.println("First element is clicked");
        driver.quit();
    }

        private static void closeAd(WebDriver driver, WebDriverWait wait){
            try {
                WebElement dismissBtn = driver.findElement(By.id("dismiss-button"));
                wait.until(ExpectedConditions.elementToBeClickable(dismissBtn));
            }catch (NoSuchElementException nse){
                System.out.println("There is web element with '#dismiss-button' locator");
            }
        }

    public static void click(WebElement hoveroverElement,WebElement clickElement, WebDriver driver)
    {
        try
        {
            String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
            ((JavascriptExecutor) driver).executeScript(mouseOverScript,hoveroverElement);
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript(mouseOverScript,clickElement);
            Thread.sleep(1000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",clickElement);


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}



