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
	
	@Test(priority=3)//删除一个工厂
	public void delFactory() throws SQLException, InterruptedException{
		Tools.button("delfactory", "factories", driver);
		Thread.sleep(2000);
		Tools.button("delfactorysurebtn", "factories", driver);
		Thread.sleep(2000);
		String test2=Tools.getelement("testelement", "factories", driver).getText();
		Assert.assertEquals(test1!=test2, true);
		
	}
}
