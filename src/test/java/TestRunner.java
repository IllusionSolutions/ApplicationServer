import org.junit.internal.runners.statements.Fail;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import powerCloudTests.TestSuite;

public class TestRunner
{
    public static void main(String [] args)
    {
        Result results = JUnitCore.runClasses(TestSuite.class);
        for (Failure failure : results.getFailures())
        {
            System.out.println(failure.toString());
        }
    }
}
