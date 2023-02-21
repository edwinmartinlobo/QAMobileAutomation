package com.mtaf.screens;

import com.mtaf.constants.Constants;
import com.mtaf.core.Actions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.cucumber.java.en.And;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginScreen extends Actions {

    AppiumDriver<MobileElement> driverInstance;
    public LoginScreen(AppiumDriver<MobileElement> driverInstance) {
        super(driverInstance);
        this.driverInstance = driverInstance;
        PageFactory.initElements(new AppiumFieldDecorator(driverInstance, Duration.ofSeconds(Constants.WAIT_TIMER))
                , this);
    }

    @AndroidFindBy(xpath = "//*[@resource-id='emailAddress']")
    private MobileElement mEmailTextbox;

    @AndroidFindBy(xpath="//*[@resource-id='password']")
    private MobileElement mPwdTextbox;

    @AndroidFindBy(xpath="//*[@resource-id='ctaForgotYourPassword']")
    private MobileElement mForgotPwd;

    @AndroidFindBy(xpath="//*[@resource-id='ctaSignIn']")
    private MobileElement mSingInBtn;

    @AndroidFindBy(xpath="//*[@resource-id='emailAddress-feedback']")
    private MobileElement mInvalidEmailMsg;

    public void enterUserName(String userName){
        enterText(mEmailTextbox, userName);
    }

    public void enterPassword(String userName){
        enterText(mPwdTextbox, userName);
    }

    public void clickSingInBtn(){
        click(mSingInBtn);
    }

    public String getInvalidEmailMsg(){
        return mInvalidEmailMsg.getText();
    }
}
