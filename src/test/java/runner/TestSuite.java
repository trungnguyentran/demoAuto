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
    public void tc_01_login_thanh_cong(){
        login("standard_user","secret_sauce");
        //verify shopping cart is displayed
        WebElement shoppingCart = driver.findElement(By.id("shopping_cart_container"));
        Assert.assertTrue(shoppingCart.isDisplayed());
    }

    @Test
    public void tc_02_login_voi_username_sai(){
        login("wrong_user","secret_sauce");
        compareLabelError("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void tc_03_login_user_lock_out() {
        login("locked_out_user", "secret_sauce");
        compareLabelError("Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void tc_04_login_user_problem() {
        login("problem_user", "secret_sauce");
        WebElement elePeekImg = driver.findElement(By.xpath("//div[@class='peek']"));
        Assert.assertTrue(elePeekImg.isDisplayed());
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

    public void compareLabelError(String textExpected) {
        WebElement errorLabel = driver.findElement(By.xpath("//div[@class='login-box']//h3"));
        Assert.assertEquals(textExpected
                ,errorLabel.getText());
    }
}
