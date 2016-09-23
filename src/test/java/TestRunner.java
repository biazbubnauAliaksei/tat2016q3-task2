import org.testng.TestNG;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Al on 23.09.2016.
 */
public class TestRunner {
    static final String SUIT_PATH = "./src/test/resources/";
    static final String COMMON_TESTS = "common-tests.xml";
    static final String TOTAL_TESTS = "total-tests.xml";
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        List<String> files = Arrays.asList(SUIT_PATH + COMMON_TESTS, SUIT_PATH + TOTAL_TESTS);

        testNG.setTestSuites(files);
        testNG.run();
    }
}
