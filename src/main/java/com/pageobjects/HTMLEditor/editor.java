package com.pageobjects.HTMLEditor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class editor {
    WebDriver driver;

    public editor(WebDriver driver) {
        this.driver = driver;
    }

    public  WebElement getBoldButton() {
        return  driver.findElement(By.id("cke_18"));
    }

    public  WebElement getUnderlinedButton() {
        return  driver.findElement(By.id("cke_20"));
    }

    public WebElement getIframeEditor() {
        return driver.findElement(By.cssSelector("iframe[class='cke_wysiwyg_frame cke_reset']"));
    }

    public WebElement getEditorBody() {
        return driver.findElement(By.xpath("html/body/p"));
    }

    public void switchToIframeEditor() {
        driver.switchTo().frame(this.getIframeEditor());
    }


    public void switchFormatButton(boolean switch_or_not, WebElement switch_button) {
        boolean button_status = Boolean.parseBoolean(switch_button.getAttribute("aria-pressed"));
        if ((button_status && !switch_or_not) || (!button_status && switch_or_not)) {
            switch_button.click();
        }
    }

    public void typeInIframeFormatted(String text_to_type, WebElement text_format) {
        driver.switchTo().parentFrame();
        this.switchFormatButton(false, this.getUnderlinedButton());
        this.switchFormatButton(false, this.getBoldButton());
        this.switchFormatButton(true, text_format);
        this.switchToIframeEditor();
        this.getEditorBody().sendKeys(text_to_type);
        driver.switchTo().parentFrame();
    }

    public void typeInIframeNoFormat(String text_to_type) {
        driver.switchTo().parentFrame();
        this.switchFormatButton(false, this.getUnderlinedButton());
        this.switchFormatButton(false, this.getBoldButton());
        this.switchToIframeEditor();
        this.getEditorBody().sendKeys(text_to_type);
        driver.switchTo().parentFrame();
    }

    public String getTagNameInIframeByText(String text_to_assert) {
        String xpath_text = "//*[text()[contains(.,'%s')]]".formatted(text_to_assert);
        return driver.findElement(By.xpath(xpath_text)).getTagName();
    }


}
