import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

public class TestSelenium {
    public static void main(String[] args){
        SafariDriver driver=new SafariDriver();
        driver.get("http://localhost:8080");
        WebElement element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("admin@gmail.com");
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");
        driver.findElement(By.xpath("//button[@name='btnLogin']")).click();
    }
}