package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.HashMap;
import java.util.Map;


public class CodeBreakerChallenge extends PageBase {

    public CodeBreakerChallenge(WebDriver driver) {
        super(driver);
    }
    private static Map <String,String>boardTexts;

    static {
        boardTexts=new HashMap<>();
        boardTexts.put("firstTextofBoard","150\nPOINTS");
        boardTexts.put("titleOfBoard","Code Breaker");
        boardTexts.put("subtitleOfBoard","Break the alpha-numeric...");
        boardTexts.put("textOfHackButton","HACK!");
        boardTexts.put("descriptionOfChallenge","Break the alpha-numeric code like in spy movies. Each guess returns a score. The higher the score the more characters you have correct and in the correct position.");
        boardTexts.put("descriptionOfCodeInput","Submit your guesses (code is 7 alpha-numeric characters long).");
        boardTexts.put("textOfSolvedLabel","SOLVED!!");
    }
    public static String properText(String element){
        return boardTexts.get(element);
    }

    @FindBy(xpath = "//h1[contains(text(),'150')]/../..")
    private WebElement flexBoard;
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

    @FindBy(xpath = "//h1[contains(text(),'150')]/..")
    private WebElement firstTextOfBoard;

    @FindBy(xpath = "//h1[contains(text(),'150')]/../../following-sibling::div//h4")
    private WebElement titleOfBoard;

    @FindBy(xpath = "//h1[contains(text(),'150')]/../../following-sibling::div//h6")
    private WebElement subtitleOfBoard;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[2]/p")
    private WebElement descriptionOfChallenge;

    @FindBy(xpath = "//h5[contains(text(),'Code Breaker')]/../../*[2]/p[3]")
    private WebElement descriptionOfCodeInput;

    public String descriptionOfCodeInput(){
        return descriptionOfCodeInput.getText();
    }

    public String descriptionOfChallenge(){
        return descriptionOfChallenge.getText();
    }

    public String titleOfBoard(){
        return titleOfBoard.getText();
    }

    public String subtitleOfBoard(){
        return subtitleOfBoard.getText();
    }

    public String textOfHackButton(){
        return hackButton.getText();
    }

    public boolean visibilityOfSolvedLabel() {
        return solvedLabel.isDisplayed();
    }
    public String textOfSolvedLabel() {
        return solvedLabel.getText();
    }

    public String getValidationText() {
        waitToBeVisible(firstValidationAlert);
        return firstValidationAlert.getText();
    }

    public String getScoreText() {
        waitToBeVisible(secondScoreAlert);
        return secondScoreAlert.getText();
    }

    public void closeAlerts() {
        waitToBeVisible(closeScoreAlertButton);
        click(closeValidationAlertButton);
        click(closeScoreAlertButton);
    }

    public void closeValidationAlert() {
        waitToBeVisible(closeValidationAlertButton);
        click(closeValidationAlertButton);
    }

    public void closeScoreAlert() {
        waitToBeVisible(closeScoreAlertButton);
        click(closeScoreAlertButton);
    }

    public void clickHackButton() {
        waitToBeVisible(hackButton);
        click(hackButton);
    }

    public void showHackButton(WebDriver driver) {
        moveToElement(flexBoard,driver);
    }
    public String firstTextofBoard(){
        return firstTextOfBoard.getText();
    }
    public void waitForHoverBoard() {
        waitToBeVisible(flexBoard);
    }
    public void waitForCodeInput() {
        waitToBeVisible(codeInput);}

    public void sendCode(String code) {
        sendKeys(codeInput, code);
    }

    public void clickSubmitButton() {
        waitToBeClickable(submitCodeButton);
        click(submitCodeButton);
    }

    public void checkClear() {
        waitToBeInvisible(firstValidationAlert);
        waitToBeInvisible(secondScoreAlert);
    }

    private String actualScoreText = "Wrong. Score is 0\n×";

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
            for (int i = firstDecASCINum; i <= lastDecASCINum; i++) {
                char charCode = (char) i;
                String code = getBrokenCode() + charCode;
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
                        System.out.println("Program nie odnalazl wartosci wyniku na stronie dla dlugosci kodu innej niz 7 lub innny problem");
                    }
                    break;
                }
                closeAlerts();
                }

            }

        }

    }





