import com.gurock.testrail.TestCaseId
import com.gurock.testrail.TestRail
import com.gurock.testrail.TestRailListeners
import geb.Browser
import org.openqa.selenium.Keys
import org.testng.annotations.Listeners
import org.testng.annotations.Test

@Listeners(TestRailListeners.class)
class GoogleTest extends TestRail {
    @Test
    @TestCaseId(id = 1)
    public void googleTest() {
        Browser.drive {
            go "http://google.com"
            logger.info("Go to page ")
            assert title == "Google","Page title not start with"

            $("input#lst-ib") << "Geb" + Keys.ENTER
            logger.info("Send Geb to input")
            waitFor { $(".rc > h3 > a") }

            logger.info("Verify results")
            assert $(".rc > h3 > a").size() == 9
            assert $(".rc > h3 > a").first().text() == "Geb - Very Groovy Browser Automation"

            assert title.startsWith("Geb")

        }

    }

    @Test
    @TestCaseId(id = 2)
    public void googleTest3() {
        logger.info("Pass test")
        assert 2 == 2
    }

    @Test
    @TestCaseId(id = 57)
    public void googleTest2() {
        logger.info("Failed test")
        assert 5 == 6
    }
}
