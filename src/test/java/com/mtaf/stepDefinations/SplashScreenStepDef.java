package com.mtaf.stepDefinations;

import com.mtaf.managers.appium.AppiumDriverManager;
import com.mtaf.screens.LoginScreen;
import com.mtaf.screens.SplashScreen;
import com.mtaf.screens.WelcomeScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SplashScreenStepDef {

    private SplashScreen splashScreen;
    private LoginScreen loginScreen;
    private WelcomeScreen welcomeScreen;
    private AppiumDriverManager appiumDriverManager;

    public SplashScreenStepDef(){
        appiumDriverManager = new AppiumDriverManager();
        splashScreen = new SplashScreen(appiumDriverManager.getDriver());
    }
    @Given("User is on Splash screen")
    public void userIsOnSplashScreen() {
        assert splashScreen.isSplashScreenLoaded();
    }

    @When("Tap on get started button")
    public void tapOnGetStartedButton() {
        welcomeScreen=splashScreen.navigateToWelcomeScreen();
    }

    @Then("It will navigate the user to Welcome screen asking {string}")
    public void itWillNavigateTheUserToWelcomeScreenAsking(String welcomeText) {
        welcomeScreen.isWelcomeTextVisible(welcomeText);
    }

    @When("User swipe to left")
    public void userSwipeToLeft() {
        splashScreen.swipeCorouselScreen();
    }

    @Then("Carousel should swipe to left and correct animation should be displayed")
    public void carouselShouldSwipeToLeftAndCorrectAnimationShouldBeDisplayed() {
    }

    @Then("Verify the app title {string} and {int} carousel sliders")
    public void verifyTheAppTitleAndCarouselSliders(String arg0, int arg1) {
    }

    @Then("Verify second section which contains welcome screen")
    public void verifySecondSectionWhichContainsWelcomeScreen() {
    }
}
