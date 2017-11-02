package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Company extends BaseTest{
	private String test1=Tools.getRandomString(7);
	@Test(priority=2)//新建企业
	public void newCompany() throws SQLException, InterruptedException, IOException{
		Tools.button("basedata", "company", driver);
		Thread.sleep(2000);
		Tools.button("companymanage", "company", driver);
		Thread.sleep(2000);
		Tools.button("addcompany", "company", driver);	
		Thread.sleep(2000);
		Tools.input("companyname", "company", test1, driver);
		Tools.input("companycode", "company", "a"+Tools.getRandomString(7), driver);
		Tools.input("contactname", "company", "companycontact", driver);
		Tools.input("contactphone", "company", "13822223333", driver);
		Tools.input("contactcard", "company", "320681198942556232", driver);
		Tools.input("licence", "company", "123455432112345", driver);
		Tools.input("productnum", "company", "1", driver);
		Tools.input("usernum", "company", "1", driver);
		Tools.input("terminalnum", "company", "2", driver);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); //下拉到页面底部
		Thread.sleep(2000);
		Tools.button("cardpicture", "company", driver);
		Runtime.getRuntime().exec("D:/chromeuploadexe/chromecompanypicture.exe");
		Thread.sleep(20000);
		Tools.button("keepcompany", "company", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("保存成功"));
	}	
	@Test(priority=3)//审核启用企业
	public void reviewAndawakenCompany() throws SQLException, InterruptedException{	
		Tools.button("review", "company", driver);
		Thread.sleep(1000);
		Tools.button("doadd", "company", driver);
		Thread.sleep(1000);
		Tools.button("awaken", "company", driver);
		Thread.sleep(1000);
		Tools.button("awakencompanysure", "company", driver);
		Thread.sleep(1000);
		String testawaken = Tools.getelement("awaken", "company", driver).getText();
		Assert.assertTrue(testawaken.contains("禁用"));
	}	
	@Test(priority=4)//禁用企业
	public void stopCompany() throws SQLException, InterruptedException{
		Tools.button("awaken", "company", driver);
		Thread.sleep(1000);
		Tools.button("awakencompanysure", "company", driver);
		Thread.sleep(1000);
		String teststop = Tools.getelement("awaken", "company", driver).getText();
		Assert.assertTrue(teststop.contains("启用"));
	}	
		
	@Test(priority=5)//修改一个企业
	public void alterCompany() throws SQLException, InterruptedException{
		Tools.button("altercompany", "company", driver);
		Thread.sleep(2000);
		Tools.input("contactname", "company", "Acompanycontact", driver);
		Tools.input("contactphone", "company", "13822225555", driver);
		Thread.sleep(1000);
		Tools.input("licence", "company", "123455432112346", driver);
		Tools.input("productnum", "company", "2", driver);
		Tools.input("usernum", "company", "2", driver);
		Tools.input("terminalnum", "company", "1", driver);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); //下拉到页面底部
		Thread.sleep(2000);
		Tools.button("altercompanysurebtn", "company", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("修改成功"));
	}
	
	
	@Test(priority=6)//删除一个企业
	public void delCompany() throws SQLException, InterruptedException{
		driver.navigate().refresh();
		Tools.button("basedata", "company", driver);
		Thread.sleep(2000);
		Tools.button("companymanage", "company", driver);
		Thread.sleep(2000);
		Tools.button("deletecompany", "company", driver);
		Tools.waitForElementPresent("delcompanysurebtn", "company",driver);
		//Thread.sleep(2000);
		Tools.button("delcompanysurebtn", "company", driver);
		Thread.sleep(2000);
		String test2=Tools.getelement("testelement", "company", driver).getText();
		Assert.assertEquals(test1!=test2, true);
	}

}
