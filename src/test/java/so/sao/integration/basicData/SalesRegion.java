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
		Tools.button("basedata", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("salesmanage", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("addsales", "salesregion", driver);
		Tools.input("salescode", "salesregion", Tools.getRandomString(5), driver);
		Tools.input("salesregionname", "salesregion", "江苏"+Tools.getRandomString(5), driver);
		Tools.button("jiangsu", "salesregion", driver);
		Tools.button("newsalesurebtn", "salesregion", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	@Test(priority=3)//删除销售区域
	public void delSalesRegion() throws SQLException, InterruptedException{
		Tools.button("delsalesregion", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("delsalessurebtn", "salesregion", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	
	@Test(priority=4)//导入销售区域
	public void leadinSalesRegion() throws SQLException, InterruptedException, IOException{
		Tools.button("salesarealeadin", "salesregion", driver);
		Thread.sleep(2000);
		Tools.button("uploadchoice", "salesregion", driver);
		Runtime.getRuntime().exec("D:/uploadexe/chromesalesarea.exe");
		Thread.sleep(30000);
		Tools.button("uploadsurebtn", "salesregion", driver);
		Thread.sleep(5000);
	}
}

