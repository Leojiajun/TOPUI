package so.sao.integration.common;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import so.sao.integration.util.Browsers;
import so.sao.integration.util.To;


public class test {
	public WebDriver driver;
	@BeforeClass
	public void loadDriver() {
		Browsers browser = new Browsers('f');
		driver = browser.driver;
	}
	@BeforeClass
	public void conect() throws ClassNotFoundException, SQLException{
		To.connection();
	}
	
	@Test
	public test() throws SQLException{
		driver.get("http://top-stable.sao.so");
		driver.manage().window().maximize();
		To.input("username", "login", "hy@sina.com", driver);
		System.out.println("******qqq");
		To.input("password", "login", "123qwe", driver);
		To.input("checksum", "login", "PPPP", driver);
		To.button("loginbutton", "login",driver);
	}
}
