import org.junit.jupiter.api.BeforeEach;
import pages.*;

public class PagesCreation extends TestBase {
    public CodeBreakerChallenge codeBreakerChallenge;
    @BeforeEach
    public void setupPages(){
        codeBreakerChallenge=new CodeBreakerChallenge(driver);
    }
}
