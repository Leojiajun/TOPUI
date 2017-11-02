package so.sao.integration.platformService;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Users extends BaseTest{
	@Test(priority=2)//新建用户
	public void newUser() throws SQLException, InterruptedException{
		Tools.button("platformservice", "users", driver);
		Thread.sleep(2000);
		Tools.button("usermanage", "users", driver);
		Thread.sleep(2000);
		Tools.button("adduser", "users", driver);
		Tools.input("loginname", "users", Tools.getRandomString(6)+"@126.com", driver);
		Tools.input("loginpassword", "users", "ab111111", driver);
		Tools.input("loginpasswordagain", "users", "ab111111", driver);
		Tools.input("fullname", "users", "测试a", driver);
		Tools.input("mobile", "users", "13812345678", driver);
		Tools.button("adduserchoicecompany", "users", driver);
		Thread.sleep(1000);
		Tools.button("adduserchoicecompanyone", "users", driver);
		Thread.sleep(1000);
		Tools.button("adduserchoiceleader", "users", driver);
		Thread.sleep(1000);
		Tools.button("adduserchoiceleaderone", "users", driver);
		Thread.sleep(1000);
		Tools.button("adduserchoicerole", "users", driver);
		Thread.sleep(1000);
		Tools.button("adduserchoiceroleone", "users", driver);
		Tools.waitForElementPresent("addusersurebtn", "users", driver);
		Tools.button("addusersurebtn", "users", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("保存成功"));
	}
	
	@Test(priority=3)//修改用户
	public void alterUser() throws SQLException, InterruptedException{
		Tools.button("useralter", "users", driver);
		Thread.sleep(2000);
		Tools.input("fullname", "users", "测试b", driver);
		Tools.input("mobile", "users", "13887654321", driver);
		Tools.waitForElementPresent("alterusersurebtn", "users", driver);
		Tools.button("alterusersurebtn", "users", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("修改成功"));	
	}
	
	@Test(priority=4)//重置用户
	public void resetUser() throws SQLException, InterruptedException{
		Tools.button("userreset", "users", driver);
		Thread.sleep(2000);
		Tools.input("resetloginpassword", "users", "ab222222", driver);
		Tools.input("resetloginpasswordagain", "users", "ab222222", driver);
		Tools.waitForElementPresent("resetusersurebtn", "users", driver);
		Tools.button("resetusersurebtn", "users", driver);
		Thread.sleep(1000);
	}
	@Test(priority=5)//停用用户
	public void stopUser() throws SQLException, InterruptedException{
		Tools.button("userstop", "users", driver);
		Thread.sleep(2000);
		Tools.button("stopusersurebtn", "users", driver);
		Thread.sleep(2000);
		String teststop=Tools.getelement("teststop", "users", driver).getText();
		Assert.assertTrue(teststop.contains("启用"));
	}
	
	@Test(priority=6)//启用用户
	public void awakenUser() throws SQLException, InterruptedException{
		Tools.button("userstop", "users", driver);
		Thread.sleep(2000);
		Tools.button("stopusersurebtn", "users", driver);
		Thread.sleep(2000);
		String testawaken=Tools.getelement("teststop", "users", driver).getText();
		Assert.assertTrue(testawaken.contains("停用"));
		Tools.button("userstop", "users", driver);
		Thread.sleep(2000);
		Tools.button("stopusersurebtn", "users", driver);
		Thread.sleep(1000);
	}

}
