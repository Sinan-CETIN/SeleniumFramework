package e_commerceWebSite.pageObjects;

import e_commerceWebSite.testComponents.BaseTest;
import e_commerceWebSite.testComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {


    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplicatin(StandAloneTest.userEmail, StandAloneTest.password + "0");

        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }


    @Test
    public void productErrorValidation () throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = super.landingPage.loginApplicatin(StandAloneTest.userEmail, StandAloneTest.password);
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName + "0");
        Assert.assertFalse(match);
    }

}
