package so.sao.integration.common;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import so.sao.integration.util.Browsers;
import so.sao.integration.util.TestngRetryListener;
import so.sao.integration.util.Tools;
@Listeners({ TestngRetryListener.class })
public class BaseTest {
	// 启动浏览器
	public WebDriver driver;

	@BeforeClass
	public void loadDriver() {
		Browsers browser = new Browsers('c');
		driver = browser.driver;
	}
	@BeforeClass
	public void conect() throws ClassNotFoundException, SQLException{
		Tools.connection();
	}

	@BeforeClass
	public void login() throws IOException, InterruptedException, SQLException {
		driver.get("http://top-stable.sao.so");
		driver.manage().window().maximize();
		Tools.input("username", "login", "hy@sina.com", driver);
		Tools.input("password", "login", "123qwe", driver);
		Tools.input("checksum", "login", "PPPP", driver);
		Tools.button("loginbutton", "login",driver);
		Thread.sleep(3000);
	}


	// 关闭浏览器
	@AfterClass
	public void closebrowser() throws Exception {
		driver.close();

	}
	@AfterClass
	public void closedb() throws ClassNotFoundException, SQLException{
		Tools.shutdown();

	}

}
