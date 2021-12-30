package com.pageobjects.MyStore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class user_page {
    WebDriver driver;

    public user_page(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPersonalInformation() {
        return driver.findElement(By.cssSelector("[title='Information']"));
    }

    public WebElement getFirstName() {
        return driver.findElement(By.id("firstname"));
    }

    public WebElement getSignOut() {
        return  driver.findElement(By.cssSelector("[title='Log me out']"));
    }
}
