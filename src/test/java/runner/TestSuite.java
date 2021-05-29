package runner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSuite {
    WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
//        Run chromedriver
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

    }

    @Test
    public void login_thanh_cong(){
        login("standard_user","secret_sauce");
        //verify shopping cart is displayed
        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        Assert.assertTrue(shoppingCart.isDisplayed());
    }

    @Test
    public void login_voi_username_sai(){
        login("wrong_user","secret_sauce");
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals("Epic sadface: User name khong hop le"
                ,errorLabel.getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    public void login(String username, String password){
        WebElement txtUsername = driver.findElement(By.id("user-name"));
        WebElement txtPassword = driver.findElement(By.id("password"));
        WebElement btnLogin = driver.findElement(By.id("login-button"));
        // Thao tác với các elements
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
}
