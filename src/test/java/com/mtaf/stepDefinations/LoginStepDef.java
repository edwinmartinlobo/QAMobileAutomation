package com.mtaf.stepDefinations;

import com.mtaf.managers.appium.AppiumDriverManager;
import com.mtaf.screens.SplashScreen;
import com.mtaf.screens.LoginScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginStepDef {
    private SplashScreen splashScreen;
    private LoginScreen loginScreen;
    private AppiumDriverManager appiumDriverManager;

    public LoginStepDef(){
        appiumDriverManager = new AppiumDriverManager();
        splashScreen = new SplashScreen(appiumDriverManager.getDriver());
    }

    @When("User enter username as {string}")
    public void userEnterUsernameAs(String userName) {
        loginScreen = splashScreen.navigateToSignInScreen();
        loginScreen.enterUserName(userName);
    }

    @And("User enter password as {string}")
    public void userEnterPasswordAs(String password) {
        loginScreen.enterPassword(password);
    }

    @And("User click on login button")
    public void userClickOnLoginButton() {
        loginScreen.clickSingInBtn();
    }

    @Then("login should fail with an error {string}")
    public void loginShouldFailWithAnError(String errorMsg) {
        Assert.assertEquals(errorMsg, loginScreen.getInvalidEmailMsg());
    }

    @Given("User is on splash screen")
    public void userIsOnSplashScreen() {
    }

    @And("User will land on welcome screen")
    public void userWillLandOnWelcomeScreen() {
    }

    @And("There are two buttons Yes and Don't have a Garment")
    public void thereAreTwoButtonsYesAndDonTHaveAGarment() {
    }

    @And("Tap on Yes>>Garment selection screen will appear")
    public void tapOnYesGarmentSelectionScreenWillAppear() {
    }

    @And("Tap on the Garment and a warning pop up with Okay and Cancel button will appear on screen")
    public void tapOnTheGarmentAndAWarningPopUpWithOkayAndCancelButtonWillAppearOnScreen() {
    }

    @Then("Verify pop up has title: Note to user")
    public void verifyPopUpHasTitleNoteToUser() {
    }

    @And("Verify pop up content as per figma")
    public void verifyPopUpContentAsPerFigma() {
    }

    @And("Tapping on cancel will bring the user to Garment selection")
    public void tappingOnCancelWillBringTheUserToGarmentSelection() {
    }

    @And("Tapping Okay will lead the user to new screen")
    public void tappingOkayWillLeadTheUserToNewScreen() {
    }

    @And("Tap on Don't have A Garment button")
    public void tapOnDonTHaveAGarmentButton() {
    }

    @And("Select a persona on the next screen that appears")
    public void selectAPersonaOnTheNextScreenThatAppears() {
    }

    @And("On the next screen that appears click on Let's Begin! button")
    public void onTheNextScreenThatAppearsClickOnLetSBeginButton() {
    }

    @And("Enter {string}, {string}, {string} and check Terms and Conditions check box details")
    public void enterAndCheckTermsAndConditionsCheckBoxDetails(String arg0, String arg1, String arg2) {
    }

    @And("Click on Next button")
    public void clickOnNextButton() {
    }

    @And("On the next screen appears provide {string} and confirm password")
    public void onTheNextScreenAppearsProvideAndConfirmPassword(String arg0) {
    }

    @And("Click on sign up button")
    public void clickOnSignUpButton() {
    }

    @Then("Verify email is sent to user's email address contains code")
    public void verifyEmailIsSentToUserSEmailAddressContainsCode() {
    }

    @And("User is taken to the screen asking to input the code")
    public void userIsTakenToTheScreenAskingToInputTheCode() {
    }
}
