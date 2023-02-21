package com.mtaf.screens;

import com.mtaf.constants.Constants;
import com.mtaf.core.Actions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class WelcomeScreen extends Actions {
    AppiumDriver<MobileElement> driverInstance;
    public WelcomeScreen(AppiumDriver<MobileElement> driverInstance) {
        super(driverInstance);
        this.driverInstance = driverInstance;
        PageFactory.initElements(new AppiumFieldDecorator(driverInstance, Duration.ofSeconds(Constants.WAIT_TIMER))
                , this);
    }

    @AndroidFindBy(id="//*[@text='Would you like to connect to your Skiin Garment?']")
    private MobileElement mWelcomeText;

    public void isWelcomeTextVisible(String welcomeText)
    {
        String welcomeTextXpath = "//*[@text='%ACTUAL_TEXT%']";
        welcomeTextXpath.replace("%ACTUAL_TEXT%", welcomeText);
        assert isElementVisible(driverInstance.findElement(By.xpath(welcomeTextXpath)));
    }

}
