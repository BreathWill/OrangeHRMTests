import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class LoginTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriver: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSuccessfulLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertTrue(driver.getTitle().contains("OrangeHRM"));
    }

    @Test
    public void testIncorrectPasswordLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("wrongpassword");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-alert-content-text")));
        assertTrue(driver.findElement(By.cssSelector(".oxd-alert-content-text")).getText().contains("Invalid credentials"));
    }

    @Test
    public void testNavigateToPIM() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        assertTrue(driver.getCurrentUrl().contains("pim"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import java.time.Duration;
//import static org.junit.Assert.assertTrue;
//
//public class LoginTests {
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//    @Before
//    public void setUp() {
//        try {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        } catch (Exception e) {
//            System.err.println("Failed to initialize WebDriver: " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    public void testSuccessfulLogin() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
//        driver.findElement(By.name("username")).sendKeys("Admin");
//        driver.findElement(By.name("password")).sendKeys("admin123");
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//        assertTrue(driver.getTitle().contains("OrangeHRM"));
//    }
//
//    @Test
//    public void testIncorrectPasswordLogin() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
//        driver.findElement(By.name("username")).sendKeys("Admin");
//        driver.findElement(By.name("password")).sendKeys("wrongpassword");
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-alert-content-text")));
//        assertTrue(driver.findElement(By.cssSelector(".oxd-alert-content-text")).getText().contains("Invalid credentials"));
//    }
//
//    @Test
//    public void testNavigateToPIM() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
//        driver.findElement(By.name("username")).sendKeys("Admin");
//        driver.findElement(By.name("password")).sendKeys("admin123");
//        driver.findElement(By.cssSelector("button[type='submit']")).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
//        driver.findElement(By.xpath("//span[text()='PIM']")).click();
//        assertTrue(driver.getCurrentUrl().contains("pim"));
//    }
//
//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}