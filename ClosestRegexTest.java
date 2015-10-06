import org.junit.*;
import static org.junit.Assert.*;

public class ClosestRegexTest {
	@Test
	public void test() {
		ClosestRegex regex = new ClosestRegex();
		assertEquals("bcdd", regex.closestString("abcd", "bcdd"));
		assertEquals("ttpcodee", regex.closestString("topcoder", "t*px*coa*de*"));
		assertEquals("cfu", regex.closestString("cmu", "c*m*fm*u*"));
		assertEquals("aaaaabccc", regex.closestString("aaaaacccc", "a*abc*"));
		assertEquals("", regex.closestString("short", "lo*ts*of*let*ter*s"));
	}
}