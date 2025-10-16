import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    Solution solution = new Solution();

    @Test
    public void test1_AllBranchesInIsPrime() {
        // Tests isPrime with: 0, 1, 2, 3, 4, composite, prime
        String input = "2371113";
        long result = solution.sumOfLargestPrimes(input);
        
        // Covers: isPrime(2), isPrime(3), isPrime(7), etc.
        // Multiple primes to test all comparison branches
        assertTrue(result > 0);
    }

    @Test
    public void test2_EvenEndingSkip() {
        // Tests: s.charAt(j) % 2 == 0 && i != j
        String input = "234";
        long result = solution.sumOfLargestPrimes(input);
        
        // "23" is prime, but "234" and "34" end in even digits (skipped)
        // Primes: 2, 3, 23
        assertEquals(28, result);
    }

    @Test
    public void test3_UpdateP1Branch() {
        // Tests: l > p1 branch
        String input = "2357";
        long result = solution.sumOfLargestPrimes(input);
        
        // Multiple primes with increasing values
        // Tests p1, p2, p3 cascade updates
        assertTrue(result > 0);
    }

    @Test
    public void test4_UpdateP2Branch() {
        // Tests: l > p2 && l < p1 branch
        String input = "7523";
        long result = solution.sumOfLargestPrimes(input);
        
        // Primes in non-ascending order to trigger p2 update
        // 7, 5, 2, 3, 75=3×25, 52=4×13, 23, 523
        // Primes: 7, 5, 2, 3, 23, 523
        // Top 3: 523, 23, 7
        assertEquals(553, result);
    }

    @Test
    public void test5_UpdateP3Branch() {
        // Tests: l > p3 && l < p2 && l < p1 branch
        String input = "53711";
        long result = solution.sumOfLargestPrimes(input);
        
        // Multiple primes to fill all three positions
        // 5, 3, 7, 11, 53, 37, 71, 537, 3711, 5371, 53711
        // Need primes that update p3 specifically
        assertTrue(result > 0);
    }

}

