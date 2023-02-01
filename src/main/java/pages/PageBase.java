package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

abstract class PageBase {
    public WebDriverWait wait;
    public PageBase(WebDriver driver){
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void click(WebElement element) {
//        System.out.println("Clicking on: " + element.getText());
        element.click();
    }
    public void sendKeys(WebElement element, String textToSend) {
//        System.out.println("Typing: " + textToSend);
        element.sendKeys(textToSend);
    }
    public void sendKeysWithClear(WebElement element, String textToSend) {
        System.out.println("Clearing field");
        element.clear();
        sendKeys(element, textToSend);
    }
    public void waitToBeVisible(By by){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitToBePresent(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitToBeClickable(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitToBeInvisible(WebElement element){wait.until(ExpectedConditions.invisibilityOf(element));}

    public void moveToElement(WebElement element, WebDriver driver){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public WebElement getRandomElement(List<WebElement> elements) {
        Random random = new Random();
        int randomIndexFromList = random.nextInt(elements.size());

        return elements.get(randomIndexFromList);
    }

}