package com.mtaf.screens;

import com.mtaf.constants.Constants;
import com.mtaf.core.Actions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SplashScreen extends Actions {

    AppiumDriver<MobileElement> driverInstance;
    public SplashScreen(AppiumDriver<MobileElement> driverInstance) {
        super(driverInstance);
        this.driverInstance = driverInstance;
        PageFactory.initElements(new AppiumFieldDecorator(driverInstance, Duration.ofSeconds(Constants.WAIT_TIMER))
                , this);
    }

    @AndroidFindBy(id="//*[@resource-id='logo']")
    private MobileElement mScreenTitle;
    @AndroidFindBy(xpath = "//*[@resource-id='ctaHomeSignIn']")
    private MobileElement mSignInBtn;

    @AndroidFindBy(xpath="//*[@resource-id='ctaSignUpForFree']")
    private MobileElement mSignUpBtn;



    public LoginScreen navigateToSignInScreen(){
        click(mSignInBtn);
        return new LoginScreen(driverInstance);
    }

    public String getLandingScreenTitle(){
        String title="";
        title = mScreenTitle.getText();
        return title;
    }

    public boolean isSplashScreenLoaded(){
        waitTillElementVisible(mScreenTitle);
        return isElementVisible(mScreenTitle);
    }

    public WelcomeScreen navigateToWelcomeScreen(){
        click(mSignUpBtn);
        return new WelcomeScreen(driverInstance);
    }

    public void swipeCorouselScreen(){
        //swipeLeft();
    }

}
