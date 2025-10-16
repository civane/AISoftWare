import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {
    Solution solution = new Solution();

    @Test
    public void testAllSpecialChars() {
        assertEquals(3, solution.numberOfSpecialChars("abcABC"));
    }

    @Test
    public void testNoSpecialChars_ReversedOrder() {
        assertEquals(0, solution.numberOfSpecialChars("ABCabc"));
    }

    @Test
    public void testOnlyLowercase() {
        assertEquals(0, solution.numberOfSpecialChars("abcdef"));
    }

    @Test
    public void testMixedValidInvalid() {
        assertEquals(2, solution.numberOfSpecialChars("aAbBCc"));
    }

    @Test
    public void testMultipleOccurrences() {
        assertEquals(1, solution.numberOfSpecialChars("aaBbbA"));
    }
}

