java -ea -classpath /Users/civane/Desktop/Eclipse/LCOne/src/randoop-all-4.3.4.jar:./ randoop.main.Main gentests --testclass=Solution --time-limit=60 --maxsize=80 --output-limit=10 --testsperfile=100  --randomseed=927
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ RegressionTest0.class })
public class RegressionTest {
}

