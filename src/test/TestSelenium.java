import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Random;


public class TestSelenium {
    public static void main(String[] args){
        // Intializes the driver and goes to the website
        ChromeDriver driver=new ChromeDriver();
        driver.get("http://localhost:8080");

        try {
            // wait 500 millis and after that run command
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Clicks the Create an Account link
//        WebElement linkByPartialText = driver.findElement(By.partialLinkText("Create"));
//        linkByPartialText.click();
//
//
//
//        // Enters first name, last name and email address
//        WebElement element=driver.findElement(By.xpath("//input[@name='firstname']"));
//        element.sendKeys("John");
//        element=driver.findElement(By.xpath("//input[@name='lastname']"));
//        element.sendKeys("Doe");
//        element=driver.findElement(By.xpath("//input[@name='email']"));
//        element.sendKeys("johndoe@gmail.com");
//
//        // Enters a password
//        element=driver.findElement(By.xpath("//input[@name='password']"));
//        element.sendKeys("Password1!");
//        element=driver.findElement(By.xpath("//input[@name='confirmPassword']"));
//        element.sendKeys("Password1!");
//
//        // Clicks the Rider radio button
//        element=driver.findElement(By.xpath("//input[@id='Admin']"));
//        element.click();
//
//
//
//        // 500 ms delay
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {}
//
//
//
//        // Create and answer security questions
//        element=driver.findElement(By.xpath("//input[@name='questionOne']"));
//        element.sendKeys("Do you have a dog?");
//        element=driver.findElement(By.xpath("//input[@name='answerOne']"));
//        element.sendKeys("yes");
//
//        element=driver.findElement(By.xpath("//input[@name='questionTwo']"));
//        element.sendKeys("Do you have a cat?");
//        element=driver.findElement(By.xpath("//input[@name='answerTwo']"));
//        element.sendKeys("no");
//
//        element=driver.findElement(By.xpath("//input[@name='questionThree']"));
//        element.sendKeys("Do you have a car?");
//        element=driver.findElement(By.xpath("//input[@name='answerThree']"));
//        element.sendKeys("yes");
//
//
//        // 500ms delay
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {}
//
//        // Click submit button
//        driver.findElement(By.xpath("//button[@name='Register']")).click();


        // Clicks the change password link
//        WebElement linkByPartialText = driver.findElement(By.partialLinkText("Change"));
//        linkByPartialText.click();

        // Enters login information
        WebElement element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("johndoe@gmail.com");
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");

        // 500ms delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {}

        // Clicks login button
        driver.findElement(By.xpath("//button[@name='btnLogin']")).click();

        // Adds a vehicle of type car
        element=driver.findElement(By.xpath("//select[@id='vehicleType']"));
        element.sendKeys("Car");
        driver.findElement(By.xpath("//button[@value='add']")).click();


        // Adds a route
        driver.findElement(By.xpath("//button[@name='addRoute']")).click();
        driver.findElement(By.xpath("//input[@name='startlocation']")).sendKeys("Coralville");
        driver.findElement(By.xpath("//input[@name='dropofflocation']")).sendKeys("Iowa City");
        driver.findElement(By.xpath("//input[@id='rate']")).clear();
        driver.findElement(By.xpath("//input[@id='rate']")).sendKeys("3.89");
        List<WebElement> els = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (int i = 0; i < els.size(); i++) {
            if ((!els.get(i).isSelected()) && (i%2 == 0) ) {
                els.get(i).click();
            }
        }
        driver.findElement(By.xpath("//button[@name='add']")).click();


        // Selects a vehicle
        List<WebElement> radios = driver.findElements(By.xpath("//input[@id='vehicleType']"));
        Random rnd = new Random();
        radios.get(rnd.nextInt(radios.size())).click();
        driver.findElement(By.xpath("//button[@name='update']")).click();


        // Logout of admin
        WebElement linkByPartialText = driver.findElement(By.partialLinkText("Sign"));
        linkByPartialText.click();


        // 1000ms delay
        try {
            // wait 500 millis and after that run command
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}






        // Clicks the Create an Account link
//        WebElement linkByPartialText = driver.findElement(By.partialLinkText("Create"));
//        linkByPartialText.click();
//
//        // Creates a rider account
//
//        // Enters first name, last name and email address
//        WebElement element=driver.findElement(By.xpath("//input[@name='firstname']"));
//        element.sendKeys("Jane");
//        element=driver.findElement(By.xpath("//input[@name='lastname']"));
//        element.sendKeys("Doe");
//        element=driver.findElement(By.xpath("//input[@name='email']"));
//        element.sendKeys("rider@gmail.com");
//
//        // Enters a password
//        element=driver.findElement(By.xpath("//input[@name='password']"));
//        element.sendKeys("Password1!");
//        element=driver.findElement(By.xpath("//input[@name='confirmPassword']"));
//        element.sendKeys("Password1!");
//
//        // Clicks the Rider radio button
//        element=driver.findElement(By.xpath("//input[@id='Admin']"));
//        element.click();
//
//
//
//        // 500 ms delay
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {}
//
//
//
//        // Create and answer security questions
//        element=driver.findElement(By.xpath("//input[@name='questionOne']"));
//        element.sendKeys("Do you have a dog?");
//        element=driver.findElement(By.xpath("//input[@name='answerOne']"));
//        element.sendKeys("yes");
//
//        element=driver.findElement(By.xpath("//input[@name='questionTwo']"));
//        element.sendKeys("Do you have a cat?");
//        element=driver.findElement(By.xpath("//input[@name='answerTwo']"));
//        element.sendKeys("no");
//
//        element=driver.findElement(By.xpath("//input[@name='questionThree']"));
//        element.sendKeys("Do you have a car?");
//        element=driver.findElement(By.xpath("//input[@name='answerThree']"));
//        element.sendKeys("yes");
//
//
//        // 500ms delay
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {}
//
//        // Click submit button
//        driver.findElement(By.xpath("//button[@name='Register']")).click();


        //Sign in using rider account
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("driver@gmail.com");
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");








//        //Sign in using rider account
//        element=driver.findElement(By.xpath("//input[@name='email']"));
//        element.sendKeys("rider@gmail.com");
//        element=driver.findElement(By.xpath("//input[@name='password']"));
//        element.sendKeys("Password1!");





        // Quits the selenium webdriver
        driver.quit();

    }

}