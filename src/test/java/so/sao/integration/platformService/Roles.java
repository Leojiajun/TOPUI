package so.sao.integration.platformService;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Roles extends BaseTest{
	@Test(priority=2)//新建角色
	public void newRole() throws SQLException, InterruptedException{
		driver.navigate().refresh();
		Thread.sleep(5000);
		Tools.button("platformservice", "roles", driver);
		Thread.sleep(2000);
		Tools.button("rolemanage", "roles", driver);
		Thread.sleep(2000);
		Tools.button("addrole", "roles", driver);
		Tools.waitForElementPresent("choiceactivity", "roles", driver);
		Tools.input("rolename", "roles", Tools.getRandomString(5), driver);
		Thread.sleep(2000);
		Tools.button("choiceactivity", "roles", driver);
		Tools.button("pulldown", "roles", driver);
		Tools.button("choiceCRM", "roles", driver);
		Tools.waitForElementPresent("addrolesurebtn", "roles", driver);
		Thread.sleep(2000);
		Tools.button("addrolesurebtn", "roles", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("创建成功"));
	}
	
	@Test(priority=3)//修改角色
	public void alterRole() throws SQLException, InterruptedException{
		String test="备注"+Tools.getRandomString(5);
		Thread.sleep(2000);
		Tools.button("alterrole", "roles", driver);
		Tools.waitForElementPresent("choiceplatform", "roles", driver);
		Tools.input("comment", "roles", test, driver);
		Thread.sleep(2000);
		Tools.button("choiceplatform", "roles", driver);
		Tools.waitForElementPresent("alterrolesurebtn", "roles", driver);
		Tools.button("alterrolesurebtn", "roles", driver);
		Tools.waitForElementPresent("addrole", "roles", driver);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains(test));
	}
	
	
	@Test(priority=4)//删除角色
	public void delRole() throws SQLException, InterruptedException{
		Thread.sleep(2000);
		Tools.button("delrole", "roles", driver);
		Thread.sleep(3000);
		Tools.button("delrolesurebtn", "roles", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("删除成功"));
	}
}
