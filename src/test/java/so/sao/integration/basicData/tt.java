package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import so.sao.integration.common.BaseTest;
import so.sao.integration.util.Tools;

public class tt extends BaseTest{
	@Test
	public void newProduct() throws SQLException, InterruptedException, IOException{
		driver.navigate().refresh();
		Tools.button("basedata", "products", driver);
		Thread.sleep(2000);
		Tools.button("productmanage", "products", driver);
		Thread.sleep(2000);
//		Actions action = new Actions(driver);
//		action.moveToElement(Tools.getelement("basedata", "products", driver)).build().perform();
		Tools.button("addproduct", "products", driver);
		Tools.button("picturemanage", "products", driver);		
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.moveToElement(Tools.getelement("part1", "products", driver)).build().perform();
		Thread.sleep(3000);
		Tools.button("uploadpicture", "products", driver);
	}	
}
