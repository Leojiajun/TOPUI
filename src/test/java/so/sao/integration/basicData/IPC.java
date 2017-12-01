package so.sao.integration.basicData;

import java.sql.SQLException;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class IPC extends BaseTest {
	String test = Tools.getRandomString(6);
	@Test(priority=2)//新建工控机
	public void newIPC() throws InterruptedException, SQLException{
		driver.navigate().refresh();
		Thread.sleep(2000);
		Tools.button("basedata", "IPC", driver);
		Thread.sleep(2000);
		Tools.button("ipcmanage", "IPC", driver);
		Thread.sleep(2000);
		Tools.button("addipc", "IPC", driver);
		Thread.sleep(2000);
		Tools.input("ipccode", "IPC", test, driver);
		Tools.input("ipcname", "IPC", Tools.getRandomString(5), driver);
		Tools.button("choicefactory", "IPC", driver);
		Tools.button("choiceonefactory", "IPC", driver);
		Tools.input("secretkey", "IPC", Tools.getRandomString(32), driver);
		Tools.input("supplier", "IPC", "供应商", driver);
		Tools.button("size", "IPC", driver);
		Thread.sleep(2000);
		Tools.button("choicesizeone", "IPC", driver);
		Tools.input("remark", "IPC", "备注", driver);
		Tools.waitForElementPresent("save", "IPC", driver);
		Tools.button("save", "IPC", driver);
		Thread.sleep(2000);
		Assert.assertTrue(driver.getPageSource().contains(test));
	}
	
	@Test(priority=3)//修改
	public void editIPC() throws InterruptedException, SQLException{
		String test2 = "修改"+Tools.getRandomString(6);
		Thread.sleep(2000);
		Tools.button("edit", "IPC", driver);
		Tools.input("ipcname", "IPC", test2, driver);
		Tools.input("supplier", "IPC", "供应商new", driver);
		Tools.input("remark", "IPC", "备注new", driver);
		Tools.button("editsavebutton", "IPC", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains(test2));	
	}
	@Test(priority=4)//禁用和启用
	public void stopandawaken() throws InterruptedException, SQLException{
		Thread.sleep(2000);
		Tools.button("stop", "IPC", driver);
		Thread.sleep(2000);
		Tools.button("stopcomfire", "IPC", driver);
		Thread.sleep(2000);
		String test3 = Tools.getelement("stop", "IPC", driver).getText();
		Assert.assertTrue(test3.equals("启用"));
		Thread.sleep(2000);
		Tools.button("stop", "IPC", driver);
		Thread.sleep(2000);
		Tools.button("stopcomfire", "IPC", driver);
		Thread.sleep(2000);
		String test4 = Tools.getelement("stop", "IPC", driver).getText();
		Assert.assertTrue(test4.equals("禁用"));
	}
	
	@Test(priority=5)//搜索
	public void search() throws InterruptedException, SQLException{
		Thread.sleep(2000);
		Tools.input("search", "IPC", test, driver);
		Thread.sleep(2000);
		Tools.getelement("search", "IPC", driver).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String strNum = Tools.getelement("total", "IPC", driver).getText().replaceAll("\\s", "");
		int ipcNum = Integer.valueOf(strNum.substring(1,2));
		Assert.assertEquals(ipcNum==1, true);	
	}

	@Test(priority=6)//删除
	public void delIPC() throws InterruptedException, SQLException{
		Thread.sleep(2000);
		Tools.button("delete", "IPC", driver);
		Thread.sleep(2000);
		Tools.waitForElementPresent("deletesurebtn", "IPC", driver);
		Tools.button("deletesurebtn", "IPC", driver);
		Thread.sleep(3000);
		Assert.assertFalse(driver.getPageSource().contains(test));
	}
}
