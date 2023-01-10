package com.automationexercise.AutomationTest;

import com.automationexercise.testComponents.BaseTestAutomation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class HomePage extends BaseTestAutomation {
    public static final String url = "https://automationexercise.com/";

    WebDriver driver;


    @FindBy(css = ".header-middle")
    WebElement headerMiddle;

    @FindBy(xpath = "//section[@id='slider']")
    WebElement slider;

    @FindBy(css ="html")
    WebElement page;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @BeforeTest
    public void goToHomePage() {
        driver.get(url);
    }

    @Test
    public void isHeaderVisibleInHomePage() {

        boolean flag = wait.until(ExpectedConditions.invisibilityOf(headerMiddle));
        Assert.assertTrue(flag);
    }

    @Test
    public void isSliderVisible() {
        boolean flag = wait.until(ExpectedConditions.invisibilityOf(slider));
        Assert.assertTrue(flag);
    }

    @Test
    public void isPageVisible() {
        goToHomePage();
        boolean flag = wait.until(ExpectedConditions.invisibilityOf(page));
        Assert.assertTrue(flag);
    }
}
