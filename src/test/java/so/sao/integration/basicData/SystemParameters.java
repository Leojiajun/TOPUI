package so.sao.integration.basicData;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class SystemParameters extends BaseTest {
	@Test(priority=1)//添加系统参数
	public void addSysPat() throws SQLException, InterruptedException{
		Tools.button("basedata", "systemparameters", driver);
		Thread.sleep(2000);
		Tools.button("systemparameters", "systemparameters", driver);
		Thread.sleep(2000);
		Tools.button("add", "systemparameters", driver);
		Tools.input("pname", "systemparameters", "pname"+Tools.getRandomString(5), driver);
		Tools.input("pvalue", "systemparameters", Tools.getRandomString(6), driver);
		Tools.input("comment", "systemparameters", Tools.getRandomString(6), driver);
		Tools.button("addsurebtn", "systemparameters", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));		
	}
	
	@Test(priority=2)//修改系统参数
	public void alterSysPat() throws SQLException, InterruptedException{
		Tools.button("alter", "systemparameters", driver);
		Thread.sleep(2000);
		Tools.input("pname", "systemparameters", "pname"+Tools.getRandomString(5), driver);
		Tools.input("pvalue", "systemparameters", Tools.getRandomString(6), driver);
		Tools.input("comment", "systemparameters", Tools.getRandomString(6), driver);
		Tools.button("altersurebtn", "systemparameters", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));	
	}
	
	@Test(priority=3)//禁用系统参数
	public void stopSysPat() throws SQLException, InterruptedException{
		Tools.button("stopandawaken", "systemparameters", driver);
		Thread.sleep(2000);
		Tools.button("stopandawakensurebtn", "systemparameters", driver);
		Thread.sleep(2000);
		String teststop = Tools.getelement("stopandawaken", "systemparameters", driver).getText();
		Assert.assertTrue(teststop.contains("启用"));
	}
	
	@Test(priority=4)//启用系统参数
	public void awakenSysPat() throws SQLException, InterruptedException{
		Tools.button("stopandawaken", "systemparameters", driver);
		Thread.sleep(2000);
		Tools.button("stopandawakensurebtn", "systemparameters", driver);
		Thread.sleep(2000);
		String testwawken = Tools.getelement("stopandawaken", "systemparameters", driver).getText();
		Assert.assertTrue(testwawken.contains("禁用"));
		Tools.button("stopandawaken", "systemparameters", driver);
		Thread.sleep(2000);
		Tools.button("stopandawakensurebtn", "systemparameters", driver);
		Thread.sleep(1000);
	}

}
