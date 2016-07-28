package powerCloudTests;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AssertTests.class,
        ExceptionTests.class,
        ParameterizedTests.class
})
public class TestSuite {}
