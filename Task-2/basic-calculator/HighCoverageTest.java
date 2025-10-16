import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void testSimpleAddition() {
    	Solution solution = new Solution();
        assertEquals(3, solution.calculate("1 + 2"));
    }

    @Test
    public void testSimpleSubtraction() {
    	Solution solution = new Solution();
        assertEquals(2, solution.calculate("5 - 3"));
    }

    @Test
    public void testMultipleOperations() {
    	Solution solution = new Solution();
        assertEquals(4, solution.calculate("2 + 3 - 1"));
    }

    @Test
    public void testParenthesesUsage() {
    	Solution solution = new Solution();
        assertEquals(1, solution.calculate("(1 + 2) - (3 - 1)"));
    }

    @Test
    public void testComplexExpression() {
    	Solution solution = new Solution();
        assertEquals(9, solution.calculate("(1 + (2 - 3)) + ((4 + 5) - 6)"));
    }
}

