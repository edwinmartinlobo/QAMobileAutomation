package com.mtaf.core;

import com.mtaf.constants.Constants;
import com.mtaf.utils.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Actions {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;
    public Actions(AppiumDriver<MobileElement> driverInstance) {
        this.driver = driverInstance;
        wait = new WebDriverWait(driver, Constants.WAIT_TIMER);
    }

    /**
     * This method will be used for clicking on element
     * */
    public void click(MobileElement e) {
        waitTillElementVisible(e);
        e.click();
    }

    public void click(MobileElement e, String msg) {
        waitTillElementVisible(e);
        Log.INFO(msg);
        e.click();
    }

    public void click(By e, String msg) {
        waitTillElementVisible(e);
        Log.INFO(msg);
        driver.findElement(e).click();
    }

    public void enterText(MobileElement e, String txt) {
        waitTillElementVisible(e);
        e.sendKeys(txt);
    }

    public void enterText(MobileElement e, String txt, String msg) {
        waitTillElementVisible(e);
        Log.INFO(msg);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement element, String attribute) {
        waitTillElementVisible(element);
        return element.getAttribute(attribute);
    }

    public String getAttribute(By element, String attribute) {
        waitTillElementVisible(element);
        return driver.findElement(element).getAttribute(attribute);
    }

//    public String getText(MobileElement element, String msg) {
//        String txt;
//        switch(new GlobalParams().getPlatformName()){
//            case "Android":
//                txt = getAttribute(element, "text");
//                break;
//            case "iOS":
//                txt = getAttribute(element, "label");
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
//        }
//        utils.log().info(msg + txt);
//        return txt;
//    }
//
//    public String getText(By by, String msg) {
//        String txt;
//        switch(new GlobalParams().getPlatformName()){
//            case "Android":
//                txt = getAttribute(by, "text");
//                break;
//            case "iOS":
//                txt = getAttribute(by, "label");
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + new GlobalParams().getPlatformName());
//        }
//        utils.log().info(msg + txt);
//        return txt;
//    }

    /**
     *
     * @param mobileElement
     * @return
     */
    protected boolean isElementVisible(MobileElement mobileElement){
        try{
            Log.INFO("Looking for element to Display");
            return mobileElement.isDisplayed();
        }
        catch (Exception ex){
            Log.ERROR("Element is not getting displayed");
            return false;
        }

    }

    private void swipeUp(){
        Log.INFO("Swipping Up");
        TouchAction action = new TouchAction(driver);
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        int endY  = (int) (height*0.3);
        int startY = (int) (height*0.7);
        int Xconstant = width/2;
        action.press(PointOption.point(Xconstant, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(Xconstant, endY))
                .release()
                .perform();
    }

    private void swipeDown(){
        TouchAction action = new TouchAction(driver);
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        int endY  = (int) (height*0.5);
        int startY = (int) (height*0.9);
        int Xconstant = width/2;
        Log.INFO("Swipping down");
        action.press(PointOption.point(Xconstant, endY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(Xconstant, startY))
                .release()
                .perform();
    }

    protected void swipeLeft(){
        Dimension size = driver.manage().window().getSize();
        System.out.println(size.height+"height");
        System.out.println(size.width+"width");
        System.out.println(size);
        int startPoint = (int) (size.width * 0.99);
        int endPoint = (int) (size.width * 0.15);
        int ScreenPlace =(int) (size.height*0.40);
        int y=(int)size.height*20;

        TouchAction ts = new TouchAction(driver);
        //for(int i=0;i<=3;i++) {
        ts.press(PointOption.point(startPoint,ScreenPlace ))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endPoint,ScreenPlace )).release().perform();
    }

    protected void waitTillElementVisible(MobileElement mobileElement){
        Log.INFO("Waiting for element to visible");
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
    }

    protected void waitTillElementVisible(By e) {
        Log.INFO("Waiting for element to visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(e));
    }

    protected void swipeUpTillElementFound(MobileElement mobileElement){
        int retry = 0;
        while(!isElementVisible(mobileElement)){
            if(retry >=3){ break;}
            Log.INFO("Swipping UP till element found, Retry, "+retry);
            swipeUp();
            retry++;
        }
        Log.INFO("Element found on Screen");
    }

    protected void swipeDownTillElementFound(MobileElement mobileElement){
        int retry = 0;
        while(!isElementVisible(mobileElement)){
            if(retry >=3){ break;}
            Log.INFO("Swipping Down till element found, Retry, "+retry);
            swipeDown();
            retry++;
        }
        Log.INFO("Element found on Screen");
    }
}
