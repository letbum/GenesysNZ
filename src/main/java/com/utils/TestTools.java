package com.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class TestTools {
    WebDriver driver;

    public TestTools(WebDriver driver) {
        this.driver = driver;
    }

    public void wait_for_element(WebElement web_element) {
        WebDriverWait driver_wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
        driver_wait.until(ExpectedConditions.visibilityOf(web_element));
    }

    public void select_option_by_value(WebElement option_locator, String option_value) {
        Select select_web_element = new Select(option_locator);
        select_web_element.selectByValue(option_value);
    }

    public String generate_email(String email_text) {
        Random r = new Random();
        int low = 1;
        int high = 1000;
        int result = r.nextInt(high-low) + low;
        return "%s%s@test.com".formatted(email_text, result);
    }

    public void scrollByPixel(Integer pixel_count) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,%s, {behavior: 'smooth'})".formatted(pixel_count));
    }

    public void scrollByPixelUntilElement(Integer pixel_count, WebElement web_element) {
        int location = web_element.getLocation().getY();
        int scroll_count = location/pixel_count;
        for (int i = 0; i < scroll_count; i++) {
            this.scrollByPixel(pixel_count);
        }
    }
}
