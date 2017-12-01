package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class SalesRegion extends BaseTest{
	@Test(priority=2)//新建销售区域
	public void addSalesRegion() throws SQLException, InterruptedException{
		driver.navigate().refresh();
		Thread.sleep(5000);
		Tools.button("basedata", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("salesmanage", "salesregion", driver);
		Thread.sleep(5000);
		Tools.button("addsales", "salesregion", driver);
		Tools.input("salescode", "salesregion", Tools.getRandomString(5), driver);
		Thread.sleep(2000);
		Tools.input("salesregionname", "salesregion", "江苏"+Tools.getRandomString(5), driver);
		Thread.sleep(2000);
		Tools.button("jiangsu", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("newsalesurebtn", "salesregion", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	
	@Test(priority=3)//修改销售区域
	public void alterSalesRegion() throws SQLException, InterruptedException{
		Tools.button("alter", "salesregion", driver);
		Thread.sleep(2000);
		Tools.input("salesregionname", "salesregion", "江苏"+Tools.getRandomString(5)+"北京", driver);
		Tools.button("beijing", "salesregion", driver);
		Thread.sleep(1000);
		Tools.button("altersalessurebtn", "salesregion", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
		
	}
	
	@Test(priority=4)//禁用和启用
	public void awakenAndStop() throws SQLException, InterruptedException{
		Tools.button("stop", "salesregion", driver);//点击禁用
		Thread.sleep(2000);
		Tools.button("stopandawakensurebtn", "salesregion", driver);
		Thread.sleep(2000);
		String test1 = Tools.getelement("stop", "salesregion", driver).getText();
		Assert.assertTrue(test1.contains("启用"));
		Thread.sleep(2000);
		Tools.button("stop", "salesregion", driver);//点击启用
		Thread.sleep(2000);
		Tools.button("stopandawakensurebtn", "salesregion", driver);
		Thread.sleep(2000);
		String test2 = Tools.getelement("stop", "salesregion", driver).getText();
		Assert.assertTrue(test2.contains("禁用"));
	}
	
	@Test(priority=5)//删除销售区域
	public void delSalesRegion() throws SQLException, InterruptedException{
		Tools.button("delsalesregion", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("delsalessurebtn", "salesregion", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	
	//@Test(priority=6)//导入销售区域
	public void leadinSalesRegion() throws SQLException, InterruptedException, IOException{
		Tools.button("salesarealeadin", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("uploadchoice", "salesregion", driver);
		Runtime.getRuntime().exec("D:/chromeuploadexe/chromesalesarea.exe");
		Thread.sleep(30000);
		Tools.button("uploadsurebtn", "salesregion", driver);
	}
}

