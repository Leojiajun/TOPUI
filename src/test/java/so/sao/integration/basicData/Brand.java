package so.sao.integration.basicData;

import java.sql.SQLException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Brand extends BaseTest{
	String test = Tools.getRandomString(6);
	@Test(priority=2)
	public void newBrand() throws InterruptedException, SQLException{
		driver.navigate().refresh();
		Thread.sleep(5000);
		Tools.button("basedata", "brand", driver);
		Thread.sleep(2000);
		Tools.button("brandmanage", "brand", driver);
		Tools.waitForElementPresent("add", "brand", driver);
		Tools.button("add", "brand", driver);
		Tools.waitForElementPresent("addsure", "brand", driver);
		Thread.sleep(2000);
		Tools.input("code", "brand", test, driver);
		Tools.input("name", "brand", Tools.getRandomString(6), driver);
		Tools.input("contacts", "brand", "张三", driver);
		Tools.input("mobile", "brand", "13812344321", driver);
		Tools.input("remark", "brand", "备注", driver);
		Tools.button("addsure", "brand", driver);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(test));	
	}
	
	@Test(priority=3)//修改
	public void editBrand() throws SQLException, InterruptedException{
		String testname = Tools.getRandomString(6);	
		Tools.button("edit", "brand", driver);
		Tools.waitForElementPresent("editsure", "brand", driver);
		Thread.sleep(2000);
		Tools.input("name", "brand", testname, driver);
		Tools.input("contacts", "brand", "张三修igai", driver);
		Tools.input("mobile", "brand", "13811111111", driver);
		Tools.input("remark", "brand", "备注2", driver);
		Tools.button("editsure", "brand", driver);	
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(testname));
	}
	@Test(priority=4)//禁用和启用
	public void stopandawaken() throws SQLException, InterruptedException{
		driver.navigate().refresh();
		Thread.sleep(2000);
		Tools.button("stop", "brand", driver);
		Tools.waitForElementPresent("stopsure", "brand", driver);
		Tools.button("stopsure", "brand", driver);
		Thread.sleep(2000);
		String teststop = Tools.getelement("stop", "brand", driver).getText();
		Assert.assertTrue(teststop.contains("启用"));
		Thread.sleep(2000);
		Tools.button("awaken", "brand", driver);
		Tools.waitForElementPresent("awakensure", "brand", driver);
		Tools.button("awakensure", "brand", driver);
		Thread.sleep(2000);
		String testawaken = Tools.getelement("awaken", "brand", driver).getText();
		Assert.assertTrue(testawaken.contains("禁用"));
	}
	@Test(priority=5)//搜索
	public void search() throws SQLException, InterruptedException{
		Thread.sleep(2000);
		Tools.input("search", "brand", test, driver);
		Tools.getelement("search", "brand", driver).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String testsearch = Tools.getelement("total", "brand", driver).getText().replaceAll("\\s", "");
		int brandNum = Integer.valueOf(testsearch.substring(1,2));
		Assert.assertEquals(brandNum==1, true);
	}
	
	@Test(priority=6)//删除
	public void delBrand() throws InterruptedException, SQLException{
		Thread.sleep(2000);
		Tools.button("delete", "brand", driver);
		Tools.waitForElementPresent("deletesurebtn", "brand", driver);
		Thread.sleep(1000);
		Tools.button("deletesurebtn", "brand", driver);
		Thread.sleep(3000);
		Assert.assertFalse(driver.getPageSource().contains(test));
	}
		
	
	
}
