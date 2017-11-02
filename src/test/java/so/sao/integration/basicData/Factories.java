package so.sao.integration.basicData;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Factories extends BaseTest{
	private String test1=Tools.getRandomString(5);
	@Test(priority=2)//新建工厂
	public void addFactory() throws SQLException, InterruptedException{
		Tools.button("basedata", "factories", driver);
		Thread.sleep(2000);
		Tools.button("factorymanage", "factories", driver);
		Thread.sleep(2000);
		Tools.button("addfactory", "factories", driver);
		Thread.sleep(2000);
		Tools.input("factoryname", "factories", test1, driver);
		Tools.input("factoryaddress", "factories", "上海", driver);
		Tools.button("factorystyle", "factories", driver);
		Tools.button("presscompany", "factories", driver);
		Thread.sleep(2000);
		Tools.button("addfacsurebtn", "factories", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	
	
	@Test(priority=3)//搜索工厂
	public void searchFactory() throws InterruptedException, SQLException{
		driver.navigate().refresh();
		Thread.sleep(2000);
		Tools.input("searchinput", "factories", test1, driver);
		Tools.button("search", "factories", driver);
		Thread.sleep(2000);
		String strNum = Tools.getelement("factorytotal", "factories", driver).getText().replaceAll("\\s", "");
		int pNum = Integer.valueOf(strNum.substring(1,2));
		Assert.assertEquals(pNum==1, true);
	} 
	
	
	@Test(priority=4)//修改工厂
	public void alterFactory() throws SQLException, InterruptedException{
		String test = "备注"+Tools.getRandomString(5);
		Thread.sleep(2000);
		Tools.button("alterfactory", "factories", driver);
		Thread.sleep(2000);
		Tools.input("factoryaddress", "factories", "北京", driver);
		Tools.button("factorystyle", "factories", driver);
		Tools.button("presscompany2", "factories", driver);
		Thread.sleep(2000);
		Tools.input("comment", "factories", test, driver);
		Thread.sleep(2000);
		Tools.button("alterfactorysurebtn", "factories", driver);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(test));
	}
	
	
	@Test(priority=5)//禁用和启用工厂
	public void stopAndawakenFactory() throws SQLException, InterruptedException{
		Tools.button("stopandawaken", "factories", driver);
		Tools.waitForElementPresent("stopandawakensurebtn", "factories", driver);
		Tools.button("stopandawakensurebtn", "factories", driver);
		Thread.sleep(1000);
		String test1=Tools.getelement("stopandawaken", "factories", driver).getText();
		Assert.assertTrue(test1.contains("启用"));
		Tools.button("stopandawaken", "factories", driver);
		Tools.waitForElementPresent("stopandawakensurebtn", "factories", driver);
		Tools.button("stopandawakensurebtn", "factories", driver);
		Thread.sleep(1000);
		String test2=Tools.getelement("stopandawaken", "factories", driver).getText();
		Assert.assertTrue(test2.contains("禁用"));
	} 
	
	
	@Test(priority=6)//删除一个工厂
	public void delFactory() throws SQLException, InterruptedException{
		Tools.button("delfactory", "factories", driver);
		Thread.sleep(2000);
		Tools.button("delfactorysurebtn", "factories", driver);
		Thread.sleep(2000);
		String test2=Tools.getelement("testelement", "factories", driver).getText();
		Assert.assertEquals(test1!=test2, true);		
	}
}
