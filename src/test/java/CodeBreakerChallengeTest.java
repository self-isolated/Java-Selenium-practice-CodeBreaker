import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class CodeBreakerChallengeTest extends PagesCreation {

    @Test
    public void breakingCode(){
        codeBreakerChallenge.waitForHoverBoard();
        codeBreakerChallenge.getHackButton(driver);
        codeBreakerChallenge.clickHackButton();
        codeBreakerChallenge.waitForCodeInput();
        codeBreakerChallenge.breakingCode(32,126);
        Assertions.assertEquals("SOLVED!!",codeBreakerChallenge.getTextOfSolvedLabel());
    }
}

