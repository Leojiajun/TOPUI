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
		Thread.sleep(1000);
		Tools.button("addusersurebtn", "users", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("保存成功"));
	}
	
	//@Test(priority=3)//修改用户
	public void alterUser(){
		
	}
}
