package e_commerceWebSite.pageObjects;

import e_commerceWebSite.testComponents.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import java.util.HashMap;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";


    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {


        ProductCatalogue productCatalogue = super.landingPage.loginApplicatin(map.get("email"), map.get("password"));
        System.out.println(map.get("product"));
        productCatalogue.addProductToCart(map.get("product"));


        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(map.get("product"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
        checkoutPage.setSelectCountry("india");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order.".trim()));
    }

    @Test(dependsOnMethods = {"submitOrder"}) //--> You can't run this test individually
    public void OrderHistoryTest() {
        ProductCatalogue productCatalogue = super.landingPage.loginApplicatin(StandAloneTest.userEmail, StandAloneTest.password);
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Boolean flag = orderPage.verifyOrderHistroy(this.productName);
        Assert.assertTrue(flag);
    }//To verify ZARA COAT 3 displaying in orders Page


}
