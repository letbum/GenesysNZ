package com.pageobjects.MyStore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class home_page {
    WebDriver driver;

    public home_page(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLoginButton() {
        return driver.findElement(By.className("login"));
    }

    public WebElement getEmailField() {
        return driver.findElement(By.id("email_create"));
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.id("SubmitCreate"));
    }

    public WebElement getFooter() {
        return driver.findElement(By.id("footer"));
    }

    public void clickLogin() {
        this.getLoginButton().click();
    }

    public void clickSubmit() {
        this.getSubmitButton().click();
    }

    public void setEmailField(String email_text) {
        this.getEmailField().sendKeys(email_text);
    }
}
