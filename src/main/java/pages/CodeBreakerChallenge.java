package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CodeBreakerChallenge extends PageBase {
    public CodeBreakerChallenge(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(text(),'150')]/../..")
    private WebElement flexHackButton;
    @FindBy(xpath = "//h4[contains(text(),'Code Breaker')]/..//button[last()]")
    private WebElement hackButton;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[2]//input")
    private WebElement codeInput;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[last()]/button")
    private WebElement submitCodeButton;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[2]")
    private WebElement firstValidationAlert;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[3]")
    private WebElement secondScoreAlert;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[2]/button[last()]")
    private WebElement closeValidationAlertButton;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[3]/button[last()]")
    private WebElement closeScoreAlertButton;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../div[2]//strong")
    private WebElement solvedLabel;

    public String getTextOfSolvedLabel() {
        return solvedLabel.getText();
    }

    public String getValidationText() {
        waitToBeVisible(By.xpath("//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[2]"));
        return firstValidationAlert.getText();
    }

    public String getScoreText() {
        waitToBeVisible(By.xpath("//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[3]"));
        return secondScoreAlert.getText();
    }

    public void closeAlerts() {
        waitToBeVisible(By.xpath("//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[3]/button[last()]"));
        click(closeValidationAlertButton);
        click(closeScoreAlertButton);
    }

    public void closeValidationAlert() {
        waitToBeVisible(By.xpath("//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[2]/button[last()]"));
        click(closeValidationAlertButton);
    }

    public void closeScoreAlert() {
        waitToBeVisible(By.xpath("//h5[contains(text(),'Code Breaker')]/../../*[last()]/*[3]/button[last()]"));
        click(closeScoreAlertButton);
    }

    public void clickHackButton() {
        waitToBeVisible(By.xpath("//h4[contains(text(),'Code Breaker')]/..//button[last()]"));
        click(hackButton);
    }

    public void getHackButton(WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(flexHackButton).perform();
    }

    public void waitForHoverBoard() {
        waitToBeVisible(By.xpath("//h1[contains(text(),'150')]/../.."));
    }

    public void waitForCodeInput() {
        waitToBeVisible(By.xpath("//h5[contains(text(),'Code Breaker')]/../../*[2]//input"));
    }

    public void sendCode(String code) {
        sendKeys(codeInput, code);
    }

    public void clickSubmitButton() {
        waitToBeClickable(By.xpath("//h5[contains(text(),'Code Breaker')]/../../*[last()]/button"));
        click(submitCodeButton);

    }

    public void checkClear() {
        waitToBeInvisible(firstValidationAlert);
        waitToBeInvisible(secondScoreAlert);
    }

    private String actualScoreText = "Wrong. Score is 0\n" + "×";

    public String getActualScoreText() {
        return actualScoreText;
    }

    public void setActualScoreText(String text) {
        actualScoreText = text;
    }

    private String brokenCode = "";

    public String getBrokenCode() {
        return brokenCode;
    }

    public void setBrokenCode(String code) {
        brokenCode = code;
    }
    public void breakingCode(int firstDecASCINum,int lastDecASCINum) {

        while (getBrokenCode().length() < 7) {
            for (int q = firstDecASCINum; q <= lastDecASCINum; q++) {
                char j = (char) q;
                String code = getBrokenCode() + j;
                sendCode(code);
                checkClear();
                clickSubmitButton();
                try {
                    if (!getScoreText().equals(getActualScoreText())) {
                        setActualScoreText(getScoreText());
                        setBrokenCode(code);
                        break;
                    }
                } catch(Exception e){
                    if (code.length()==7) {
                        setBrokenCode(code);
                    }else {
                        System.out.println("Program nie odnalazl wartosci wyniku na stronie dla dlugosci kodu innej niz 7");
                    }
                    break;
                }
                if (getBrokenCode().length()<7){
                    closeAlerts();
                }

            }

        }

    }

}



