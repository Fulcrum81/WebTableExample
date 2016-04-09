import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by Вадим on 08.04.2016.
 */
public class TableTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

    @Test
    public void tableTest(){
        Table firstTable = new Table(driver.findElement(By.cssSelector("#table1>tbody")));
        assertEquals("Smith", firstTable.getCell(1, 1).getText());
        assertEquals("John", firstTable.getCell(1, 2).getText());

        Table secondTable = new Table(driver.findElement(By.cssSelector("#table2>tbody")));
        assertEquals("Smith", secondTable.getCell(1, 1).getText());
        assertEquals("John", secondTable.getCell(1, 2).getText());
    }

    @Test
    public void editTest() throws InterruptedException {
        Table firstTable = new Table(driver.findElement(By.cssSelector("#table1>tbody")));
        firstTable.getCellLink(1, 6).click();
        assertTrue(driver.getCurrentUrl().endsWith("#edit"));
        firstTable.getCellLink(1, 6, 2).click();
        assertTrue(driver.getCurrentUrl().endsWith("#delete"));
    }

    @Test
    public void cellByNameTest() {
        Table secondTable = new Table(driver.findElement(By.cssSelector("#table2>tbody")));
        assertEquals("Smith", secondTable.getCell(1, "Last name").getText());
        assertEquals("John", secondTable.getCell(1, "First name").getText());
    }
}
