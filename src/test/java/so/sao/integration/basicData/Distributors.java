package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Distributors extends BaseTest{
	@Test(priority=2)//新建一个经销商
	public void newLeader() throws SQLException, InterruptedException{
		Tools.button("basedata", "distributors", driver);
		Thread.sleep(2000);
		Tools.button("leadermanage", "distributors", driver);
		Thread.sleep(2000);
		Tools.button("addleader", "distributors", driver);
		Tools.input("leadercode", "distributors", "LC"+Tools.getRandomString(7), driver);
		Tools.input("leadername", "distributors", "LN"+Tools.getRandomString(7), driver);
		Tools.input("bussinessNum", "distributors", "BN"+Tools.getRandomString(13), driver);
		Tools.input("leadercontact", "distributors", "contact", driver);
		Tools.input("leaderphoneNum", "distributors", "13812345678", driver);
		Thread.sleep(2000);
		Tools.button("choiceArea", "distributors", driver);
		Tools.button("beijing", "distributors", driver);
		Thread.sleep(2000);
		Tools.button("addleadersurebtn", "distributors", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("添加成功"));
	}
	
	@Test(priority=3)//删除一个经销商
	public void delLeader() throws SQLException, InterruptedException{
		Tools.button("deleteleader", "distributors", driver);
		Thread.sleep(2000);
		Tools.button("deleteleadersurebtn", "distributors", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));	
	}
	
	@Test(priority=4)//导入一批经销商
	public void loadin() throws SQLException, InterruptedException, IOException{
		Tools.button("leaderloadinbtn", "distributors", driver);
		Thread.sleep(2000);
		Tools.button("uploadchoice", "distributors", driver);
		Runtime.getRuntime().exec("D:/uploadrun/franchiser.exe");
		Thread.sleep(30000);
		Tools.button("uploadsurebtn", "distributors", driver);
		Thread.sleep(5000);
	}
}
