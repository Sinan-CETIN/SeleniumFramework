package com.automationexercise.testComponents;

import e_commerceWebSite.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTestAutomation {

    public WebDriver driver;
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    public BaseTestAutomation(WebDriver driver) {
        this.driver = driver;

    }

    public WebDriver initiliazeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//automationexercise//resources//Globalproperties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.geckodriver.driver", "//Users//Sinan//Downloads//geckodriver");
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.geckodriver.edge", "//Users//Sinan//Downloads//edgedriver");
            driver = new EdgeDriver();
        } else {
            driver = null;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }



    @BeforeTest(alwaysRun = true)
    public void launchApplication() throws IOException {
        driver = initiliazeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
