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
        WebElement linkByPartialText = driver.findElement(By.partialLinkText("Create"));
        linkByPartialText.click();



        // Enters first name, last name and email address
        WebElement element=driver.findElement(By.xpath("//input[@name='firstname']"));
        element.sendKeys("John");
        element=driver.findElement(By.xpath("//input[@name='lastname']"));
        element.sendKeys("Doe");
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("johndoe@gmail.com");

        // Enters a password
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");
        element=driver.findElement(By.xpath("//input[@name='confirmPassword']"));
        element.sendKeys("Password1!");

        // Clicks the Rider radio button
        element=driver.findElement(By.xpath("//input[@id='Admin']"));
        element.click();



        // 500 ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}



        // Create and answer security questions
        element=driver.findElement(By.xpath("//input[@name='answerOne']"));
        element.sendKeys("chicago");

        element=driver.findElement(By.xpath("//input[@name='answerTwo']"));
        element.sendKeys("steak");

        element=driver.findElement(By.xpath("//input[@name='answerThree']"));
        element.sendKeys("dog");


        // 500ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Click submit button
        driver.findElement(By.xpath("//button[@name='Register']")).click();


        // Enters login information
        element=driver.findElement(By.xpath("//input[@name='email']"));
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
        driver.findElement(By.xpath("//button[@value='addveh']")).click();
        element=driver.findElement(By.xpath("//select[@id='vehicleType']"));
        element.sendKeys("Car");
        driver.findElement(By.xpath("//button[@value='addveh']")).click();



        // Adds a route
        driver.findElement(By.xpath("//button[@value='add']")).click();
        driver.findElement(By.xpath("//input[@name='startlocation']")).sendKeys("Coralville");
        driver.findElement(By.xpath("//input[@name='dropofflocation']")).sendKeys("Iowa City");
        driver.findElement(By.xpath("//input[@id='rate']")).clear();
        driver.findElement(By.xpath("//input[@id='rate']")).sendKeys("3.89");
        driver.findElement(By.xpath("//input[@id='savings']")).clear();
        driver.findElement(By.xpath("//input[@id='savings']")).sendKeys("1.22");
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
        linkByPartialText = driver.findElement(By.partialLinkText("Sign"));
        linkByPartialText.click();


        // 1000ms delay
        try {
            // wait 500 millis and after that run command
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}


        // Clicks the Create an Account link
        linkByPartialText = driver.findElement(By.partialLinkText("Create"));
        linkByPartialText.click();

        // Creates a rider account

        // Enters first name, last name and email address
        element=driver.findElement(By.xpath("//input[@name='firstname']"));
        element.sendKeys("John");
        element=driver.findElement(By.xpath("//input[@name='lastname']"));
        element.sendKeys("Smith");
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("driver@gmail.com");

        // Enters a password
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");
        element=driver.findElement(By.xpath("//input[@name='confirmPassword']"));
        element.sendKeys("Password1!");

        // Clicks the Rider radio button
        element=driver.findElement(By.xpath("//input[@id='Driver']"));
        element.click();



        // 500 ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}



        // Create and answer security questions
        element=driver.findElement(By.xpath("//input[@name='answerOne']"));
        element.sendKeys("chicago");

        element=driver.findElement(By.xpath("//input[@name='answerTwo']"));
        element.sendKeys("steak");

        element=driver.findElement(By.xpath("//input[@name='answerThree']"));
        element.sendKeys("dog");


        // 500ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Click submit button
        driver.findElement(By.xpath("//button[@name='Register']")).click();



        //Sign in using rider account
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("driver@gmail.com");
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");
        driver.findElement(By.xpath("//button[@name='btnLogin']")).click();

        // Select a random route
        List<WebElement> driverRoutes = driver.findElements(By.xpath("//input[@id='routes']"));
        driverRoutes.get(rnd.nextInt(driverRoutes.size())).click();
        driverRoutes.get(rnd.nextInt(driverRoutes.size())).click();

        driver.findElement(By.xpath("//button[@id='signup']")).click();


        List<WebElement> driverMyRoutes = driver.findElements(By.xpath("//input[@id='myRoutes']"));
        driverMyRoutes.get(rnd.nextInt(driverMyRoutes.size())).click();

        driver.findElement(By.xpath("//button[@id='leaveBtn']")).click();


        // 1000ms delay
        try {
            // wait 500 millis and after that run command
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Logout of Driver
        linkByPartialText = driver.findElement(By.partialLinkText("Sign"));
        linkByPartialText.click();


        // Clicks the Create an Account link
        linkByPartialText = driver.findElement(By.partialLinkText("Create"));
        linkByPartialText.click();

        // Creates a rider account

        // Enters first name, last name and email address
        element=driver.findElement(By.xpath("//input[@name='firstname']"));
        element.sendKeys("Jane");
        element=driver.findElement(By.xpath("//input[@name='lastname']"));
        element.sendKeys("Doe");
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("rider@gmail.com");

        // Enters a password
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");
        element=driver.findElement(By.xpath("//input[@name='confirmPassword']"));
        element.sendKeys("Password1!");

        // Clicks the Rider radio button
        element=driver.findElement(By.xpath("//input[@id='Rider']"));
        element.click();



        // 500 ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}



        // Create and answer security questions
        element=driver.findElement(By.xpath("//input[@name='answerOne']"));
        element.sendKeys("chicago");

        element=driver.findElement(By.xpath("//input[@name='answerTwo']"));
        element.sendKeys("steak");

        element=driver.findElement(By.xpath("//input[@name='answerThree']"));
        element.sendKeys("dog");


        // 500ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Click submit button
        driver.findElement(By.xpath("//button[@name='Register']")).click();


        //Sign in using rider account
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("rider@gmail.com");
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");
        driver.findElement(By.xpath("//button[@name='btnLogin']")).click();


        // Select a random route
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@id='routes']"));
        checkboxes.get(rnd.nextInt(checkboxes.size())).click();

        // 1000ms delay
        try {
            // wait 500 millis and after that run command
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Add a random route
        driver.findElement(By.xpath("//button[@id='signup']")).click();

        // Logout of Driver
        linkByPartialText = driver.findElement(By.partialLinkText("Sign"));
        linkByPartialText.click();


        //Sign in using rider account
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("rider@gmail.com");
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password1!");
        driver.findElement(By.xpath("//button[@name='btnLogin']")).click();

        // Select a random route
        List<WebElement> riderRoutes = driver.findElements(By.xpath("//input[@id='routes']"));
        riderRoutes.get(rnd.nextInt(riderRoutes.size())).click();

        // 1000ms delay
        try {
            // wait 500 millis and after that run command
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Add a random route
        driver.findElement(By.xpath("//button[@id='signup']")).click();

        // Logout of Rider
        linkByPartialText = driver.findElement(By.partialLinkText("Sign"));
        linkByPartialText.click();


        // 1000ms delay
        try {
            // wait 500 millis and after that run command
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}

        // Clicks the change password link
        linkByPartialText = driver.findElement(By.partialLinkText("Change"));
        linkByPartialText.click();

        // Enter rider email for password recovery
        element=driver.findElement(By.xpath("//input[@id='email']"));
        element.sendKeys("rider@gmail.com");

        // Recover by questions
        driver.findElement(By.xpath("//button[@value='questions']")).click();

        // 1000ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}


        // Answer recovery questions
        element=driver.findElement(By.xpath("//input[@id='answerOne']"));
        element.sendKeys("chicago");
        element=driver.findElement(By.xpath("//input[@id='answerTwo']"));
        element.sendKeys("steak");
        element=driver.findElement(By.xpath("//input[@id='answerThree']"));
        element.sendKeys("dog");


        // Click recover button
        driver.findElement(By.xpath("//button[@id='recover']")).click();

        // Enters a new password
        element=driver.findElement(By.xpath("//input[@id='password']"));
        element.sendKeys("Password2!");
        element=driver.findElement(By.xpath("//input[@id='confirmPassword']"));
        element.sendKeys("Password2!");
        driver.findElement(By.xpath("//button[@id='reset']")).click();

        // Login with new password information for the rider account
        element=driver.findElement(By.xpath("//input[@name='email']"));
        element.sendKeys("rider@gmail.com");
        element=driver.findElement(By.xpath("//input[@name='password']"));
        element.sendKeys("Password2!");
        driver.findElement(By.xpath("//button[@name='btnLogin']")).click();

        // 1000ms delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}


        // Quits the selenium webdriver
        driver.quit();

    }

}