package so.sao.integration.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

public class To {
	public static Connection conn=null;
	 public static ResultSet rs=null;
	 public static int  timeout=5;
	 public static WebDriver alldriver;
	//链接数据库
			public static void connection()
				      throws ClassNotFoundException, SQLException {
				    String elementpath = null;
				    // 设定mysql驱动
				    Class.forName("com.mysql.jdbc.Driver");
				    // 建立数据库连接
				    conn = DriverManager.getConnection(
				        "jdbc:mysql://192.168.10.30:3306/TOPTEST", "root", "123456");
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
	
	public static void input(String keyname,String tablename,String value,WebDriver driver) throws SQLException {
		try{
		WebElement input=driver.findElement(By.xpath(getdbData(keyname, tablename)));
		input.clear();
		input.sendKeys(value);
		}catch(NoSuchElementException e){
			WebElement input=driver.findElement(By.cssSelector(getdbData(keyname, tablename)));
			input.clear();
			input.sendKeys(value);
		}
	}
	
	public static void button(String keyname,String tablename,WebDriver driver) throws SQLException {
		try{
		WebElement button=driver.findElement(By.xpath(getdbData(keyname, tablename)));
		button.click();
		}catch(NoSuchElementException e){
			WebElement button=driver.findElement(By.cssSelector(getdbData(keyname, tablename)));
			button.click();
		}
		
	}
}
