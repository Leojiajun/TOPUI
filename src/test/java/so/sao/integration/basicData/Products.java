package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Products extends BaseTest {
	private String test1="PC"+Tools.getRandomString(6);
	@Test(priority=2)//新建一个产品
	public void newProduct() throws SQLException, InterruptedException, IOException{
		driver.navigate().refresh();
		Tools.button("basedata", "products", driver);
		Thread.sleep(2000);
		Tools.button("productmanage", "products", driver);
		Thread.sleep(2000);
		Tools.button("addproduct", "products", driver);
		Tools.input("productcoding", "products", test1, driver);
		Tools.input("productname", "products", "PN"+Tools.getRandomString(6), driver);
		Tools.button("classification", "products", driver);
		Tools.input("saleprice", "products", "10", driver);
		Tools.button("wine", "products", driver);
		Thread.sleep(2000);
		Tools.button("preserve", "products", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	@Test(priority=3)//修改一个产品
	public void alterProduct() throws SQLException, InterruptedException{
		Tools.button("productalter", "products", driver);
		Thread.sleep(2000);
		Tools.input("productcoding", "products", "PC"+Tools.getRandomString(6), driver);
		Tools.input("saleprice", "products", "5", driver);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); //下拉到页面底部
		Thread.sleep(1000);
		Tools.button("productaltersurebtn", "products", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	@Test(priority=4)//禁用和启用产品
	public void stopAndawakenProduct() throws SQLException, InterruptedException{
		Tools.button("stopandawaken", "products", driver);
		Tools.waitForElementPresent("stopandawakensurebtn", "products", driver);
		Tools.button("stopandawakensurebtn", "products", driver);
		Thread.sleep(1000);
		String test1=Tools.getelement("stopandawaken", "products", driver).getText();
		Assert.assertTrue(test1.contains("启用"));
		Tools.button("stopandawaken", "factories", driver);
		Tools.waitForElementPresent("stopandawakensurebtn", "products", driver);
		Tools.button("stopandawakensurebtn", "products", driver);
		Thread.sleep(1000);
		String test2=Tools.getelement("stopandawaken", "products", driver).getText();
		Assert.assertTrue(test2.contains("禁用"));
	} 
	
	@Test(priority=5)//删除一个产品
	public void delProduct() throws SQLException, InterruptedException{
		Tools.button("Pdelete", "products", driver);
		Thread.sleep(2000);
		//Tools.waitForElementPresent("deleteproductmakesureBtn", "products", driver);
		Tools.button("deleteproductmakesureBtn", "products", driver);
		Thread.sleep(2000);
		String test2=Tools.getelement("testelement", "products", driver).getText();
		System.out.println(test2);
		Assert.assertEquals(test1!=test2, true);	
	}
	
	//@Test(priority=6)//导入产品
	public void leadinProduct() throws SQLException, InterruptedException, IOException{
		Tools.button("leadin", "products", driver);
		Thread.sleep(2000);
		Tools.button("uploadchoice", "products", driver);
		Thread.sleep(2000);
		Runtime.getRuntime().exec("D:/uploadrun/product.exe");
		Thread.sleep(20000);
		Tools.button("uploadsurebtn", "products", driver);
		Thread.sleep(5000);
	}
}
