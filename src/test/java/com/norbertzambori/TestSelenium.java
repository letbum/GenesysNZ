package com.norbertzambori;

import com.pageobjects.HTMLEditor.editor;
import com.pageobjects.MyStore.home_page;
import com.pageobjects.MyStore.registration_form;
import com.pageobjects.MyStore.user_page;
import com.utils.TestTools;
import com.utils.DriverFactory;
import com.utils.logger;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestSelenium {
    WebDriver driver;
    TestTools tools;
    home_page objHomePage;
    registration_form objRegistrationForm;
    user_page objUserPage;
    editor objEditor;
    DriverFactory driver_factory;
    logger logger;

    @BeforeMethod
    public void setUp(Method method) {
        driver_factory = new DriverFactory();
        driver = driver_factory.getChromeDriver();
        objRegistrationForm = new registration_form(driver);
        objHomePage = new home_page(driver);
        objUserPage = new user_page(driver);
        objEditor = new editor(driver);
        tools = new TestTools(driver);
        logger = new logger();
        logger.logToFile("Starting test - " + method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String tcName = result.getName();
        if(result.getStatus() == ITestResult.SUCCESS) {
            logger.logToFile(tcName  + " has passed");
        } else if(result.getStatus() == ITestResult.FAILURE) {
            logger.logToFile(tcName  + " has failed");
        } else if(result.getStatus() == ITestResult.SKIP ){
            logger.logToFile(tcName  + " has skipped");
        }
        driver.quit();
    }

    @Test()
    public void AutomateUserRegistrationProcess(){
        String email_address = tools.generate_email("test_email");
        driver.get("http://automationpractice.com/index.php");
        tools.wait_for_element(objHomePage.getLoginButton());
        objHomePage.clickLogin();
        tools.wait_for_element(objHomePage.getEmailField());
        objHomePage.setEmailField(email_address);
        objHomePage.clickSubmit();
        tools.wait_for_element(objRegistrationForm.getFirstName());
        objRegistrationForm.getGenderMale().click();
        objRegistrationForm.getFirstName().sendKeys("John");
        objRegistrationForm.getLastName().sendKeys("Smith");
        objRegistrationForm.getPasswordField().sendKeys("test#1234");
        tools.select_option_by_value(objRegistrationForm.getBirthDay(), "25");
        tools.select_option_by_value(objRegistrationForm.getBirthMonth(), "10");
        tools.select_option_by_value(objRegistrationForm.getBirthYear(), "1999");
        objRegistrationForm.getAddressFirstName().sendKeys("John");
        objRegistrationForm.getAddressLastName().sendKeys("Smith");
        objRegistrationForm.getAddressCompany().sendKeys("Testing Ltd.");
        objRegistrationForm.getAddressLineOne().sendKeys("Bug street 01");
        objRegistrationForm.getAddressLineTwo().sendKeys("Second floor 15. door");
        objRegistrationForm.getAddressCity().sendKeys("New York");
        tools.select_option_by_value(objRegistrationForm.getAddressState(), "32");
        objRegistrationForm.getAddressPostalCode().sendKeys("10001");
        tools.select_option_by_value(objRegistrationForm.getAddressCountry(), "21");
        objRegistrationForm.getAddressAdditionalInformation().sendKeys("Beware of the cat!");
        objRegistrationForm.getAddressMobilePhone().sendKeys("+001122334455");
        objRegistrationForm.getRegisterButton().click();
        objUserPage.getPersonalInformation().click();
        tools.wait_for_element(objUserPage.getFirstName());
        Assert.assertEquals(objUserPage.getFirstName().getAttribute("value"), "John");
        objUserPage.getSignOut().click();
    }

    @Test()
    public void VerifyErrorMessagesForMandatoryFields() {
        String email_address = tools.generate_email("test_email");
        driver.get("http://automationpractice.com/index.php");
        tools.wait_for_element(objHomePage.getLoginButton());
        objHomePage.clickLogin();
        tools.wait_for_element(objHomePage.getEmailField());
        objHomePage.setEmailField(email_address);
        objHomePage.clickSubmit();
        tools.wait_for_element(objRegistrationForm.getFirstName());
        objRegistrationForm.getRegisterButton().click();
        Assert.assertTrue(objRegistrationForm.getMandatoryFieldsAlert().isDisplayed());
    }

    @Test()
    public void iFrameAndTabHandling() {
        driver.get("https://onlinehtmleditor.dev/");
        objEditor.typeInIframeFormatted("Automation ", objEditor.getBoldButton());
        objEditor.typeInIframeFormatted("Test", objEditor.getUnderlinedButton());
        objEditor.typeInIframeNoFormat(" Example");
        objEditor.switchToIframeEditor();
        Assert.assertEquals(objEditor.getTagNameInIframeByText("Automation"), "strong");
        Assert.assertEquals(objEditor.getTagNameInIframeByText("Test"), "u");
        Assert.assertEquals(objEditor.getTagNameInIframeByText("Example"), "p");

    }

    @Test()
    public void ScrollDownWithJavaScript() {
        driver.get("http://automationpractice.com/index.php");
        tools.scrollByPixelUntilElement(100, objHomePage.getFooter());
    }
}