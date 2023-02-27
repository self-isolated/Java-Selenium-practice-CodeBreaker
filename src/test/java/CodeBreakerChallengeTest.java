import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CodeBreakerChallengeTest extends PagesCreation {

    @Test
    public void resolveChallenge(){
        codeBreakerChallenge.waitForHoverBoard();
        Assertions.assertEquals(codeBreakerChallenge.properText("firstTextofBoard"),codeBreakerChallenge.firstTextofBoard());
        codeBreakerChallenge.showHackButton(driver);
        Assertions.assertEquals(codeBreakerChallenge.properText("titleOfBoard"),codeBreakerChallenge.titleOfBoard());
        Assertions.assertEquals(codeBreakerChallenge.properText("subtitleOfBoard"),codeBreakerChallenge.subtitleOfBoard());
        Assertions.assertEquals(codeBreakerChallenge.properText("textOfHackButton"),codeBreakerChallenge.textOfHackButton());
        codeBreakerChallenge.clickHackButton();
        codeBreakerChallenge.waitForCodeInput();
        Assertions.assertEquals(codeBreakerChallenge.properText("descriptionOfChallenge"),codeBreakerChallenge.descriptionOfChallenge());
        Assertions.assertEquals(codeBreakerChallenge.properText("descriptionOfCodeInput"),codeBreakerChallenge.descriptionOfCodeInput());
        Assertions.assertFalse(codeBreakerChallenge.visibilityOfSolvedLabel());
        codeBreakerChallenge.breakingCode(32,126);
        Assertions.assertTrue(codeBreakerChallenge.visibilityOfSolvedLabel());
        Assertions.assertEquals(codeBreakerChallenge.properText("textOfSolvedLabel"),codeBreakerChallenge.textOfSolvedLabel());
    }
}

