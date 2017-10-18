package so.sao.integration.util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browsers {

	public static  WebDriver driver =null;
	private String projectpath = System.getProperty("user.dir");
 
	
	
	public Browsers(char browserstype){
		switch( browserstype){
			case 'f':
				driver = new FirefoxDriver();
				break;
			case 'c':
				System.setProperty("webdriver.chrome.driver","D:/soft/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setBinary("C:\\Users\\user\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
				driver = new ChromeDriver(options);
				break;
			case 'i':
				System.setProperty("webdriver.ie.driver", projectpath+"/Tools/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				break;						
			}			
		}
}