import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CodeBreakerChallenge;

public class CodeBreakerChallengeTest extends PagesCreation {

    @Test
    public void resolveChallenge(){
        codeBreakerChallenge.waitForHoverBoard();
        Assertions.assertEquals(CodeBreakerChallenge.properText("firstTextofBoard"),codeBreakerChallenge.firstTextofBoard());
        codeBreakerChallenge.showHackButton(driver);
        Assertions.assertEquals(CodeBreakerChallenge.properText("titleOfBoard"),codeBreakerChallenge.titleOfBoard());
        Assertions.assertEquals(CodeBreakerChallenge.properText("subtitleOfBoard"),codeBreakerChallenge.subtitleOfBoard());
        Assertions.assertEquals(CodeBreakerChallenge.properText("textOfHackButton"),codeBreakerChallenge.textOfHackButton());
        codeBreakerChallenge.clickHackButton();
        codeBreakerChallenge.waitForCodeInput();
        Assertions.assertEquals(CodeBreakerChallenge.properText("descriptionOfChallenge"),codeBreakerChallenge.descriptionOfChallenge());
        Assertions.assertEquals(CodeBreakerChallenge.properText("descriptionOfCodeInput"),codeBreakerChallenge.descriptionOfCodeInput());
        Assertions.assertFalse(codeBreakerChallenge.visibilityOfSolvedLabel());
        codeBreakerChallenge.breakingCode(32,126);
        Assertions.assertTrue(codeBreakerChallenge.visibilityOfSolvedLabel());
        Assertions.assertEquals(CodeBreakerChallenge.properText("textOfSolvedLabel"),codeBreakerChallenge.textOfSolvedLabel());
    }
}

