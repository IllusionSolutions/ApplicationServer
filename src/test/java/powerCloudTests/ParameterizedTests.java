package powerCloudTests;

import org.junit.*;
import com.illusionsolutions.persistence.FirebaseHandler.*;


import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ParameterizedTests
{
    private FirebaseHandler fbHandler;
    private String inputString;
    private Boolean expectedResult;

    public ParameterizedTests(String inputString, Boolean expectedResult)
    {
        this.inputString = inputString;
        this.expectedResult = expectedResult;
        fbHandler = new FirebaseHandler();
    }

    @Parameterized.Parameters
    public static Collection Months()
    {
        return Arrays.asList(new Object[][]
                {
                    { "Jan", true },
                    { "Feb", true },
                    { "Mar", true },
                    { "Apr", true },
                    { "May", true },
                    { "Jun", true },
                    { "Jul", true },
                    { "Aug", true },
                    { "Sep", true },
                    { "Oct", true },
                    { "Nov", true },
                    { "Dec", true },
                    { "Wan", false },
                    { "Ter", false },
                    { "Ber", false },
                    { "Per", false },
                    { "Per", false },
                    { "Per", false },
                    { "Per", false }
                });
    }

    public Boolean testCheckMonth(String inputString)
    {
        if(fbHandler.checkMonth(inputString) > -1)
        {
            return true;
        }
        return false;
    }

    @Test
    public void checkMonth()
    {
        System.out.println("Parameterized String is : " + inputString);
        System.out.println("Expected Result is : " + expectedResult);
        assertEquals(expectedResult, testCheckMonth(inputString));
    }
}
