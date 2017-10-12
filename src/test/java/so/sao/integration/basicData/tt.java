package so.sao.integration.basicData;

import java.io.IOException;
import java.sql.SQLException;

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
		
		
	}	
}
