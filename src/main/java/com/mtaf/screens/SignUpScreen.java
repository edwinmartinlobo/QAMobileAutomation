package com.mtaf.screens;

import com.mtaf.constants.Constants;
import com.mtaf.core.Actions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SignUpScreen extends Actions {
    AppiumDriver<MobileElement> driverInstance;
    public SignUpScreen(AppiumDriver<MobileElement> driverInstance) {
        super(driverInstance);
        this.driverInstance = driverInstance;
        PageFactory.initElements(new AppiumFieldDecorator(driverInstance, Duration.ofSeconds(Constants.WAIT_TIMER))
                , this);
    }

    @AndroidFindBy(id = "ctaHomeSignIn")
    private MobileElement mSignInBtn;

    @AndroidFindBy(id="ctaSignUpForFree")
    private MobileElement mSignUpBtn;
}
