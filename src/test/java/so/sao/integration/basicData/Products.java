package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class Products extends BaseTest {
	private String test1="PC"+Tools.getRandomString(6);
	@Test(priority=2)//新建一个产品
	public void newProduct() throws SQLException, InterruptedException, IOException{
		driver.navigate().refresh();
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(Tools.getelement("basedata", "products", driver)).build().perform();//悬浮在基础数据
		Thread.sleep(2000);
		Tools.button("productmanage", "products", driver);
		Thread.sleep(5000);
		Tools.button("addproduct", "products", driver);
		Tools.input("productcoding", "products", test1, driver);
		Tools.input("productname", "products", "PN"+Tools.getRandomString(6), driver);
		Tools.input("namehelp", "products", Tools.getRandomString(6), driver);
		Tools.button("brand", "products", driver);
		Tools.button("choicebrand", "products", driver);
		Tools.input("saleprice", "products", "10", driver);
		Tools.input("spec", "products", "10", driver);
		Tools.button("unitchoice", "products", driver);
		Tools.button("unit", "products", driver);
		Thread.sleep(2000);
		Tools.input("material", "products", "原料", driver);
		Tools.button("classification", "products", driver);
		Tools.button("wine", "products", driver);
		Thread.sleep(2000);
		Tools.button("picturemanage", "products", driver);		
		Thread.sleep(2000);
		//action.moveToElement(Tools.getelement("part1", "products", driver)).build().perform();//鼠标悬浮在part1
		//Thread.sleep(2000);
		Tools.button("part1", "products", driver);
		//Tools.button("uploadpicture", "products", driver);
		//driver.findElement(By.cssSelector("div.ivu-upload-select>i.ivu-icon-ios-upload-outline")).click();
		//Tools.JavaScriptClick(Tools.getelement("uploadpicture", "products", driver), driver);
		Thread.sleep(2000);
		Runtime.getRuntime().exec("D:/chromeuploadexe/chromeproductpicture.exe");
		Thread.sleep(20000);
		Tools.button("uploadpicturesurebtn", "products", driver);
		Thread.sleep(2000);
		driver.switchTo().frame("ueditor_0");//切换到富文本
		driver.findElement(By.tagName("body")).sendKeys("这是产品介绍，产品图片上传成功");//在body中输入内容
		Thread.sleep(1000);
		driver.switchTo().defaultContent();//切回来
		Tools.button("preserve", "products", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	
	@Test(priority=3)//搜索产品
	public void searchProduct() throws InterruptedException, SQLException{
		driver.navigate().refresh();
		Thread.sleep(2000);
		Tools.input("searchinput", "products", test1, driver);
		Tools.button("search", "products", driver);
		Thread.sleep(2000);
		String strNum = Tools.getelement("producttotal", "products", driver).getText().replaceAll("\\s", "");
		int pNum = Integer.valueOf(strNum.substring(1,2));
		Assert.assertEquals(pNum==1, true);
	}
	
	@Test(priority=4)//修改产品
	public void alterProduct() throws SQLException, InterruptedException{
		Tools.button("productalter", "products", driver);
		Thread.sleep(2000);
		Tools.input("productname", "products", "PN"+Tools.getRandomString(6), driver);
		Tools.input("namehelp", "products", Tools.getRandomString(5), driver);
		Tools.input("saleprice", "products", "5", driver);
		Tools.input("spec", "products", "5", driver);
		Tools.input("material", "products", "原料2", driver);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); //下拉到页面底部
		Thread.sleep(2000);
		driver.switchTo().frame("ueditor_0");//切换到富文本
		driver.findElement(By.tagName("body")).sendKeys("产品介绍修改产品介绍修改产品介绍修改");//在body中输入内容
		Thread.sleep(1000);
		driver.switchTo().defaultContent();//切回来
		Tools.button("productaltersurebtn", "products", driver);
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("成功"));
	}
	@Test(priority=5)//禁用和启用产品
	public void stopAndawakenProduct() throws SQLException, InterruptedException{
		driver.navigate().refresh();
		Thread.sleep(2000);
		Tools.button("stopandawaken", "products", driver);
		Tools.waitForElementPresent("stopandawakensurebtn", "products", driver);
		Tools.button("stopandawakensurebtn", "products", driver);
		Thread.sleep(1000);
		String test1=Tools.getelement("stopandawaken", "products", driver).getText();
		Assert.assertTrue(test1.contains("启用"));
		Tools.button("stopandawaken", "factories", driver);
		Tools.waitForElementPresent("stopandawakensurebtn", "products", driver);
		Tools.button("stopandawakensurebtn", "products", driver);
		Thread.sleep(1000);
		String test2=Tools.getelement("stopandawaken", "products", driver).getText();
		Assert.assertTrue(test2.contains("禁用"));
	} 
	
	@Test(priority=6)//删除一个产品
	public void delProduct() throws SQLException, InterruptedException{
		Tools.button("Pdelete", "products", driver);
		Thread.sleep(2000);
		//Tools.waitForElementPresent("deleteproductmakesureBtn", "products", driver);
		Tools.button("deleteproductmakesureBtn", "products", driver);
		Thread.sleep(2000);
		String test2=Tools.getelement("testelement", "products", driver).getText();
		System.out.println(test2);
		Assert.assertEquals(test1!=test2, true);	
	}
	
	@Test(priority=7)//导入产品
	public void leadinProduct() throws SQLException, InterruptedException, IOException{
		Tools.button("leadin", "products", driver);
		Thread.sleep(2000);
		Tools.button("uploadchoice", "products", driver);
		Thread.sleep(2000);
		Runtime.getRuntime().exec("D:/chromeuploadexe/chromeproduct.exe");
		Thread.sleep(20000);
		Tools.button("uploadsurebtn", "products", driver);
		Thread.sleep(5000);
	}
}
