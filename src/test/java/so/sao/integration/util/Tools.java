package so.sao.integration.util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Tools {
	public static Connection conn=null;
	 public static ResultSet rs=null;
	 public static int  timeout=5;
	 public static WebDriver alldriver;

	//文本框输入内容
	public static void input(By by,String value,WebDriver driver){
		WebElement input=driver.findElement(by);
		input.sendKeys(Keys.BACK_SPACE);
		input.clear();
		input.sendKeys(value);
	}
	
	//文本框输入内容
	public static void input(String keyname,String tablename,String value,WebDriver driver) throws SQLException {
		try{
			input(By.xpath(getdbData(keyname, tablename)),value,driver);
		}catch(NoSuchElementException e){
			input(By.cssSelector(getdbData(keyname, tablename)),value,driver);
		}
	
	}
		
	
	//按钮点击
	public static void button(By by,WebDriver driver){
		WebElement button=driver.findElement(by);
		button.click();
		  }
	
	//按钮点击
	public static void button(String keyname,String tablename,WebDriver driver) throws SQLException {
		try{
			button(By.xpath(getdbData(keyname, tablename)),driver);
		}catch(NoSuchElementException e){
			button(By.cssSelector(getdbData(keyname, tablename)),driver);
		}
	}
	
	public static WebElement getelement(String keyname,String tablename,WebDriver driver) throws SQLException{
		try{
			return driver.findElement(By.xpath(getdbData(keyname, tablename)));
		}catch(NoSuchElementException e){
			return driver.findElement(By.cssSelector(getdbData(keyname, tablename)));
		}
		
		
	}
	
	//当前时间加1天
		public static String getNetDay(Date date){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
			date=calendar.getTime();
			return df.format(date);
			}
		//当前时间
				public static String getToday(){
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					return df.format(new Date());
					}
		
		//随机生成字符串
		public static String getRandomString(int length) {//length表示生成字符串的长度
			String base = "abcdefghijklmnopqrstuvwxyz0123456789";
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
			return sb.toString();
		}
		
//链接数据库
		public static void connection()
			      throws ClassNotFoundException, SQLException {
			    // 设定mysql驱动
			    Class.forName("com.mysql.jdbc.Driver");
			    // 建立数据库连接
			    conn = DriverManager.getConnection(
			        "jdbc:mysql://192.168.6.9:3306/TOPTEST", "root", "123456");
			    // 判断数据库连接是否成功
			    if (!conn.isClosed()) {
			      System.out.println("数据库连接成功");
			    } else {
			      System.out.println("数据库连接失败");
			    }
		}
			   
//关闭链接
		public static void shutdown()
			      throws ClassNotFoundException, SQLException {
			 // 关闭数据集
		    rs.close();
		    System.out.println("关闭数据集");
			
		    // 关闭连接
		    conn.close();
		    System.out.println("关闭链接");
			   
		}
//从表中获取数据
		public static String getdbData(String localtername,String tablename) throws SQLException{
			String elementpath=null;
		 // 创建Statement对象可以用对应的方法executeQuery(sql语句)获取测试数据
	    Statement sta = conn.createStatement();
	    // 创建一个结果集存放数据库执行完sql的数据
	    rs = sta.executeQuery("select * from " + tablename
	        + " where keyname = '" + localtername + "';");

	    while (rs.next()) {
	        elementpath = rs.getString("path");// 获取“XpathOrCss”字段的值
	    }
	   
	    return elementpath;
	  
		}

		//等待元素出现
	public static void waitForElementPresent(String keyname, String tablename,WebDriver driver) throws SQLException{
		try{
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(getdbData(keyname, tablename))));
		}catch(TimeoutException e){
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(getdbData(keyname, tablename))));
		}	
	}
	
	//等待元素可操作
	public static void waitForElementIsEnable(String keyname, String tablename,WebDriver driver) throws SQLException{
		try{
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.elementToBeClickable(By.xpath(getdbData(keyname, tablename))));
		}catch(TimeoutException e){
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(getdbData(keyname, tablename))));
		}	
	}
	
	public static void JavaScriptClick(WebElement element,WebDriver driver){
		if (element.isEnabled() && element.isDisplayed()){
			((JavascriptExecutor) driver ).executeScript("arguments[0].click();",element);
		}else{
			System.out.println("页面上的元素无法进行单击操作");		
		}
	}
}