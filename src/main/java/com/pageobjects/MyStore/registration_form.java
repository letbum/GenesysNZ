package com.pageobjects.MyStore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class registration_form {
    WebDriver driver;

    public registration_form(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getGenderMale() {
        return driver.findElement(By.id("id_gender1"));
    }

    public WebElement getFirstName() {
        return driver.findElement(By.id("customer_firstname"));
    }

    public WebElement getLastName() {
        return driver.findElement(By.id("customer_lastname"));
    }

    public WebElement getPasswordField() {
        return driver.findElement(By.id("passwd"));
    }

    public WebElement getBirthDay() {
        return driver.findElement(By.id("days"));
    }

    public WebElement getBirthMonth() {
        return driver.findElement(By.id("months"));
    }

    public WebElement getBirthYear() {
        return driver.findElement(By.id("years"));
    }

    public WebElement getAddressFirstName() {
        return driver.findElement(By.id("firstname"));
    }

    public WebElement getAddressLastName() {
        return driver.findElement(By.id("lastname"));
    }

    public WebElement getAddressCompany() {
        return driver.findElement(By.id("company"));
    }

    public WebElement getAddressLineOne() {
        return driver.findElement(By.id("address1"));
    }

    public WebElement getAddressLineTwo() {
        return driver.findElement(By.id("address2"));
    }

    public WebElement getAddressCity() {
        return driver.findElement(By.id("city"));
    }

    public WebElement getAddressState() {
        return driver.findElement(By.id("id_state"));
    }

    public WebElement getAddressPostalCode() {
        return driver.findElement(By.id("postcode"));
    }

    public WebElement getAddressCountry() {
        return driver.findElement(By.id("id_country"));
    }

    public WebElement getAddressAdditionalInformation() {
        return driver.findElement(By.id("other"));
    }

    public WebElement getAddressMobilePhone() {
        return driver.findElement(By.id("phone_mobile"));
    }

    public WebElement getRegisterButton() {
        return driver.findElement(By.id("submitAccount"));
    }

    public WebElement getMandatoryFieldsAlert() {
        return driver.findElement(By.cssSelector("[class*='alert-danger']"));
    }
}
