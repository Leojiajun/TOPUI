package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Products extends BaseTest {
	private String test1="PC"+Tools.getRandomString(6);
	@Test(priority=2)//新建一个产品
	public void newProduct() throws SQLException, InterruptedException{
		Tools.button("basedata", "products", driver);
		Thread.sleep(2000);
		Tools.button("productmanage", "products", driver);
		Thread.sleep(2000);
		Tools.button("addproduct", "products", driver);
		//String test="PC"+Tools.getRandomString(6);
		Tools.input("productcoding", "products", test1, driver);
		Tools.input("productname", "products", "PN"+Tools.getRandomString(6), driver);
		Tools.button("classification", "products", driver);
		Tools.button("wine", "products", driver);
		Tools.button("preserve", "products", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	
	@Test(priority=3)//删除一个产品
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
	
	//@Test(priority=4)//导入产品
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
