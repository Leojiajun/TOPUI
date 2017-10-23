package so.sao.integration.basicData;

import java.sql.SQLException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class DataDictionary extends BaseTest{
	@Test(priority=2)//新建数据字典
	public void newDataDictionary() throws SQLException, InterruptedException{
		Actions action = new Actions(driver);
		action.moveToElement(Tools.getelement("basedata", "datadictionary", driver)).build().perform();//悬浮在基础数据
		Thread.sleep(2000);
		Tools.button("datamanage", "datadictionary", driver);
		Thread.sleep(2000);
		Tools.button("adddata", "datadictionary", driver);
		Tools.input("datacode", "datadictionary", Tools.getRandomString(6), driver);
		Tools.button("dataclass", "datadictionary", driver);
		Tools.button("productclass", "datadictionary", driver);
		Tools.input("dataname", "datadictionary", "test"+Tools.getRandomString(6), driver);
		Tools.button("newdatasurebtn", "datadictionary", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("添加成功"));
	}
	
	@Test(priority=3)//删除数据字典
	public void delDataDictionary() throws SQLException, InterruptedException{
		Tools.button("deldata", "datadictionary", driver);
		Thread.sleep(2000);
		Tools.button("deldatasurebtn", "datadictionary", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("删除成功"));
	}
}
