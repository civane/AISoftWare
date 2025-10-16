import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {
	Solution solution = new Solution();

    @Test
    public void test1_NoCollisions_AllRight() {
        int[] positions = {1, 2, 3};
        int[] healths = {5, 10, 15};
        String directions = "RRR";
        
        List<Integer> result = solution.survivedRobotsHealths(positions, healths, directions);
        assertEquals(Arrays.asList(5, 10, 15), result);
    }

    @Test
    public void test2_RightStronger() {
        int[] positions = {1, 3};
        int[] healths = {10, 5};
        String directions = "RL";
        
        List<Integer> result = solution.survivedRobotsHealths(positions, healths, directions);
        assertEquals(Arrays.asList(9), result);
    }

    @Test
    public void test3_LeftStronger() {
        int[] positions = {1, 3};
        int[] healths = {5, 10};
        String directions = "RL";
        
        List<Integer> result = solution.survivedRobotsHealths(positions, healths, directions);
        assertEquals(Arrays.asList(9), result);
    }

    @Test
    public void test4_EqualHealth() {
        int[] positions = {1, 3};
        int[] healths = {10, 10};
        String directions = "RL";
        
        List<Integer> result = solution.survivedRobotsHealths(positions, healths, directions);
        assertTrue(result.isEmpty());
    }

    @Test
    public void test5_MultipleCollisions() {
        int[] positions = {1, 2, 3, 4, 5};
        int[] healths = {2, 3, 4, 15, 5};
        String directions = "RRRLL";
        
        List<Integer> result = solution.survivedRobotsHealths(positions, healths, directions);
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}

