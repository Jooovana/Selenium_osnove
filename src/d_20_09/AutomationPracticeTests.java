package d_20_09;



import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

		public class AutomationPracticeTests extends BasicTestHW20_09 {
			
			
			
		@Test(priority=10)
		public void addingProductsToCart() {
	
			driver.navigate().to(baseUrl + "/index.php?id_product=1&controller=product");
			
			buyBoxPage.scrollToPickAColorElement();
			
			buyBoxPage.getQuantityInput().clear();
			buyBoxPage.getQuantityInput().sendKeys("3");
			
			buyBoxPage.getSize("L");
			
			buyBoxPage.getPickColorElement("Blue").click();
			
			buyBoxPage.getAddToCartBtn().click();
			
			layerCartPage.waitForDialogVisibility();
			
			Assert.assertTrue(
					layerCartPage.getQuantity().getText().equals("3"),
					"ERROR: Quantity should be 3");	
			
			Assert.assertTrue(
					layerCartPage.getAttributesOfProduct().getText().equals("Blue, L"),
					"ERROR: Attributes should be Blue, L");
			
			String totalPriceText = layerCartPage.getTotalPrice().getText().replaceAll("[$]", "");
			double totalPrice = Double.parseDouble(totalPriceText);
			String productPriceText = buyBoxPage.getProductPrice().getText().replaceAll("[$]", "");
			double productPrice = Double.parseDouble(productPriceText);
			
			Assert.assertTrue(
					productPrice * 3 == totalPrice,
					"ERROR: Wrong total price");
			
			layerCartPage.getContinueToShopingBtn().click();
			
			layerCartPage.waitForDialogInvisibility();
			
			buyBoxPage.scrollToPickAColorElement();
			
			buyBoxPage.getQuantityInput().clear();
			buyBoxPage.getQuantityInput().sendKeys("1");
			
			buyBoxPage.getSize("S");
			
			buyBoxPage.getPickColorElement("Orange").click();
			
			buyBoxPage.getAddToCartBtn().click();
			
			layerCartPage.waitForDialogVisibility();
			
			layerCartPage.getProcedToCheckoutBtn().click();
			
			Assert.assertTrue(
					driver.getTitle().equals("Order - My Store"),
					"ERROR: You are on wrong page");
		}
		
		@Test(priority=20)
		public void topMenuMouseOver() throws InterruptedException{
	
			actions.moveToElement(topMenuPage.getWomenLink()).perform();
			wait.until(ExpectedConditions.visibilityOf(topMenuPage.getWomenDropdown()));
			softAssert.assertTrue(
					topMenuPage.getWomenDropdown().isDisplayed(),
					"ERROR: Women submenu should be displayed");
			
			actions.moveToElement(topMenuPage.getDressesLink()).perform();
			wait.until(ExpectedConditions.visibilityOf(topMenuPage.getDressesDropdown()));
			softAssert.assertTrue(
					topMenuPage.getDressesDropdown().isDisplayed(),
					"ERROR: Dresses submenu should be displayed");
			
			actions.moveToElement(topMenuPage.getTshirtsLink()).perform();
			wait.until(ExpectedConditions.invisibilityOf(topMenuPage.getWomenDropdown()));
			wait.until(ExpectedConditions.invisibilityOf(topMenuPage.getDressesDropdown()));
			softAssert.assertFalse(
					topMenuPage.getDressesDropdown().isDisplayed() && topMenuPage.getWomenDropdown().isDisplayed(),
					"ERROR: Dresses and women submenus should not be displayed");
			
			softAssert.assertAll();
		}
		
		@Test(priority=30)
		public void phoneNumberVisibilityCheckOnResize() {
	
			wait.until(ExpectedConditions.visibilityOf(headerPage.getShopPhone()));
			softAssert.assertTrue(
					headerPage.getShopPhone().isDisplayed(),
					"ERROR: Shopphone should be visible.");
			
			driver.manage().window().setSize(new Dimension(767, 700));
			wait.until(ExpectedConditions.invisibilityOf(headerPage.getShopPhone()));
			softAssert.assertFalse(
					headerPage.getShopPhone().isDisplayed(),
					"ERROR: Shopphone should not be visible.");
			
			driver.manage().window().setSize(new Dimension(800, 700));
			wait.until(ExpectedConditions.visibilityOf(headerPage.getShopPhone()));
			softAssert.assertTrue(
					headerPage.getShopPhone().isDisplayed(),
					"ERROR: Shopphone should be visible.");
			
			driver.manage().window().maximize();
			
			softAssert.assertAll();
		}
		
		@Test(priority=40)
		public void headerLinksCheck() {
			headerPage.getContactLink().click();
			softAssert.assertTrue(
					driver.getTitle().equals("Contact us - My Store"),
					"ERROR: Title should be Contact us - My Store");
			
			headerPage.getSignInLink().click();
			softAssert.assertTrue(
					driver.getTitle().equals("Login - My Store"),
					"ERROR: Title should be Login - My Store");
			
			softAssert.assertAll();
	
		}
		
	
	
	
	
	
	
	
	
}
