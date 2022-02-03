// DO NOT TOUCH THIS FILE!
package edu.nyu.cs;

// import junit 4 testing framework
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.junit.contrib.java.lang.system.SystemOutRule; // system rules lib - useful for capturing system output
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
// import static org.mockito.Mockito.*;


public class BlackjackTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule();

    @Test
    public void testWelcomeMessage() {
        systemInMock.provideLines("stand");
        systemOutRule.enableLog(); // start capturing System.out
        String[] args = {};
        try {
            Blackjack.main(args);
            String output = systemOutRule.getLogWithNormalizedLineSeparator().strip();
            String[] lines = output.split("\n");
            String actual = lines[0].strip();
            String expected = "Welcome to Blackjack!";
            assertEquals(expected, actual);
        }
        catch (Exception e) {
            assert false; // fail the test if any exception occurs
        }

    }

    @Test
    public void testUserBust() {
        systemInMock.provideLines("hit", "hit", "hit", "hit", "hit", "hit", "hit", "hit", "hit", "hit", "hit");
        systemOutRule.enableLog(); // start capturing System.out
        String[] args = {};
        try {
            Blackjack.main(args);
            String output = systemOutRule.getLogWithNormalizedLineSeparator().strip();
            String[] lines = output.split("\n");
            String actualSecondToLastLine = lines[lines.length -2].strip();
            String actualLastLine = lines[lines.length -1].strip();
            String expectedLastLine = "Dealer wins!";
            String expectedSecondToLastLine = "You have bust!";
            assertEquals(expectedLastLine, actualLastLine);
            assertEquals(expectedSecondToLastLine, actualSecondToLastLine);
        }
        catch (Exception e) {
            assert false; // fail the test if any exception occurs
        }

    }


}
